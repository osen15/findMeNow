package com.findMeNow.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASS_WORD")
    private String password;
    //TODO from existed data
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "CITY")
    private String city;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "DATE_REGISTERED")
    private Date dateRegistered;
    @Column(name = "DATE_LAST_ACTIVE")
    private Date dateLastActive;
    //TODO enum
    @Column(name = "RELATIONSHIP_STATUS")
    private String relationshipStatus;
    @Column(name = "RELIGION")
    private String religion;
    //TODO from existed data
    @Column(name = "SCHOOL")
    private String school;
    @Column(name = "UNIVERSITY")
    private String university;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userFrom")
    private List<Message> messagesSent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTo")
    private List<Message> messagesReceived;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public Date getDateLastActive() {
        return dateLastActive;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public String getReligion() {
        return religion;
    }

    public String getSchool() {
        return school;
    }

    public String getUniversity() {
        return university;
    }

    public List<Message> getMessagesSent() {
        return messagesSent;
    }

    public List<Message> getMessagesReceived() {
        return messagesReceived;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDateregistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public void setDateLastActive(Date dateLastActive) {
        this.dateLastActive = dateLastActive;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setMessagesSent(List<Message> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public void setMessagesReceived(List<Message> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", dateRegistered=" + dateRegistered +
                ", dateLastActive=" + dateLastActive +
                ", relationshipStatus='" + relationshipStatus + '\'' +
                ", religion='" + religion + '\'' +
                ", school='" + school + '\'' +
                ", university='" + university + '\'' +
                '}';
    }
}