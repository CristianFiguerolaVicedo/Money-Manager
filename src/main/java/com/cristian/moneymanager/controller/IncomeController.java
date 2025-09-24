package com.cristian.moneymanager.controller;

import com.cristian.moneymanager.dto.IncomeDto;
import com.cristian.moneymanager.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/incomes")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<IncomeDto> addIncome(@RequestBody IncomeDto dto) {
        IncomeDto savedIncome = incomeService.addIncome(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedIncome);
    }

    @GetMapping
    public ResponseEntity<List<IncomeDto>> getExpenses() {
        List<IncomeDto> incomes = incomeService.getCurrentMonthIncomesForCurrentUser();
        return ResponseEntity.ok(incomes);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
