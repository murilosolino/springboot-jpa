package com.personalproject.springJpaHibernate.services.exceptions;

public class ResourceNotFundException extends RuntimeException {
    public ResourceNotFundException(Object id) {
        super("Resource not found. Id: " + id);
    }
}
