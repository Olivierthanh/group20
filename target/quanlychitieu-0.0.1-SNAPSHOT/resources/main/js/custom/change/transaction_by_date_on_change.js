$("#view-transaction-by-date").attr("max", getTodayDate());

$("#view-transaction-by-date").on("change", event => {
   let date = $(event.target).val();
   let activeTransactionNode = $(".transaction-day:not(.d-none)");
   let targetTransactionNode = $(`.transaction-day[transaction-date='${date}']`);
   if (targetTransactionNode.length > 0) {
        activeTransactionNode.toggleClass('d-none');
        targetTransactionNode.toggleClass('d-none');
   }
   else {
       swal(`Không có giao dịch nào vào ngày ${date}`);
   }
});