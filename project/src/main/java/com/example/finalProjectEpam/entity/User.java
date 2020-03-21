package com.example.finalProjectEpam.entity;

import com.example.finalProjectEpam.entity.enums.RoleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Integer id;

    @Pattern(regexp = "[A-Za-z_0-9.]{2,20}")
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "FIRST_NAME_UKR")
    private String firstNameUkr;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "LAST_NAME_UKR")
    private String lastNameUkr;

    @Enumerated(EnumType.STRING)
    private RoleStatus role;

}
