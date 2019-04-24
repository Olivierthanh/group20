package com.quanlychitieu.utils;

public class AjaxMessage {
    private String type;
    private String title;
    private String message;
    private String attachedData = "";

    public AjaxMessage() {

    }

    public AjaxMessage(String type, String title, String message) {
        this.type = type;
        this.title = title;
        this.message = message;
    }

    public AjaxMessage(String type, String title, String message, String attachedData) {
        this.type = type;
        this.title = title;
        this.message = message;
        this.attachedData = attachedData;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttachedData() {
        return attachedData;
    }

    public void setAttachedData(String attachedData) {
        this.attachedData = attachedData;
    }
}
