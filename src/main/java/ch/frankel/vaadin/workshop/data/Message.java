package ch.frankel.vaadin.workshop.data;

import java.util.Date;

public class Message {

    private String author;
    private String text;
    private Date timeStamp;

    public Message(String author, String text, Date timeStamp) {
        this.author = author;
        this.text = text;
        this.timeStamp = timeStamp;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
