package com.buildweek.unit4javabuild.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attendee")
public class Attendee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long attendeeid;

    @Column
    private String name;

    private boolean isGoing = false;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("attendees")
    private User user;

    @ManyToOne
    @JoinColumn(name = "potluckid")
    @JsonIgnoreProperties("attendees")
    private Potluck potluck;

    public Attendee()
    {
    }

    public Attendee(String name, User user, Potluck potluck) {
        this.name = name;
        this.user = user;
        this.potluck = potluck;
    }

    public long getAttendeeid()
    {
        return attendeeid;
    }

    public void setAttendeeid(long attendeeid) {
        this.attendeeid = attendeeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGoing() {
        return isGoing;
    }

    public void setGoing(boolean going) {
        isGoing = going;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Potluck getPotluck() {
        return potluck;
    }

    public void setPotluck(Potluck potluck) {
        this.potluck = potluck;
    }
}
