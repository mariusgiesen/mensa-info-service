package org.ict.mensainfoservice.entity;


import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {

    private String id;
    private String username;
    private LocalDateTime date;
    private String text;
    private String heading;

    public Comment(String heading, String text, String username){
        this.heading = heading;
        this.text = text;
        this.username = username;
        this.id = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getHeading() {
        return heading;
    }
}
