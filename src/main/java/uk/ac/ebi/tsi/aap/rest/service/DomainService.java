package uk.ac.ebi.tsi.aap.rest.service;

import uk.ac.ebi.tsi.aap.rest.repository.model.Domain;

import java.util.List;

/**
 * Created by ukumbham on 31/05/2016.
 * This Interface deals with Domain CRUD operations
 */
public interface DomainService {

    public Domain createDomain(Domain domain);

    /**
     * Get the domain object from the database with id as input
     * @param id
     * @return
     */
    public Domain findDomainById(Long id);

    /**static uk.ac.ebi.tsi.aap.rest.repository.model.Domain deleteDomain(Long ) {
    }
     * Get the all the Domain object from the domains table
     * @return
     */
    List<Domain> getAllDomains();


    /**
     * Deletes Domain entry from the domains table based on domainId
     * @param domainId
     * @return domain
     */
    public void deleteDomain(Long domainId);

    Domain updateDomain(Domain domain);

    Domain getDomainByName(String domainName);
}
