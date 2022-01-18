package com.ozansaribal.n11bootcamp.entity;

import com.ozansaribal.n11bootcamp.enums.EnumDebtType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DEBT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Debt implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MEMBER",
            foreignKey = @ForeignKey(name = "FK_MEMBER_DEBT_ID")
    )
    private Member memberId;

    @Column(updatable = false, scale = 2)
    private Double mainAmount;

    @Column(scale = 2)
    private Double restAmount;

    private EnumDebtType debtType;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    private Date debtBorrowedDate;

    @Temporal(TemporalType.DATE)
    private Date debtPaidDate;

}
