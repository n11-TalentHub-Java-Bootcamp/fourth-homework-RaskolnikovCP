package com.ozansaribal.n11bootcamp.controller;

import com.ozansaribal.n11bootcamp.dto.MemberDto;
import com.ozansaribal.n11bootcamp.dto.MemberSaveRequestDto;
import com.ozansaribal.n11bootcamp.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@AllArgsConstructor
@CrossOrigin
public class MemberController {

    private MemberService memberService;

    @GetMapping
    public ResponseEntity getAll(){

        List<MemberDto> memberDtoList = memberService.findAll();

        return ResponseEntity.ok(memberDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){

        MemberDto memberDto = memberService.findById(id);

        return ResponseEntity.ok(memberDto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity getByUsername(@PathVariable String username){

        MemberDto memberDto = memberService.findByUsername(username);

        return ResponseEntity.ok(memberDto);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody MemberSaveRequestDto memberSaveRequestDto){

        MemberDto memberDto = memberService.saveMember(memberSaveRequestDto);

        return ResponseEntity.ok(memberDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        memberService.delete(id);

    }

}
