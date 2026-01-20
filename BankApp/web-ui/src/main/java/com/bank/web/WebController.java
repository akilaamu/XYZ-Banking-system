package com.bank.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/web")
public class WebController {

    private final RestTemplate restTemplate = new RestTemplate();

    // Kubernetes service DNS names
    private static final String CUSTOMER_SERVICE = "http://customer-service:8080/customers";
    private static final String TRANSACTION_SERVICE = "http://transaction-service:8080/transactions";
    private static final String LOAN_SERVICE = "http://loan-service:8080/loans";

    // ---------------- CUSTOMER ----------------

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody Map<String, Object> customer) {
        return restTemplate.postForEntity(
                CUSTOMER_SERVICE,
                customer,
                Object.class
        );
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        return restTemplate.getForEntity(
                CUSTOMER_SERVICE,
                Object.class
        );
    }

    // ---------------- TRANSACTION ----------------

    @PostMapping("/transaction")
    public ResponseEntity<?> createTransaction(@RequestBody Map<String, Object> transaction) {
        return restTemplate.postForEntity(
                TRANSACTION_SERVICE,
                transaction,
                Object.class
        );
    }

    @GetMapping("/transactions/{customerId}")
    public ResponseEntity<?> getTransactions(@PathVariable Long customerId) {
        return restTemplate.getForEntity(
                TRANSACTION_SERVICE + "/customer/" + customerId,
                Object.class
        );
    }

    // ---------------- LOAN ----------------

    @PostMapping("/loan")
    public ResponseEntity<?> applyLoan(@RequestBody Map<String, Object> loan) {
        return restTemplate.postForEntity(
                LOAN_SERVICE,
                loan,
                Object.class
        );
    }

    @GetMapping("/loans/{customerId}")
    public ResponseEntity<?> getLoans(@PathVariable Long customerId) {
        return restTemplate.getForEntity(
                LOAN_SERVICE + "/customer/" + customerId,
                Object.class
        );
    }
}

