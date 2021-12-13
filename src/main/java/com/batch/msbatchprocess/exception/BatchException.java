package com.batch.msbatchprocess.exception;

import org.flywaydb.core.api.ErrorCode;

public class BatchException extends Throwable {
    private String error;
    public BatchException() {
        error = ErrorCode.ERROR.toString();
    }
}
