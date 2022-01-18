package com.ozansaribal.n11bootcamp.service.entityService;

import com.ozansaribal.n11bootcamp.dao.DebtDao;
import com.ozansaribal.n11bootcamp.entity.Debt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class DebtEntityService extends BaseEntityService<Debt, DebtDao> {

    public DebtEntityService(DebtDao dao) {
        super(dao);
    }

    public Debt findAllByDatesBetween(Date firstDate, Date secondDate){
        return getDao().findAllByDatesBetween(firstDate,secondDate);
    }

    public List<Debt> findAllByMemberIdOrderByIdAndRestAmountGreaterThan(Long memberId, Long restAmount){
        return getDao().findAllByMemberIdOrderByIdAndRestAmountGreaterThan(memberId, restAmount);
    }

    public List<Debt> findAllByMemberIdOrderByIdAndDueDateBeforeAndRestAmountGreaterThan(Long memberId, Date date, Long restAmount){
        return getDao().findAllByMemberIdOrderByIdAndDueDateBeforeAndRestAmountGreaterThan(memberId, date, restAmount);
    }

}
