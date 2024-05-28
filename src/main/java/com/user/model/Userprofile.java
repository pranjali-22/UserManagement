
package com.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Userprofile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private Date dob;
    private String phone;
}
