package com.buildweek.unit4javabuild.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "foodItem")
public class FoodItem extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long foodid;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String attendeename;

    @ManyToOne
    @JoinColumn(name = "potluckid")
    @JsonIgnoreProperties("fooditems")
    private Potluck potluck;

    public FoodItem() {
    }

    public FoodItem(String name,
                    String type,
                    String attendeename,
                    Potluck potluck)
    {
        this.name = name;
        this.type = type;
        this.attendeename = attendeename;
        this.potluck = potluck;
    }

    public long getFoodid() {
        return foodid;
    }

    public void setFoodid(long foodid) {
        this.foodid = foodid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttendeename() {
        return attendeename;
    }

    public void setAttendeename(String attendeename) {
        this.attendeename = attendeename;
    }

    public Potluck getPotluck() {
        return potluck;
    }

    public void setPotluck(Potluck potluck) {
        this.potluck = potluck;
    }
}
