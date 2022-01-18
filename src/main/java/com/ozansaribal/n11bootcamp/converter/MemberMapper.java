package com.ozansaribal.n11bootcamp.converter;

import com.ozansaribal.n11bootcamp.dto.MemberDto;
import com.ozansaribal.n11bootcamp.dto.MemberSaveRequestDto;
import com.ozansaribal.n11bootcamp.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member convertMemberSaveRequestDtoToMember(MemberSaveRequestDto memberSaveRequestDto);

    MemberDto convertMemberToMemberDto(Member member);

    List<MemberDto> convertMemberListToMemberDtoList(List<Member> memberList);

}
