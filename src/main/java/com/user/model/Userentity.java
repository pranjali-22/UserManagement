package com.user.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
//
@Data
@Entity
public class Userentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private Date dob;
    private String phone;
}
