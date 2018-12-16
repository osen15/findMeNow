package com.findMeNow.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    @SequenceGenerator(name = "POST_SEQ", sequenceName = "POST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ")
    @Column(name = "ID")
    private Long id;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "DATE_POSTED")
    private Date datePosted;
    @ManyToOne
    @JoinColumn(name = "USER_POSTED_ID", nullable = false)
    private User userPosted;

    //TODO
    //levels permissions
    //TODO
    //comments

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public User getUserPosted() {
        return userPosted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public void setUserPosted(User userPosted) {
        this.userPosted = userPosted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(message, post.message) &&
                Objects.equals(datePosted, post.datePosted) &&
                Objects.equals(userPosted, post.userPosted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, datePosted, userPosted);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", datePosted=" + datePosted +
                ", userPosted=" + userPosted +
                '}';
    }
}
