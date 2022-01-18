package com.ozansaribal.n11bootcamp.dto;

import com.ozansaribal.n11bootcamp.enums.EnumDebtType;
import lombok.Data;

import java.util.Date;

@Data
public class DebtDto {

    private Long id;

    private Double mainAmount;

    private Double restAmount;

    private EnumDebtType debtType;

    private Date dueDate;

    private Date debtBorrowedDate;

    private Date debtPaidDate;

}
