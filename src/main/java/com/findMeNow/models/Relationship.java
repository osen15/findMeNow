package com.findMeNow.models;

import com.findMeNow.enums.RelationshipStatus;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
@Component
@Entity
@Table(name = "RELATIONSHIPS")
public class Relationship implements Serializable {

    @Id
    @SequenceGenerator(name = "RELATIONSHIP_SEQ", sequenceName = "RELATIONSHIP_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RELATIONSHIP_SEQ")
    @Column(name = "RELATIONSHIPS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FROM_ID")
    private User userFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_TO_ID")
    private User userTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private RelationshipStatus relationshipStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public RelationshipStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "id=" + id +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", relationshipStatus=" + relationshipStatus +
                '}';
    }
}