package com.myblog.myblog11.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;


//use @data from lambok automatically write getters and setters
//@AllArgsConstructor uses for create auto constroctors from lambok
//@NoArgsConstructor no arguments constroctors from lambok
@Data
@Entity
@Table(name="posts")
@AllArgsConstructor
@NoArgsConstructor
public class post {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String title;
    private String description;
    private String content;
}
