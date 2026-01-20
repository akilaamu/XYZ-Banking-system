package com.bank.transaction;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    // Kubernetes service name for Notification Service
    private static final String NOTIFICATION_URL =
            "http://notification-service:8080/notify";

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    // Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    // Create a new transaction
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {

        transaction.setStatus("SUCCESS");

        Transaction savedTransaction = repository.save(transaction);

        // Redirect to Notification Service
        String message = "Transaction SUCCESS for Customer ID: "
                + transaction.getCustomerId()
                + ", Amount: " + transaction.getAmount();

        restTemplate.postForObject(NOTIFICATION_URL, message, String.class);

        return savedTransaction;
    }
}

