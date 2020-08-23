package com.buildweek.unit4javabuild.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attendee")
public class Attendee extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long attendeeid;

    private boolean isGoing = false;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("attendees")
    private User user;

    @OneToMany(mappedBy = "attendee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("attendee")
    private Set<Potluck> potlucks = new HashSet<>();

    public Attendee()
    {
    }

    public Attendee(User user, Set<Potluck> potlucks) {
        this.user = user;
        this.potlucks = potlucks;
    }

    public long getAttendeeid()
    {
        return attendeeid;
    }

    public void setAttendeeid(long attendeeid) {
        this.attendeeid = attendeeid;
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

    public Set<Potluck> getPotlucks() {
        return potlucks;
    }

    public void setPotlucks(Set<Potluck> potlucks) {
        this.potlucks = potlucks;
    }
}
