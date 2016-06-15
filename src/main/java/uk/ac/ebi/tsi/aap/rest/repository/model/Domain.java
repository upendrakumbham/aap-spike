package uk.ac.ebi.tsi.aap.rest.repository.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by ukumbham on 31/05/2016.
 */
@Entity
@Table(name= "domains")
//@Access(AccessType.FIELD)
public class Domain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String domainName;

    private String domainDesc;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_domain",joinColumns = @JoinColumn(name = "domain_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    private Set<User> users;


    public Domain(){

    }
    public  Domain(String domainName, String domainDesc){
        this.domainName = domainName;
        this.domainDesc = domainDesc;
    }

//    public  Domain(Long id,String domainName, String domainDesc){
//        this.domainName = domainName;
//        this.domainDesc = domainDesc;
//        this.id = id;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainDesc() {
        return domainDesc;
    }

    public void setDomainDesc(String domainDesc) {
        this.domainDesc = domainDesc;
    }


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "id=" + id +
                ", domainName='" + domainName + '\'' +
                ", domainDesc='" + domainDesc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Domain)) return false;

        Domain domain = (Domain) o;

        if (!id.equals(domain.id)) return false;
        if (!domainName.equals(domain.domainName)) return false;
        return domainDesc.equals(domain.domainDesc);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + domainName.hashCode();
        result = 31 * result + domainDesc.hashCode();
        return result;
    }



}
