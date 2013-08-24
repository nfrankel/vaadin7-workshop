package ch.frankel.duchessswiss.vaadin.ui;

import java.util.Date;

public class Message {

    private String text;
    private String sender;
    private Date time;

    public Message(String sender, String text, Date time) {

        this.sender = sender;
        this.text = text;
        this.time = time;
    }

    public String getSender() {

        return sender;
    }

    public String getText() {

        return text;
    }

    public Date getTime() {

        return time;
    }
}
