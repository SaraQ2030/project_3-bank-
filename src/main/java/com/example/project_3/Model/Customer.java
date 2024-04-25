package com.example.project_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Customer {
    @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "the password cannot be empty ")
    @Length(min = 6,message = "the password should be at least 6 char")
    // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$" ,message = "the password should be letter and numbers")
    private String password;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "email cannot be empty")
    @Email
    @Column(columnDefinition = "varchar(20) not null unique ")
    private String email;
    @NotEmpty(message = "phoneNumber cannot be empty")
    //  @Pattern(regexp = "^05\\d{8}$"  , message = "Number should start with 05xxxxxxxx and contain 10 numbers")
    @Column(columnDefinition = "varchar (10) not null")
    private String phoneNumber;


    @OneToOne
    @JsonIgnore
    @MapsId
    private User user;


    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "customer")
    private Set<Account> accounts;

}
