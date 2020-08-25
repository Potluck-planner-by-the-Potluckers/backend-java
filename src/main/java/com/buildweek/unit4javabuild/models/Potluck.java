package com.buildweek.unit4javabuild.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "potluck")
public class Potluck
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long potluckid;

    @Column(nullable = false,
            unique = true)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("potlucks")
    private User user;

    @OneToMany(mappedBy = "potluck",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("potluck")
    private Set<FoodItem> fooditems = new HashSet<>();

    @OneToMany(mappedBy = "potluck",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("potluck")
    private Set<Attendee> attendees = new HashSet<>();

    public Potluck(String name,
                   String description,
                   String location,
                   String date,
                   String time,
                   User user)
    {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.user = user;
    }



    public Potluck()
    {
    }

    public long getPotluckid()
    {
        return potluckid;
    }

    public void setPotluckid(long potluckid)
    {
        this.potluckid = potluckid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<FoodItem> getFooditems()
    {
        return fooditems;
    }

    public void setFooditems(Set<FoodItem> foodItems)
    {
        this.fooditems = foodItems;
    }

    public Set<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<Attendee> attendees) {
        this.attendees = attendees;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
