package org.ict.mensainfoservice.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private LocalDateTime date;
    private String text;
    private String heading;

    public Comment(){}

    public Comment(String heading, String text, String username){
        this.heading = heading;
        this.text = text;
        this.username = username;
        //this.id = UUID.randomUUID().toString();
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
