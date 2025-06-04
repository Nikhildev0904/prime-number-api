package com.nikhildev.projects.prime_number.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrimeCheckResponse {
    private Long number;
    private boolean isPrime;
    private String message;

    public PrimeCheckResponse() {}

    public PrimeCheckResponse(Long number, boolean isPrime, String message) {
        this.number = number;
        this.isPrime = isPrime;
        this.message = message;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @JsonProperty("is_prime")
    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
