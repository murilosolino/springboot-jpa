package com.personalproject.springJpaHibernate.services.exceptions;

public class NullDataObjectException extends RuntimeException {
    public NullDataObjectException(String message) {
        super(message);
    }
}
