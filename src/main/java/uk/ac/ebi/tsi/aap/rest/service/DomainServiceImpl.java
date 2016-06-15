package uk.ac.ebi.tsi.aap.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.tsi.aap.rest.repository.DomainRepository;
import uk.ac.ebi.tsi.aap.rest.repository.model.Domain;

import java.util.List;

/**
 * Created by ukumbham on 31/05/2016.
 */
@Service
@Transactional
public class DomainServiceImpl implements DomainService {
    @Autowired
    DomainRepository domainRepository;

    @Override
    public Domain createDomain(Domain domain) {

        return domainRepository.save(domain);
    }

    @Override
    public Domain findDomainById(Long id) {
        return domainRepository.findOne(id);
    }

    /**
     * Get the all the Domain object from the domains table
     *
     * @return
     */
    @Override
    public List<Domain> getAllDomains() {
        return domainRepository.findAll();
    }

    /**
     * Deletes Domain entry from the domains table based on domainId
     *
     * @param domainId
     * @return domain
     */
    @Override
    public void deleteDomain(Long domainId) {
        domainRepository.delete(domainId);
    }

    @Override
    public Domain updateDomain(Domain domain) {
        return domainRepository.save(domain);
    }

    @Override
    public Domain getDomainByName(String domainName) {
        return  domainRepository.findByName(domainName);
    }
}
