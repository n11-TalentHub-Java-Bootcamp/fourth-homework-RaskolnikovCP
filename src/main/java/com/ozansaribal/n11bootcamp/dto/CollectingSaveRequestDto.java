package com.ozansaribal.n11bootcamp.dto;

import com.ozansaribal.n11bootcamp.entity.Debt;
import lombok.Data;

import java.util.Date;

@Data
public class CollectingSaveRequestDto {

    private Long id;

    private Debt debtId;

    private Double lateFeeDebt;

    private Double mainDebt;

    private Double restDebt;

    private Date dueDate;

    private Date collectingDate;

}
