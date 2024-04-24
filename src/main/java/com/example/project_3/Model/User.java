package com.example.project_3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Length(min = 4,max = 10,message = "minimum length 4 and max is 10")
    @NotEmpty(message = "username cannot be empty")
    @Column(columnDefinition = "varchar(10) not null unique ")
    private String username;
//     @Length(min = 6,message = "minimum length 6")
//    @NotEmpty(message = "password cannot be empty")
//    @Column(columnDefinition = "varchar(15) not null ")
    private String password;
    @NotEmpty(message = "name cannot be empty")
    @Length(min = 2,max = 20,message = "minimum length 2 and max is 20")
    private String name;
   // @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "(CUSTOMER|EMPLOYEE|ADMIN)" ,message = "the role should be customer OR employee OR  admin")
    @Column(columnDefinition = "varchar(9) not null ")//check (role='CUSTOMER' or role='EMPLOYEE' or role='ADMIN')
    private String role;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "users")
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
