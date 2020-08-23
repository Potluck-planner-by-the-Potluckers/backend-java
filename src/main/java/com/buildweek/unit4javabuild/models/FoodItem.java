package com.buildweek.unit4javabuild.models;

import javax.persistence.*;

@Entity
@Table(name = "foodItems")
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

    public FoodItem() {
    }

    public FoodItem(String name, String type, String attendeename) {
        this.name = name;
        this.type = type;
        this.attendeename = attendeename;
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


}
