package com.cristian.moneymanager.controller;

import com.cristian.moneymanager.dto.ExpenseDto;
import com.cristian.moneymanager.dto.IncomeDto;
import com.cristian.moneymanager.service.ExpenseService;
import com.cristian.moneymanager.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/incomes")
public class IncomeController {

    private IncomeService incomeService;

    @PostMapping
    public ResponseEntity<IncomeDto> addExpense(@RequestBody IncomeDto dto) {
        IncomeDto savedIncome = incomeService.addIncome(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedIncome);
    }
}
