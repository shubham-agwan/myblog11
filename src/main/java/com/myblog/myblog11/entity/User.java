package com.myblog.myblog11.entity;

import lombok.Data;

import javax.persistence.*;

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
}
