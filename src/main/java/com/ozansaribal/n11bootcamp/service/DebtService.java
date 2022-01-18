package com.ozansaribal.n11bootcamp.service;

import com.ozansaribal.n11bootcamp.converter.DebtMapper;
import com.ozansaribal.n11bootcamp.dto.DebtDto;
import com.ozansaribal.n11bootcamp.dto.DebtSaveRequestDto;
import com.ozansaribal.n11bootcamp.entity.Debt;
import com.ozansaribal.n11bootcamp.enums.EnumDebtType;
import com.ozansaribal.n11bootcamp.service.entityService.DebtEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class DebtService {

    private DebtEntityService debtEntityService;

    private CollectingService collectingService;

    private final static Date lateFeeDate = new Date(2018-1900,1,1);

    public Debt findById(Long id) {

        Debt debt = findDebtById(id);

        return debt;
    }

    public DebtDto saveDebt(DebtSaveRequestDto debtSaveRequestDto){

        Debt debt = DebtMapper.INSTANCE.convertDebtSaveRequestDtoToDebt(debtSaveRequestDto);

        EnumDebtType enumDebtType = debt.getDebtType();

        if(enumDebtType == EnumDebtType.NORMAL){

            debt = debtEntityService.save(debt);

        }
        else {

            throw new RuntimeException("Debt is not normal debt! Late Fee debts cannot be saved!");

        }

        DebtDto debtDto = DebtMapper.INSTANCE.convertDebtToDebtDto(debt);

        return debtDto;

    }

    public void delete(Long id) {

        Debt debt = findDebtById(id);

        debtEntityService.delete(debt);

    }

    private Debt findDebtById(Long id) {

        Debt debt;

        Optional<Debt> optionalDebt = debtEntityService.findById(id);

        if (optionalDebt.isPresent()){

            debt = optionalDebt.get();

        }
        else {

            throw new RuntimeException("Debt not found!");

        }

        return debt;

    }

    public DebtDto findAllByDatesBetween(Date firstDate, Date secondDate) {

        Debt debt = debtEntityService.findAllByDatesBetween(firstDate, secondDate);

        if (debt == null){
            throw new RuntimeException("There is no debt record within these dates!");
        }

        DebtDto debtDto = DebtMapper.INSTANCE.convertDebtToDebtDto(debt);

        return debtDto;
    }

    public List<DebtDto> findAllByMemberIdOrderByIdAndRestAmountGreaterThan(Long memberId, Long restAmount) {

        List<Debt> debtList = debtEntityService.findAllByMemberIdOrderByIdAndRestAmountGreaterThan(memberId, restAmount);

        List<DebtDto> debtDtoList = DebtMapper.INSTANCE.convertDebtListToDebtDtoList(debtList);

        return debtDtoList;

    }

    public List<DebtDto> findAllByMemberIdOrderByIdAndDueDateBeforeAndRestAmountGreaterThan(Long memberId, Date date, Long restAmount){

        List<Debt> lateFeeDebtList = debtEntityService.findAllByMemberIdOrderByIdAndDueDateBeforeAndRestAmountGreaterThan(memberId, date, restAmount);

        List<DebtDto> lateFeeDebtDtoList = DebtMapper.INSTANCE.convertDebtListToDebtDtoList(lateFeeDebtList);

        return lateFeeDebtDtoList;

    }

    public double findSumOfAllDebtsByMemberId(Long memberId, Long restAmount){

        List<Debt> debtList = debtEntityService.findAllByMemberIdOrderByIdAndRestAmountGreaterThan(memberId, restAmount);

        List<DebtDto> debtDtoList = DebtMapper.INSTANCE.convertDebtListToDebtDtoList(debtList);

        double result = 0;

        for(DebtDto debtDto : debtDtoList){

            result += debtDto.getMainAmount();

        }

        return result;

    }

    public double findSumOfAllLateFeeDebtsByMemberId(Long memberId, Date date, Long restAmount){

        List<Debt> debtList = debtEntityService.findAllByMemberIdOrderByIdAndDueDateBeforeAndRestAmountGreaterThan(memberId, date, restAmount);

        List<DebtDto> debtDtoList = DebtMapper.INSTANCE.convertDebtListToDebtDtoList(debtList);

        double result = 0;

        for(DebtDto debtDto : debtDtoList){

            result += debtDto.getMainAmount();

        }

        return result;

    }

    public double calculateInitialLateFeeDebt(Long memberId, Date date, Long restAmount){

        List<Debt> debtList = debtEntityService.findAllByMemberIdOrderByIdAndDueDateBeforeAndRestAmountGreaterThan(memberId, date, restAmount);

        List<DebtDto> debtDtoList = DebtMapper.INSTANCE.convertDebtListToDebtDtoList(debtList);

        double daysBetweenPaidAndLateFee = (double) ChronoUnit.DAYS.between(lateFeeDate.toInstant(), date.toInstant());

        double result = 0.0;

        double debtAmount = 0.0;

        for(DebtDto debtDto : debtDtoList){

            debtAmount = debtDto.getMainAmount();

            result += (debtAmount) + (daysBetweenPaidAndLateFee * 2);

            if(debtAmount < 1 && debtAmount != 0){

                result = 1;

            }

        }

        return result;

    }

}
