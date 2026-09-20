package com.waleed.task17.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, message = "Length must be more than 4 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Must contain only characters (no numbers)")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Must be a valid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "Password cannot be null or empty")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Age cannot be null")
    @Min(value = 22, message = "Must be more than 21")
    @Column(nullable = false)
    private Integer age;

    @NotEmpty(message = "Role cannot be null or empty")
    @Pattern(regexp = "JOB_SEEKER|EMPLOYER", message = "Must be either 'JOB_SEEKER' or 'EMPLOYER' only")
    @Column(nullable = false)
    private String role;
}