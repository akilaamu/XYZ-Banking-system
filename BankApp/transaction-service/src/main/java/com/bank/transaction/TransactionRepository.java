package com.bank.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Get transactions by customer
    List<Transaction> findByCustomerId(Long customerId);
}
