package org.ict.mensainfoservice.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long mealId;
    private Long customUserId;

    private LocalDateTime date;
    private String text;
    private String heading;

    public Comment(){}

    public Comment(String heading, String text, Long mealId, Long customUserId){
        this.heading = heading;
        this.text = text;
        this.date = LocalDateTime.now();
        this.mealId = mealId;
        this.customUserId = customUserId;
    }

    public Long getCustomUserId() {
        return customUserId;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", mealId=" + mealId +
                ", customUserId=" + customUserId +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", heading='" + heading + '\'' +
                '}';
    }
}
