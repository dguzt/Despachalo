package org.guzman.despachalo.adapter.web.config;

import io.sentry.Sentry;
import lombok.Builder;
import lombok.Getter;
import org.guzman.despachalo.commons.errors.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<RestError> handleBusinessAndSystemExceptions(Exception ex) {
        var status = getErrorHttpStatus(ex);

        if (ex instanceof BusinessException) {
            var error = buildRestError((BusinessException) ex);
            logger.error(error.getMessage());
            return new ResponseEntity<>(error, new HttpHeaders(), status);
        }

        logger.error(ex.getMessage(), ex);
        var error = buildSystemRestError();
        return new ResponseEntity<>(error, new HttpHeaders(), status);
    }

    @Builder @Getter
    public static class RestError {
        String code;
        String message;
        Object data;
    }

    private RestError buildSystemRestError() {
        return RestError.builder()
                .message("A system error happened. Please, try again later.")
                .build();
    }

    private RestError buildRestError(BusinessException businessException) {
        return RestError.builder()
                .code(businessException.getCode())
                .message(businessException.getMessage())
                .data(businessException.getData())
                .build();
    }

    private HttpStatus getErrorHttpStatus(Exception ex) {
        if (ex instanceof NotFoundException) {
            return HttpStatus.NOT_FOUND;
        }

        if (ex instanceof UserInputException) {
            return HttpStatus.BAD_REQUEST;
        }

        if (ex instanceof AuthorizationException) {
            return HttpStatus.FORBIDDEN;
        }

        if (ex instanceof AuthenticationException) {
            return HttpStatus.UNAUTHORIZED;
        }

        Sentry.captureException(ex);
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
