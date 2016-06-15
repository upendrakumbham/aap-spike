package uk.ac.ebi.tsi.aap.rest.controller;

import org.springframework.hateoas.ResourceSupport;
import uk.ac.ebi.tsi.aap.rest.repository.model.Domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by ukumbham on 08/06/2016.
 */
public class DomainResource extends ResourceSupport {


    public Long getDomainId() {
        return domainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getDomainDescription() {
        return domainDescription;
    }

    public DomainResource(Domain domain) {
        this.domainId = domain.getId();
        this.domainName = domain.getDomainName();
        this.domainDescription = domain.getDomainDesc();
        this.add(
                linkTo(
                        methodOn(DomainRestController.class
                        ).getDomain(domain.getId())

                ).withSelfRel()
        );
        this.add(
                linkTo(
                       methodOn(DomainRestController.class
                       ).createDomain(domain.getDomainName(),domain.getDomainDesc())
                ).withSelfRel()
        );

        this.add(
                linkTo(
                        methodOn(DomainRestController.class
                        ).updateDomain(domain.getId(),domain.getDomainName(),domain.getDomainDesc())
                ).withSelfRel()
        );
//        this.add(
//                linkTo(
//                        methodOn(
//                                DomainRestController.class
//                        ).getAllDomains()
//                ).withSelfRel()
//        );
//        this.add(
//                linkTo(
//                        methodOn(DomainRestController.class
//                        ).deleteDomain(domain.getId())
//                ).withSelfRel()
//        );

    }

    private Long domainId;
    private String domainName;
    private String domainDescription;


}
