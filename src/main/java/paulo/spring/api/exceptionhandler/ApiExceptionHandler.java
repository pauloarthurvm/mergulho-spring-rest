package paulo.spring.api.exceptionhandler;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice   // Help controller classes
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Problem problem = new Problem();
        List<Problem.Field> fields = new ArrayList<>();

        for (ObjectError err : ex.getBindingResult().getAllErrors()) {
            fields.add(new Problem.Field(
                    ((FieldError) err).getField(), messageSource.getMessage(err, LocaleContextHolder.getLocale())));
        }

        problem.setStatus(status.value());
        problem.setDateTime(LocalDateTime.now());
        problem.setTitle("One or more invalid values.");
        problem.setFields(fields);
        return handleExceptionInternal(ex, problem, headers, status, request);
    }
}
