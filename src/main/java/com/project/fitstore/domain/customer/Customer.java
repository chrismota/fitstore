package com.project.fitstore.domain.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
