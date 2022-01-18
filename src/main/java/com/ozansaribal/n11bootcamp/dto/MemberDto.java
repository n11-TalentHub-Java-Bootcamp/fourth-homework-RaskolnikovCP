package com.ozansaribal.n11bootcamp.dto;

import lombok.Data;

@Data
public class MemberDto {

    private Long id;

    private String name;

    private String nickname;

    private String username;

    private String password;

    private String imageUrl;

}
