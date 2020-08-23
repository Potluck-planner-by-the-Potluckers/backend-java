package com.buildweek.unit4javabuild.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private LocalDate date;

    @Column(nullable = false)
    private String time;

    @OneToMany(mappedBy = "potluck",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("potluck")
    private Set<FoodItem> fooditems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "attendeeid")
    @JsonIgnoreProperties("potluck")
    private Attendee attendee;

    public Potluck()
    {
    }

    public Potluck(String name,
                   String description,
                   String location,
                   LocalDate date,
                   String time,
                   Set<FoodItem> fooditems,
                   Attendee attendee)
    {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.fooditems = fooditems;
        this.attendee = attendee;
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

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate time)
    {
        this.date = time;
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

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }
}
