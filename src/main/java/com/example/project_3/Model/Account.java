package com.example.project_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Account number cannot be empty")
    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}", message = "The account number should be in the format xxxx-xxxx-xxxx-xxxx")
    @Column(columnDefinition = "int not null ")
    private Integer AccountNumber;
    @NotNull(message = "balance cannot be empty")
    @Column(columnDefinition = "double not null ")
     private Double balance;
    @AssertFalse
    @Column(columnDefinition = "boolean default false")
    private Boolean isActive;

   @ManyToOne
   @JsonIgnore
   @JoinColumn(name = "customer_id" , referencedColumnName = "user_id")
    private Customer customer;
}
