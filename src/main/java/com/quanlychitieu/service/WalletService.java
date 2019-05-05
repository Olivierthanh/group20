package com.quanlychitieu.service;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;

import com.quanlychitieu.dao.UserDao;
import com.quanlychitieu.entity.*;
import com.quanlychitieu.entity.Currency;
import com.quanlychitieu.utils.Utils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanlychitieu.dao.TransactionDao;
import com.quanlychitieu.dao.WalletDao;

import com.quanlychitieu.utils.AjaxMessage;

@Service
public class WalletService {

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private UserDao userDao;

	public Wallet getWallet(int walletId) {
		Wallet wallet = walletDao.getWalletByWalletId(walletId);
		return wallet;
	}

	public int getInflow(int walletId) {
		int result = 0;
		List<Transaction> transactions = transactionDao.getTransactionByTransactionType(walletId,
				TransactionType.income);
		for (Transaction transaction : transactions) {
			result = result + transaction.getAmount();
		}
		return result;
	}

	public int getOutflow(int walletId) {
		int result = 0;
		List<Transaction> transactions = transactionDao.getTransactionByTransactionType(walletId,
				TransactionType.expense);
		for (Transaction transaction : transactions) {
			result = result - transaction.getAmount();
		}
		return result;
	}

	public List<Transaction> getTransactionByWalletId(int walletId) {
		return transactionDao.getTransactionByWalletId(walletId);

	}

	public Set<User> getUserByWalletId(int walletId) {
		Wallet wallet = getWallet(walletId);
		Set<User> listUser = wallet.getListUser();
		return listUser;
	}

	/*public String deleteWallet(int walletId, String email) {
		ObjectMapper mapper = new ObjectMapper();
		String returnMessage = "";
		AjaxMessage message;

		User user = userDao.getUserByEmail(email);
		Wallet wallet = getWallet(walletId);
		boolean deleted = walletDao.deleteWallet(wallet);
		int deletedTransaction = transactionDao.deleteTransactionByWalletId(walletId);
		if (deleted && deletedTransaction==1) {
			message = new AjaxMessage("success", "Delete wallet successfully", "Your wallet is deleted! ");
		} else {
			message = new AjaxMessage("error", "Delete wallet unsuccessfully", "Your wallet was not deleted");
		}
		try {
			returnMessage = mapper.writeValueAsString(message);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnMessage;
	}*/

	public String deleteWallet(int walletId, String email) {
	    String ajaxResponse = "";
	    AjaxMessage message;
//	    User user = userDao.getUserByEmail(email);
	    Wallet wallet = walletDao.getWalletByWalletId(walletId);
	    if (wallet == null){
	    	message = new AjaxMessage("error", "Ví không tồn tại", "Bạn đã bị xóa khỏi ví hoặc ví không tồn tại");
		}
	    else if (isWalletOwner(wallet, email)) {
            Set<User> users = wallet.getListUser();
            boolean isNoUserInWalletAfterDelete = users.size() == 1;

	        if (isNoUserInWalletAfterDelete) {
	            walletDao.deleteWallet(wallet);
	            message = new AjaxMessage("success", "Đã xóa", "Ví của bạn đã được xóa");
            }
	        else {
                users.removeIf(u -> u.getEmail().equals(email));
                if (walletDao.updateWallet(wallet)) {
					message = new AjaxMessage("success", "Đã xóa", "Bạn đã bị xóa khỏi ví này");
                }
                else {
                	message = new AjaxMessage("error", "Lỗi", "Có lỗi xảy ra, hãy thử lại");
				}
            }
        }
	    else {
	    	message = new AjaxMessage("error", "Lỗi", "Bạn không sở hữu ví này");
		}
	    try {
	    	ajaxResponse = new ObjectMapper().writeValueAsString(message);
		}
	    catch (Exception ex) {
	    	ex.printStackTrace();
		}
	    return ajaxResponse;
    }

    public String addWallet(String walletName, Currency currency, User user) {
	    String ajaxResponse = "";
	    AjaxMessage message;
	    Wallet wallet = new Wallet(walletName, currency, user);

	    if (walletDao.addWallet(wallet)) {
	    	HashMap<String, String> map = new HashMap<>();
	    	map.put("walletId", String.valueOf(wallet.getWalletId()));
	    	map.put("walletName", walletName);
	    	map.put("createdDate", new SimpleDateFormat("yyyy-MM-dd").format(wallet.getCreatedDate()));
	        message = new AjaxMessage("success", "Đã thêm", "Ví " + walletName + " đã được thêm vào danh sách ví của bạn", map);
        }
	    else {
	        message = new AjaxMessage("error", "Có lỗi xảy ra", "Không thể xóa ví, hãy thử lại!");
        }

	    try {
	        ajaxResponse = new ObjectMapper().writeValueAsString(message);
        }
	    catch (Exception ex) {
	        ex.printStackTrace();
        }

	    return ajaxResponse;
    }

