package jowens_software.tickerfetch3;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SingleTickerNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SingleTickerQuoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(SingleTickerQuoteNotFoundException ex) {
        return ex.getMessage();
    }
}
