package com.ozansaribal.n11bootcamp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String nickname;

    private String username;

    private String password;

    private String imageUrl;

}
