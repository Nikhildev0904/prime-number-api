package com.nikhildev.projects.prime_number.controller;


import com.nikhildev.projects.prime_number.dto.PrimeCheckResponse;
import com.nikhildev.projects.prime_number.service.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PrimeNumberController {

    private final PrimeNumberService primeNumberService;

    @Autowired
    public PrimeNumberController(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    @GetMapping("/prime/check/{number}")
    public ResponseEntity<PrimeCheckResponse> checkPrime(@PathVariable long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be greater than 0");
        }

        boolean isPrime = primeNumberService.isPrime(number);
        String message = primeNumberService.generateMessage(number, isPrime);

        PrimeCheckResponse response = new PrimeCheckResponse(number, isPrime, message);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/prime")
    public ResponseEntity<PrimeCheckResponse> checkPrimeWithQueryParam(@RequestParam long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be greater than 0");
        }

        boolean isPrime = primeNumberService.isPrime(number);
        String message = primeNumberService.generateMessage(number, isPrime);

        PrimeCheckResponse response = new PrimeCheckResponse(number, isPrime, message);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Prime Number API is running!");
    }
}
