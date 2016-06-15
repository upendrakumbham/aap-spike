package uk.ac.ebi.tsi.aap.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uk.ac.ebi.tsi.aap.rest.exception.DomainNotFoundException;

/**
 * Created by ukumbham on 13/06/2016.
 */
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainNotFoundException.class)

    protected ResponseEntity<Object>  domainNotFoundExceptionHadler(RuntimeException ex, WebRequest request){
      String bodyofResponse =  "Domain not found......";
        return new ResponseEntity<Object>(bodyofResponse,new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
