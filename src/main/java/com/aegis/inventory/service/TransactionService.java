package com.aegis.inventory.service;

import com.aegis.inventory.entity.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<Transaction> findActiveTransactions();
    Transaction findTransactionById(UUID id);
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransaction(UUID id, Transaction transaction);
    void deleteTransaction(UUID id);
}
