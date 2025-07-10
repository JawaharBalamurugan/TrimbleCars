package com.trimblecars.carleaseservice.exception;

public class MaxLeaseLimitException extends RuntimeException {
    public MaxLeaseLimitException() {
        super("Maximum Lease Limit Reached (2 active leases allowed");
    }
}
