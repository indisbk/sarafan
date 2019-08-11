package com.learning.sarafan.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "person")
@Data
public class User {
    @Id
    private String id;
    private String name;
    private String avatar;
    private String email;
    private String gender;
    private String locale;
    private LocalDateTime lastVisit;
}
