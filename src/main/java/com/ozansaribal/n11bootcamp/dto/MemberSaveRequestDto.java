package com.ozansaribal.n11bootcamp.dto;

import lombok.Data;

@Data
public class MemberSaveRequestDto {

    private Long id;

    private Long memberDebtId;

    private String name;

    private String nickname;

}
