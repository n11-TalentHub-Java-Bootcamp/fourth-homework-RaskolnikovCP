package com.ozansaribal.n11bootcamp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COLLECTING")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collecting implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DEBT",
            foreignKey = @ForeignKey(name = "FK_COLLECTING_DEBT_ID")
    )
    private Debt debtId;

    @Column(scale = 2)
    private Double lateFeeDebt;

    @Column(scale = 2)
    private Double mainDebt;

    @Column(scale = 2)
    private Double restDebt;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    private Date collectingDate;

}
