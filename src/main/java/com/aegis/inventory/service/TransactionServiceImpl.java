package com.aegis.inventory.service;

import com.aegis.inventory.entity.Product;
import com.aegis.inventory.entity.Transaction;
import com.aegis.inventory.entity.User;
import com.aegis.inventory.exception.InsufficientProductStock;
import com.aegis.inventory.exception.ResourceNotFound;
import com.aegis.inventory.repository.ProductRepository;
import com.aegis.inventory.repository.TransactionRepository;
import com.aegis.inventory.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Transaction> findActiveTransactions() {
        return transactionRepository.findAllActiveTransactions();
    }

    @Override
    public Transaction findTransactionById(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Transaction not found with id + " + id));

        return transaction;
    }

    @Override
    public Transaction createTransaction(Transaction payload) {
        User user = userRepository.findById(payload.getUser().getId())
                .orElseThrow(() -> new ResourceNotFound("User not found with id + " + payload.getUser().getId()));
        Product product = productRepository.findById(payload.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFound("Product not found with id + " + payload.getProduct().getId()));

        if (product.getStock() < 0) {
            throw new InsufficientProductStock("Insufficient product stock with id :" + product.getId());
        }

        product.setStock(product.getStock() - 1);

        Transaction transaction = new Transaction();
        transaction.setOrderDate(LocalDateTime.now().toString());
        transaction.setUser(user);
        transaction.setProduct(product);
        transaction.setTotalPrice(payload.getTotalPrice());

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(UUID id, Transaction payload) {
        Transaction transaction =  transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Transaction not found with id + " + id));
        User user = userRepository.findById(payload.getUser().getId())
                        .orElseThrow(() -> new ResourceNotFound("User not found with id + " + payload.getUser().getId()));
        Product product = productRepository.findById(payload.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFound("Product not found with id + " + payload.getProduct().getId()));

        transaction.setOrderDate(LocalDateTime.now().toString());
        transaction.setUser(user);
        transaction.setProduct(product);
        transaction.setTotalPrice(payload.getTotalPrice());
        transaction.setOrderStatus(Transaction.OrderStatus.PENDING);

        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Transaction not found with id + " + id));
        transaction.setDeletedAt(LocalDateTime.now());

        transactionRepository.save(transaction);
    }
}
