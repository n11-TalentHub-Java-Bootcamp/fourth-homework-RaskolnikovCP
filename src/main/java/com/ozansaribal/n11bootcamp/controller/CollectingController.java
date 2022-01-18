package com.ozansaribal.n11bootcamp.controller;

import com.ozansaribal.n11bootcamp.dto.CollectingDto;
import com.ozansaribal.n11bootcamp.dto.CollectingSaveRequestDto;
import com.ozansaribal.n11bootcamp.dto.DebtSaveRequestDto;
import com.ozansaribal.n11bootcamp.service.CollectingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/collecting")
@AllArgsConstructor
@CrossOrigin
public class CollectingController {

    private CollectingService collectingService;

    @GetMapping
    public ResponseEntity getAll(){

        List<CollectingDto> collectingDtoList = collectingService.findAll();

        return ResponseEntity.ok(collectingDtoList);

    }

    @PostMapping
    public ResponseEntity save(@RequestBody CollectingSaveRequestDto collectingSaveRequestDto, @RequestBody DebtSaveRequestDto debtSaveRequestDto, @RequestBody Long debtId){

        CollectingDto collectingDto = collectingService.saveCollecting(collectingSaveRequestDto, debtSaveRequestDto, debtId);

        return ResponseEntity.ok(collectingDto);

    }

    @GetMapping("/{date1}-{date2}")
    public ResponseEntity findAllByDatesBetween(@PathVariable Date firstDate, @PathVariable Date secondDate){

        CollectingDto collectingDto = collectingService.findAllByDatesBetween(firstDate, secondDate);

        return ResponseEntity.ok(collectingDto);

    }

    @GetMapping("/{memberId}")
    public ResponseEntity findAllByMemberId(@PathVariable Long memberId){

        List<CollectingDto> collectingDtoList = collectingService.findAllByMemberIdOrderById(memberId);

        return ResponseEntity.ok(collectingDtoList);

    }

    @GetMapping("/sum/{memberId}")
    public ResponseEntity findSumOfAllLateFeeDebtsByMemberId(@PathVariable Long memberId){

        double result = collectingService.findSumOfAllLateFeeDebtsByMemberId(memberId);

        return ResponseEntity.ok(result);

    }

/*
    @PostMapping
    public ResponseEntity create(@RequestBody UsrUserSaveRequestDto usrUserSaveRequestDto){

        UsrUserDto usrUserDto = usrUserService.save(usrUserSaveRequestDto);

        return ResponseEntity.ok(usrUserDto);
    }*/

}
