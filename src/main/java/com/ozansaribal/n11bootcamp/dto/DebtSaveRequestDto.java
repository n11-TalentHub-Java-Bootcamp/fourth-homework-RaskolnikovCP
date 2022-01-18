package com.ozansaribal.n11bootcamp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DebtSaveRequestDto {


    private Long id;

    private Double mainAmount;

    private Double restAmount;

    private Date dueDate;

    private Date debtBorrowedDate;

    private Date debtPaidDate;

}
