package com.ozansaribal.n11bootcamp.dao;

import com.ozansaribal.n11bootcamp.entity.Collecting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CollectingDao extends JpaRepository<Collecting, Long> {

    //@Query("select collecting from Collecting collecting where collecting.collectingDate > :firstDate and collecting.collectingDate < :secondDate")
    Collecting findAllByDatesBetween(Date firstDate, Date secondDate);

    List<Collecting> findAllByMemberIdOrderById(Long memberId);

}
