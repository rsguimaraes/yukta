package com.yukta.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Length(min = 3)
    private String name;
    @NotNull
    @Column(unique = true)
    @Email(message = "Email inválido")
    private String email;
    @NotNull
    @Length(min = 8)
    private String password;
    @NotNull
    private String role;
}