	public String addSharedUserIntoWallet(int walletId, String ownerEmail, String sharedUserEmail) {
		String ajaxResponse = "";
		AjaxMessage message;
		User sharedUser = userDao.getUserByEmail(sharedUserEmail);
		if (sharedUser != null) {
			Wallet wallet = walletDao.getWalletByWalletId(walletId);
			if (wallet == null) {
				message = new AjaxMessage("error", "Ví không tồn tại", "Bạn đã bị xóa khỏi ví hoặc ví không tồn tại");
			}
			else if (isWalletOwner(wallet, sharedUserEmail)) {
				message = new AjaxMessage("error", "Lỗi", "Người này đã nằm trong danh sách người dùng chung");
			}
			else if (!isWalletOwner(wallet, ownerEmail)) {
				message = new AjaxMessage("error", "Lỗi", "Bạn không được phép thêm người dùng chung vào ví này");
			}
			else {
				wallet.getListUser().add(sharedUser);
				wallet.setWalletType(WalletType.shared);
				if (walletDao.updateWallet(wallet)) {
					Map<String, String> data = new HashMap<>();
					data.put("sharedUserId", String.valueOf(sharedUser.getUserId()));
					data.put("sharedUserName", sharedUser.getName());
					message = new AjaxMessage("success", "Đã thêm " + sharedUser.getName() + " vào ví này", sharedUser.getName() + "được thêm thành công vào ví này", data);
				}
				else {
					message = new AjaxMessage("error", "Có lỗi xảy ra", "Hãy thử lại");
				}
			}
		}
		else {
			message = new AjaxMessage("error", "Email " + sharedUserEmail + " không tồn tại", "Please try again");
		}

		try {
			ajaxResponse = new ObjectMapper().writeValueAsString(message);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return ajaxResponse;
	}

	public String deleteSharedUserFromWallet(int walletId, String ownerEmail, int sharedUserId) {
		String ajaxResponse = "";
		AjaxMessage message;
		Wallet wallet = walletDao.getWalletByWalletId(walletId);
		if (isWalletOwner(wallet, ownerEmail)) {
			if(isWalletOwner(wallet, sharedUserId)) {
				Set<User> users = wallet.getListUser();
//				users.removeIf(user -> user.getUserId() == sharedUserId);
				String sharedUserName = "";
				Iterator<User> iterator = users.iterator();
				while(iterator.hasNext()) {
					User user = iterator.next();
					if (user.getUserId() == sharedUserId) {
						sharedUserName = user.getName();
						iterator.remove();
						break;
					}
				}

				boolean isIntoPersonalWallet = users.size() == 1;
				if (isIntoPersonalWallet) {
				    wallet.setWalletType(WalletType.personal);
                }

				if (walletDao.updateWallet(wallet)) {
					message = new AjaxMessage("success", "Đã xóa " + sharedUserName, "Xóa thành công " + sharedUserName + " khỏi ví này");
				}
				else {
					message = new AjaxMessage("error", "Có lỗi xảy ra", "Hãy thử lại");
				}
			}
			else {
				message = new AjaxMessage("error", "Người dùng này không sở hữu ví này", "Hãy thử lại");
			}
		}
		else {
			message = new AjaxMessage("error", "Lỗi", "Bạn không được phép xóa người dùng chung khỏi ví này");
		}

		try {
			ajaxResponse = new ObjectMapper().writeValueAsString(message);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return ajaxResponse;
	}

	private boolean isWalletOwner(Wallet wallet, String ownerEnail) {
		boolean isOwner = false;
		Set<User> userList = wallet.getListUser();
		for (User user: userList) {
			if (user.getEmail().equals(ownerEnail)) {
				isOwner = true;
			}
		}
		return isOwner;
	}

	private boolean isWalletOwner(Wallet wallet, int ownerId) {
		boolean isOwner = false;
		Set<User> userList = wallet.getListUser();
		for (User user: userList) {
			if (user.getUserId() == ownerId) {
				isOwner = true;
			}
		}
		return isOwner;
	}

}
