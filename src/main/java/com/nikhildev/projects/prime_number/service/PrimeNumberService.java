package com.nikhildev.projects.prime_number.service;

import org.springframework.stereotype.Service;

@Service
public class PrimeNumberService {
    public boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        for (long i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    public String generateMessage(long number, boolean isPrime) {
        if (isPrime) {
            return String.format("%d is a prime number", number);
        } else {
            return String.format("%d is not a prime number", number);
        }
    }
}
