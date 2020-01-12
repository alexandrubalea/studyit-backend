package com.ubbdevs.studyit.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private Long id;

    private String number;
    private String indoorDirections;

    @ManyToOne
    private Location location;
}
