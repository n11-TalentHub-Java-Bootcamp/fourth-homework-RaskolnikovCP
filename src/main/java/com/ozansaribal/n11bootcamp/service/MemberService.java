package com.ozansaribal.n11bootcamp.service;

import com.ozansaribal.n11bootcamp.converter.MemberMapper;
import com.ozansaribal.n11bootcamp.dto.MemberDto;
import com.ozansaribal.n11bootcamp.dto.MemberSaveRequestDto;
import com.ozansaribal.n11bootcamp.entity.Member;
import com.ozansaribal.n11bootcamp.service.entityService.MemberEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberEntityService memberEntityService;

    public List<MemberDto> findAll() {

        List<Member> memberList = memberEntityService.findAll();

        List<MemberDto> memberDtoList = MemberMapper.INSTANCE.convertMemberListToMemberDtoList(memberList);

        return memberDtoList;
    }

    public MemberDto findById(Long id) {

        Member member = findMemberById(id);

        MemberDto memberDto = MemberMapper.INSTANCE.convertMemberToMemberDto(member);

        return memberDto;
    }

    public MemberDto findByUsername(String username) {

        Member member = memberEntityService.findByUsername(username);

        if (member == null){
            throw new RuntimeException("Member not found!");
        }

        MemberDto memberDto = MemberMapper.INSTANCE.convertMemberToMemberDto(member);

        return memberDto;
    }

    public MemberDto saveMember(MemberSaveRequestDto memberSaveRequestDto){

        Member member = MemberMapper.INSTANCE.convertMemberSaveRequestDtoToMember(memberSaveRequestDto);

        member = memberEntityService.save(member);

        MemberDto memberDto = MemberMapper.INSTANCE.convertMemberToMemberDto(member);

        return memberDto;

    }

    public void delete(Long id) {

        Member member = findMemberById(id);

        memberEntityService.delete(member);

    }

    private Member findMemberById(Long id) {

        Member member;

        Optional<Member> optionalDebt = memberEntityService.findById(id);

        if (optionalDebt.isPresent()){
            member = optionalDebt.get();
        } else {
            throw new RuntimeException("Member not found!");
        }

        return member;

    }

}
