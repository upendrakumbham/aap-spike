package uk.ac.ebi.tsi.aap.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ebi.tsi.aap.rest.repository.model.Domain;
import uk.ac.ebi.tsi.aap.rest.service.DomainService;

import java.util.List;

/**
 * Created by ukumbham on 31/05/2016.
 *
 * @since v1.0
 * This RESTAPI responsible for
 * 1. Creating new domain with valid input data(name.description).
 * 2. Deleting Domain with domainId as input
 * 3. Get the specific domain details based on domainId
 * 4. Get all the domain objects details from the domains table.
 * <p>
 * This REST API currently using SAML Security (SSOCircle takes care of user authentication part before using rest
 * service.
 * <p>
 * TODO: Need to handle Exceptions
 */
@RestController
@RequestMapping(value = "/domains")
public class DomainRestController {

    private static final Logger logger = LoggerFactory.getLogger(DomainRestController.class);

    @Autowired
    DomainService domainService;

    /**
     * This resource takes care of adding new domain entry to the domains table and displays the added domain
     * details to the user to verify.
     *
     * @param name
     * @param desc
     * @return addedDomain
     */
    @RequestMapping(value = "/{domainName}/{domainDesc}", method = {RequestMethod.POST}, produces = "application/json")

    public DomainResource createDomain(@PathVariable("domainName") String name, @PathVariable("domainDesc") String desc) {
        Domain domain = new Domain(name, desc);
        logger.info("creating new domain with: name-" + name + ", description- " + desc);
        Domain addedDomain = domainService.createDomain(domain);
        if(addedDomain == null);
        logger.info("new domain created with: name- " + name + " description- " + desc);
        return new DomainResource(addedDomain);
    }

    /**
     * This resource is responsible for deleting specified domain object.
     *
     * @param domainId
     */
    @RequestMapping(value = "/{domainId}", method = RequestMethod.DELETE)
    public void deleteDomain(@PathVariable("domainId") Long domainId) {
        logger.info("deleting this domain object : id - " + domainId);
        domainService.deleteDomain(domainId);
        logger.info("deleted domain: id - " + domainId);

    }

    /**
     * This resource is responsible for gets the user requested domain details.
     *
     * @param domainId
     * @return domain
     */
    @RequestMapping(value = "/{domainId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8;")
    public DomainResource getDomain(@PathVariable("domainId") Long domainId) {
        logger.info("gettting domain details : id - " + domainId);
        Domain domain = domainService.findDomainById(domainId);
        logger.info("got the domain details....");
        return new DomainResource(domain);
    }

    /**
     * This resource manages to display all the domains details to user.
     *
     * @return domainList
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Domain> getAllDomains() {
        logger.info("gettting all the domains details..... ");
        List<Domain> domainList = domainService.getAllDomains();
        logger.info("got all the domains..... ");
        return domainList;
    }

    /**
     * This resource deals with updating the domain object in the table
     *
     * @param domainId
     * @param name
     * @param desc
     * @return
     */
    @RequestMapping(value = "/{domainId}/{domainName}/{domainDesc}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8;")
    public DomainResource updateDomain(@PathVariable("domainId") Long domainId, @PathVariable("domainName") String name, @PathVariable("domainDesc") String desc) {
        logger.info("gettting domain details : id - " + domainId);
        Domain domain = domainService.findDomainById(domainId);
        logger.info("got the domain details....");
        logger.info("updating domain details....");
        domain.setDomainName(name);
        domain.setDomainDesc(desc);
        logger.info("updating domain details....");
        Domain resultDomain = domainService.updateDomain(domain);
        return new DomainResource(resultDomain);
    }

    @RequestMapping(value = "/{domainName}/{userName}",method = RequestMethod.GET,produces = "application/json; charset=utf-8;")
     public ResponseEntity<Object> getUserMembership(@PathVariable("userName") String userName, @PathVariable("domainName") String domainName){

         Domain domain = domainService.getDomainByName(domainName);

         logger.info("domainService.getDomainByName*******"+domain);

         return  new ResponseEntity<Object>(domain,HttpStatus.OK);
     }

}

