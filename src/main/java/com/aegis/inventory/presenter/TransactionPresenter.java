package com.aegis.inventory.presenter;

import com.aegis.inventory.entity.Transaction;
import com.aegis.inventory.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TransactionPresenter {
    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("/transactions")
    public Map<String, Object> getTransactions() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", transactionService.findActiveTransactions());
        return response;
    }

    @GetMapping("/transactions/{id}")
    public Map<String, Object> getTransactionById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", transactionService.findTransactionById(id));
        return response;
    }

    @PostMapping("/transactions")
    public Map<String, Object> createTransaction(@RequestBody Transaction transaction) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", transactionService.createTransaction(transaction));
        return response;
    }

    @PutMapping("/transactions/{id}")
    public Map<String, Object> updateTransaction(@PathVariable UUID id, @RequestBody Transaction payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", transactionService.updateTransaction(id, payload));
        return response;
    }

    @DeleteMapping("/transactions/{id}")
    public void deleteTransaction(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        transactionService.deleteTransaction(id);
    }
}
