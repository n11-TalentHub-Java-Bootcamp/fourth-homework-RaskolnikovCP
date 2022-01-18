package com.ozansaribal.n11bootcamp.dao;

import com.ozansaribal.n11bootcamp.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DebtDao extends JpaRepository<Debt, Long> {

    //@Query("select debt from Debt debt where debt.debtPaidDate > :firstDate and debt.debtPaidDate < :secondDate")
    Debt findAllByDatesBetween(Date firstDate, Date secondDate);

    //@Query("select debt from Debt debt where debt.memberId = :memberId and debt.restAmount > 0")
    List<Debt> findAllByMemberIdOrderByIdAndRestAmountGreaterThan(Long memberId, Long restAmount);

    //@Query("select debt from Debt debt where debt.memberId = :memberId and debt.dueDate < date and debt.restAmount > 0")
    List<Debt> findAllByMemberIdOrderByIdAndDueDateBeforeAndRestAmountGreaterThan(Long memberId, Date date, Long restAmount);

    /*
    @Query("select member from Member member where member.nickname = :nickname")
    public List<Member> findByNickname(String nickname);

    @Query("select member from Member member where member.telephone = :telephone")
    public List<Member> findByTelephone(String telephone);

    @Query("delete from Member member where member.nickname = :nickname and member.telephone = :telephone")
    public void deleteByNicknameAndTelephone(String nickname, String telephone);*/

}
