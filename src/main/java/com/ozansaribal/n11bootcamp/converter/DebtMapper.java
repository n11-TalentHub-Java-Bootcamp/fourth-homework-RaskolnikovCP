package com.ozansaribal.n11bootcamp.converter;

import com.ozansaribal.n11bootcamp.dto.DebtDto;
import com.ozansaribal.n11bootcamp.dto.DebtSaveRequestDto;
import com.ozansaribal.n11bootcamp.entity.Debt;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebtMapper {

    DebtMapper INSTANCE = Mappers.getMapper(DebtMapper.class);

    Debt convertDebtSaveRequestDtoToDebt(DebtSaveRequestDto debtSaveRequestDto);

    DebtDto convertDebtToDebtDto(Debt debt);

    List<DebtDto> convertDebtListToDebtDtoList(List<Debt> debtList);

}
