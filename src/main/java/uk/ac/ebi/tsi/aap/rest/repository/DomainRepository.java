package uk.ac.ebi.tsi.aap.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.tsi.aap.rest.repository.model.Domain;

/**
 * Created by ukumbham on 31/05/2016.
 */
@Repository
@EnableJpaRepositories
public interface DomainRepository extends JpaRepository <Domain,Long>{
    Domain findByName(String domainName);


    //Domain findByName(String domainName);


}
