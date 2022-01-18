package com.ozansaribal.n11bootcamp.service;

import com.ozansaribal.n11bootcamp.converter.CollectingMapper;
import com.ozansaribal.n11bootcamp.converter.DebtMapper;
import com.ozansaribal.n11bootcamp.dto.CollectingDto;
import com.ozansaribal.n11bootcamp.dto.CollectingSaveRequestDto;
import com.ozansaribal.n11bootcamp.dto.DebtSaveRequestDto;
import com.ozansaribal.n11bootcamp.entity.Collecting;
import com.ozansaribal.n11bootcamp.entity.Debt;
import com.ozansaribal.n11bootcamp.enums.EnumDebtType;
import com.ozansaribal.n11bootcamp.service.entityService.CollectingEntityService;
import com.ozansaribal.n11bootcamp.service.entityService.DebtEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CollectingService {

    private CollectingEntityService collectingEntityService;

    private DebtEntityService debtEntityService;

    private DebtService debtService;

    private final static Date lateFeeDate = new Date(2018-1900,1,1);

    public CollectingDto saveCollecting(CollectingSaveRequestDto collectingSaveRequestDto, DebtSaveRequestDto debtSaveRequestDto, Long debtId){

        Collecting collecting = CollectingMapper.INSTANCE.convertCollectingSaveRequestDtoToCollecting(collectingSaveRequestDto);

        Debt debt = DebtMapper.INSTANCE.convertDebtSaveRequestDtoToDebt(debtSaveRequestDto);

        debt.setId(debtId);

        collecting.setDebtId(debt);

        debt = debtService.findById(debtId);

        Date date = new Date();

        collecting.setCollectingDate(date);

        debt.setDebtPaidDate(date);

        collecting.setDebtId(debt);

        double lateFeeDebt = 0.0;

        if(collecting.getCollectingDate().after(collecting.getDueDate())){

            lateFeeDebt = calculateLateFeeDebt(debt.getId());

            collecting.setRestDebt(0.0);

            collecting.setLateFeeDebt(lateFeeDebt);

            collecting.setDebtId(debt);

            collecting = collectingEntityService.save(collecting);

            debt.setRestAmount(0.0);

            debt.setDebtType(EnumDebtType.LATE_FEE);

            debt = debtEntityService.save(debt);

        }
        else if(collecting.getCollectingDate().before(collecting.getDueDate())){

            collecting.setRestDebt(0.0);

            collecting.setDebtId(debt);

            collecting = collectingEntityService.save(collecting);

            debt.setRestAmount(0.0);

            debt.setDebtType(EnumDebtType.NORMAL);

            debt = debtEntityService.save(debt);

        }

        CollectingDto collectingDto = CollectingMapper.INSTANCE.convertCollectingToCollectingDto(collecting);

        return collectingDto;

    }

    public double calculateLateFeeDebt(Long debtId){

        Debt debt = debtService.findById(debtId);

        double lateFeeDebt = 0.0;

        double daysBetweenPaidAndLateFee = (double) ChronoUnit.DAYS.between(lateFeeDate.toInstant(), debt.getDebtPaidDate().toInstant());

        double daysBetweenLateFeeAndBorrowed = (double) ChronoUnit.DAYS.between(debt.getDebtBorrowedDate().toInstant(),lateFeeDate.toInstant());

        if(debt.getDebtPaidDate().after(debt.getDueDate())){

            lateFeeDebt = (daysBetweenPaidAndLateFee * 2) + (daysBetweenLateFeeAndBorrowed * 1.5);

        }
        else if(debt.getDebtPaidDate().before(debt.getDueDate())){

            lateFeeDebt = (daysBetweenLateFeeAndBorrowed * 1.5);

        }

        return lateFeeDebt;

    }

    public List<CollectingDto> findAll() {

        List<Collecting> collectingList = collectingEntityService.findAll();

        List<CollectingDto> collectingDtoList = CollectingMapper.INSTANCE.convertCollectingListToCollectingDtoList(collectingList);

        return collectingDtoList;

    }

    public CollectingDto findAllByDatesBetween(Date firstDate, Date secondDate) {

        Collecting collecting = collectingEntityService.findAllByDatesBetween(firstDate, secondDate);

        if (collecting == null){
            throw new RuntimeException("There is no collecting record within these dates!");
        }

        CollectingDto collectingDto = CollectingMapper.INSTANCE.convertCollectingToCollectingDto(collecting);

        return collectingDto;

    }

    public List<CollectingDto> findAllByMemberIdOrderById(Long memberId) {

        List<Collecting> collectingList = collectingEntityService.findAllByMemberIdOrderById(memberId);

        List<CollectingDto> collectingDtoList = CollectingMapper.INSTANCE.convertCollectingListToCollectingDtoList(collectingList);

        return collectingDtoList;

    }

    public double findSumOfAllLateFeeDebtsByMemberId(Long memberId){

        List<Collecting> collectingList = collectingEntityService.findAllByMemberIdOrderById(memberId);

        List<CollectingDto> collectingDtoList = CollectingMapper.INSTANCE.convertCollectingListToCollectingDtoList(collectingList);

        double result = 0.0;

        for(CollectingDto collectingDto: collectingDtoList){

            result += collectingDto.getLateFeeDebt();

        }

        return result;

    }

   /*
    public Collecting getCollectingByDebt(Debt debt) {

        Collecting collecting;

        collecting = findCollectingById(debt.getId());

        CollectingSaveRequestDto collectingSaveRequestDto = CollectingMapper.INSTANCE
                .convertCollectingToCollectingSaveRequestDto(collecting);

        return collectingSaveRequestDto;

    }*/
/*
    public Collecting findById(Long id) {

        Collecting collecting = findCollectingById(id);

        return collecting;

    }*/

    private Collecting findCollectingById(Long id) {

        Collecting collecting;

        Optional<Collecting> optionalCollecting = collectingEntityService.findById(id);

        if (optionalCollecting.isPresent()){

            collecting = optionalCollecting.get();

        } else {

            throw new RuntimeException("Collecting not found!");

        }

        return collecting;

    }

}
