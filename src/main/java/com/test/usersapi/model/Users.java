package com.test.usersapi.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="s_users", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="zipcode")
    private String zipcode;

    @Column(name="country")
    private String country;

    public static Users mapUser(User user) {
        if(user != null) {
            Users users = new Users();
            if(user.getId() != null)
                users.setId(user.getId().longValue());
            users.setName(user.getName());
            users.setSurname(user.getSurname());
            users.setEmail(user.getEmail());
            users.setPassword(user.getPassword());
            users.setAddress(user.getAddress());
            users.setCity(user.getCity());
            users.setZipcode(user.getZipcode());
            users.setCountry(user.getCountry());

            return users;
        }

        return null;
    }

    public static User mapUsers(Users users) {
        if(users != null) {
            User user = new User();
            user.setId(BigDecimal.valueOf(users.getId()));
            user.setName(users.getName());
            user.setSurname(users.getSurname());
            user.setEmail(users.getEmail());
            user.setPassword(users.getPassword());
            user.setAddress(users.getAddress());
            user.setCity(users.getCity());
            user.setZipcode(users.getZipcode());
            user.setCountry(users.getCountry());

            return user;
        }

        return null;
    }
}
