package com.ubbdevs.studyit.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    private Long id;

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
}
