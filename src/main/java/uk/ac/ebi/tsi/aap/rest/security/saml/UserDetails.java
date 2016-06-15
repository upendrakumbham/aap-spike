package uk.ac.ebi.tsi.aap.rest.security.saml;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.saml.SAMLCredential;

import java.util.Collection;

/**
 * Created by ameliec on 17/05/2016.
 */
public class UserDetails extends User {

    static final String OID_EMAIL = "urn:oid:0.9.2342.19200300.100.1.3";
    static final String OID_DISPLAY_NAME = "urn:oid:2.16.840.1.113730.3.1.241";

    String email;
    String name;

    public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserDetails withAttributes(SAMLCredential credential) {
        this.email = findEmail(credential);
        this.name = findName(credential);
        return this;
    }

    private String findEmail(SAMLCredential credential) {
        String email = credential.getAttributeAsString(OID_EMAIL);
        return (email != null) ?
                email :
                credential.getAttributeAsString("EmailAddress");
    }

    private String findName(SAMLCredential credential) {
        String name = credential.getAttributeAsString(OID_DISPLAY_NAME);
        if(name == null) {
            String forename = credential.getAttributeAsString("FirstName");
            String lastName = credential.getAttributeAsString("LastName");
            name = forename + " " + lastName;
        }
        return name;
    }
}
