package com.ozansaribal.n11bootcamp.controller;

import com.ozansaribal.n11bootcamp.dto.DebtDto;
import com.ozansaribal.n11bootcamp.dto.DebtSaveRequestDto;
import com.ozansaribal.n11bootcamp.service.DebtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
@AllArgsConstructor
@CrossOrigin
public class DebtController {

    private DebtService debtService;

    @PostMapping
    public ResponseEntity save(@RequestBody DebtSaveRequestDto debtSaveRequestDto){

        DebtDto debtDto = debtService.saveDebt(debtSaveRequestDto);

        return ResponseEntity.ok(debtDto);

    }

    @GetMapping("/{date1}-{date2}")
    public ResponseEntity findAllByDatesBetween(@PathVariable Date firstDate, @PathVariable Date secondDate){

        DebtDto debtDto = debtService.findAllByDatesBetween(firstDate, secondDate);

        return ResponseEntity.ok(debtDto);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity findAllByMemberIdOrderByIdAndRestAmountGreaterThan(@PathVariable Long memberId, @PathVariable Long restAmount){

        List<DebtDto> debtDtoList = debtService.findAllByMemberIdOrderByIdAndRestAmountGreaterThan(memberId, 0L);

        return ResponseEntity.ok(debtDtoList);
    }

    @GetMapping("/late-fee-debts/{memberId}-{date}-{0}")
    public ResponseEntity findAllByMemberIdOrderByIdAndDueDateBeforeAndRestAmountGreaterThan(@PathVariable Long memberId, @PathVariable Date date, @PathVariable Long restAmount){

        List<DebtDto> debtDtoList = debtService.findAllByMemberIdOrderByIdAndDueDateBeforeAndRestAmountGreaterThan(memberId, date, 0L);

        return ResponseEntity.ok(debtDtoList);

    }

    @GetMapping("/sum/{memberId}-{restAmount}")
    public ResponseEntity findSumOfAllDebtsByMemberId(@PathVariable Long memberId, @PathVariable Long restAmount){

        double result = debtService.findSumOfAllDebtsByMemberId(memberId, 0L);

        return ResponseEntity.ok(result);

    }

    @GetMapping("/late-fee-debts/sum/{memberId}-{date}-{restAmount}")
    public ResponseEntity findSumOfAllLateFeeDebtsByMemberId(@PathVariable Long memberId, @PathVariable Date date, @PathVariable Long restAmount){

        double result = debtService.findSumOfAllLateFeeDebtsByMemberId(memberId, date, 0L);

        return ResponseEntity.ok(result);

    }

    @GetMapping("/late-fee-debts/calculate/{memberId}-{date}-{restAmount}")
    public ResponseEntity calculateInitialLateFeeDebt(@PathVariable Long memberId, @PathVariable Date date, @PathVariable Long restAmount){

        double result = debtService.calculateInitialLateFeeDebt(memberId,date,0L);

        return ResponseEntity.ok(result);

    }

}
