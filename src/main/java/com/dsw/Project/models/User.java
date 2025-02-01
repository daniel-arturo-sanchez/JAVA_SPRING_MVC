package com.dsw.Project.models;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, columnDefinition = "")
    private String name;

    @Email
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    private String password;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "id_user") },
            inverseJoinColumns = { @JoinColumn(name = "id_role") }
    )
    private List<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

//    @PostConstruct
//    public void init() {
////        user@myikea.com → role USER
////        manager@myikea.com → role MANAGER
////        admin1@myikea.com → role ADMIN
////        admin2@myikea.com → este ha de tener 2 roles, ADMIN y MANAGER
//    }
}
