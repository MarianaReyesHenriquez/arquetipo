package arquetipo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ArquetipoNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ArquetipoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String arquetipoNotFoundHandler(ArquetipoNotFoundException ex) {
        return ex.getMessage();
    }
}
