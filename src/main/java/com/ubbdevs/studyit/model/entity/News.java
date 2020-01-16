package com.ubbdevs.studyit.model.entity;

import com.ubbdevs.studyit.model.entity.Subject;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime creationDate;

    @ManyToOne
    private Subject subject;

    private Long professorId;
}
