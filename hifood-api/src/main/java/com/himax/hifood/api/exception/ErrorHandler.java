package com.himax.hifood.api.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handle errors using the "Problem Details for HTTP APIs" standardized
 * following RFC 9457 - Problem Details for HTTP APIs.
 * @see <a href="https://www.rfc-editor.org/info/rfc9457">RFC 9457</a>
 */
@Service
public class ErrorHandler {

    public ProblemDetail handleException(Exception ex, HttpStatus status, HttpServletRequest servletRequest, MessageSource messageSource) {
        ProblemDetail problemDetail = setupProblemDetail(ex, status, servletRequest,messageSource);
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    private ProblemDetail setupProblemDetail(Exception ex, HttpStatus status,HttpServletRequest servletRequest, MessageSource messageSource) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setTitle(status.getReasonPhrase());

        if(hasFields(ex)){
            problemDetail.setProperty("fields", handleFields(ex, messageSource));
            problemDetail.setTitle("Invalid params");
        }

        /*
         * This url could be created in application to provide human-readable documentation for the
         * problem type. If not, its value is assumed to be "about:blank".
         * Remove this url defining null if url will not be created
         */
        String url = servletRequest.getRequestURL().toString().concat(("/error"));
        problemDetail.setType(URI.create(url));

        return problemDetail;
    }

    private boolean hasFields(Exception ex) {
        return ex instanceof MethodArgumentNotValidException;
    }

    public HttpStatus getHttpStatus(Exception ex){
        return switch (getErrorName(ex)) {
            case "ChildNotFoundException" -> HttpStatus.BAD_REQUEST;
            case "EntityInUseException" -> HttpStatus.CONFLICT;
            case "EntityNotFoundException" -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    private Map<String, String> handleFields(Exception ex, MessageSource messageSource) {
        MethodArgumentNotValidException error = (MethodArgumentNotValidException) ex;
        /*
         * List of invalid params and theirs error messages customized at
         * messages.properties file
         */
        return error
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, e -> messageSource.getMessage(e, LocaleContextHolder.getLocale())));
    }

    private String getErrorName(Exception exception) {
        // Traverse the exception chain to find the root cause
        Throwable rootCause = exception;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }

        // Return the simple class name of the root cause as the identifier
        return rootCause.getClass().getSimpleName();
    }
}
