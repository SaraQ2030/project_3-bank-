package com.example.project_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Employee {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "the password cannot be empty ")
    @Length(min = 6,message = "the password should be at least 6 char")
    //  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$" ,message = "the password should be letter and numbers")
    private String password;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "email cannot be empty")
    @Email
    @Column(columnDefinition = "varchar(20) not null unique ")
    private String email;
    @NotEmpty(message = "position cannot be empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String position;
    @NotNull(message = "salary cannot be empty")
    @PositiveOrZero(message = "the salary must be positive decimal number or zero")
    @Column(columnDefinition = "double not null ")
    private Double salary;


    @OneToOne
    @JsonIgnore
    @JoinColumn
    private User users;


}
