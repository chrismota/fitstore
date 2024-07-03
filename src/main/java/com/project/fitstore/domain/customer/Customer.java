package com.project.fitstore.domain.customer;

import com.project.fitstore.dtos.customer.CreateCustomerDto;
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

    public Customer(CreateCustomerDto createCustomerDto){
    this.name = createCustomerDto.name();
    this.phoneNumber = createCustomerDto.phoneNumber();
    this.address = createCustomerDto.address();
    this.cpf = createCustomerDto.cpf();
    this.email = createCustomerDto.email();
    this.password = createCustomerDto.password();
    }
}
