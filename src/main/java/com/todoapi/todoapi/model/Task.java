package com.todoapi.todoapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="todo")
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

}
