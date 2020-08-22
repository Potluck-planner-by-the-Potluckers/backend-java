package com.buildweek.unit4javabuild.models;

import javax.persistence.*;

@Entity
@Table(name = "attendees")
public class Attendee extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long attendeeid;

    private boolean isGoing = false;


}
