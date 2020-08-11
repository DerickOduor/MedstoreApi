package com.derick.dto.chat;

public class SendNotification {
    private String to;
    private String collapse_key="type_a";
    private PushNotification notification;
    private PushNotificationData data;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCollapse_key() {
        return collapse_key;
    }

    public void setCollapse_key(String collapse_key) {
        this.collapse_key = collapse_key;
    }

    public PushNotification getNotification() {
        return notification;
    }

    public void setNotification(PushNotification notification) {
        this.notification = notification;
    }

    public PushNotificationData getData() {
        return data;
    }

    public void setData(PushNotificationData data) {
        this.data = data;
    }
}
