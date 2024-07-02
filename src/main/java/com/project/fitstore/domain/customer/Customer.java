package com.project.fitstore.domain.customer;

import com.project.fitstore.dtos.customer.CustomerDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String phoneNumber;
    private String address;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String password;

    public Customer(CustomerDto customerDto){
    this.name = customerDto.name();
    this.phoneNumber = customerDto.phoneNumber();
    this.address = customerDto.address();
    this.cpf = customerDto.cpf();
    this.email = customerDto.email();
    this.password = customerDto.password();
    }
}
