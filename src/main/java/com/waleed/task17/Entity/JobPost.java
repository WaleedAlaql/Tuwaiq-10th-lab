package com.waleed.task17.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_posts")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 5, message = "Title length must be more than 4 characters")
    @Column(nullable = false)
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotEmpty(message = "Location cannot be empty")
    @Column(nullable = false)
    private String location;

    @NotNull(message = "Salary cannot be null")
    @PositiveOrZero(message = "Salary must be a non-negative number")
    @Column(nullable = false)
    private Double salary;

    private LocalDate postingDate;
}