package com.cristian.moneymanager.controller;

import com.cristian.moneymanager.dto.ExpenseDto;
import com.cristian.moneymanager.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/expenses")
public class ExpenseController {

    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDto> addExpense(@RequestBody ExpenseDto dto) {
        ExpenseDto savedExpense = expenseService.addExpense(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedExpense);
    }
}
