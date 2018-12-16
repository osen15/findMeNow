package com.findMeNow.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Component
@Entity
@Table(name = "MESSAGES")
public class Message {
    @Id
    @SequenceGenerator(name = "MESSAGE_SEQ", sequenceName = "MESSAGE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_SEQ")
    @Column(name = "ID")
    private Long id;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "DATE_SEND")
    private Date dateSend;
    @Column(name = "DATE_READ")
    private Date dateRead;
    @ManyToOne
    @JoinColumn(name = "USER_FROM_ID", nullable = false)
    private User userFrom;
    @ManyToOne
    @JoinColumn(name = "USER_TO_ID", nullable = false)
    private User userTo;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getDateSent() {
        return dateSend;
    }

    public Date getDateRead() {
        return dateRead;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateSent(Date dateSent) {
        this.dateSend = dateSent;
    }

    public void setDateRead(Date dateRead) {
        this.dateRead = dateRead;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(text, message.text) &&
                Objects.equals(dateSend, message.dateSend) &&
                Objects.equals(dateRead, message.dateRead) &&
                Objects.equals(userFrom, message.userFrom) &&
                Objects.equals(userTo, message.userTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, dateSend, dateRead, userFrom, userTo);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dateSent=" + dateSend +
                ", dateRead=" + dateRead +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                '}';
    }
}
