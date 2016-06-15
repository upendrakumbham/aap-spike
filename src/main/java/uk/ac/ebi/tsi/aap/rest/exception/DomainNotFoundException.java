package uk.ac.ebi.tsi.aap.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ukumbham on 13/06/2016.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DomainNotFoundException extends RuntimeException {

    public DomainNotFoundException(Long domainId){
        super("could find domain"+domainId);
    }
}
