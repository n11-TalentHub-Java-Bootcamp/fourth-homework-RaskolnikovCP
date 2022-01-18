package com.ozansaribal.n11bootcamp.converter;

import com.ozansaribal.n11bootcamp.dto.CollectingDto;
import com.ozansaribal.n11bootcamp.dto.CollectingSaveRequestDto;
import com.ozansaribal.n11bootcamp.entity.Collecting;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectingMapper {

    CollectingMapper INSTANCE = Mappers.getMapper(CollectingMapper.class);

    CollectingDto convertCollectingToCollectingDto(Collecting collecting);

    List<CollectingDto> convertCollectingListToCollectingDtoList(List<Collecting> collectingList);

    Collecting convertCollectingSaveRequestDtoToCollecting(CollectingSaveRequestDto collectingSaveRequestDto);

}
