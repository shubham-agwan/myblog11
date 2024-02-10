package com.myblog.myblog11.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "user",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
        }
      )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String name;
    private  String email;
    private String username;
    private String password;
        //FetchType.EAGER load all the required table
    //FetchType.LAZY load only required table
    @ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id" ,referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id" ,referencedColumnName = "id"))
    private Set<Role> Roles;
}
