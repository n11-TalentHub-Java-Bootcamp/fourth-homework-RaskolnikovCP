package com.ozansaribal.n11bootcamp.service.entityService;

import com.ozansaribal.n11bootcamp.dao.CollectingDao;
import com.ozansaribal.n11bootcamp.entity.Collecting;

import java.util.Date;
import java.util.List;

public class CollectingEntityService extends BaseEntityService<Collecting, CollectingDao>{

    public CollectingEntityService(CollectingDao dao) {
        super(dao);
    }

    public Collecting findAllByDatesBetween(Date firstDate, Date secondDate){

        return getDao().findAllByDatesBetween(firstDate,secondDate);

    }

    public List<Collecting> findAllByMemberIdOrderById(Long memberId){

        return getDao().findAllByMemberIdOrderById(memberId);

    }

}
