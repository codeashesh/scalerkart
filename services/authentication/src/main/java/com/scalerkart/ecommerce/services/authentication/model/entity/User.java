package com.scalerkart.ecommerce.services.authentication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "unique_username", columnNames = "username"),
        @UniqueConstraint(name = "unique_email", columnNames = "email"),
        @UniqueConstraint(name = "unique_phone", columnNames = "phone_number")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @NaturalId
    @Column(name = "email")
    private String email;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "age", nullable = false)
    private String age;

    @Column(name = "phone_number", unique = true)
    private String phone;

    @Lob
    @Column(name = "image_url")
    private String avatar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}
