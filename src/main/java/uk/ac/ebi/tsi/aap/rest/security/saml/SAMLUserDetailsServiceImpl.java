package uk.ac.ebi.tsi.aap.rest.security.saml;

import org.opensaml.saml2.core.Attribute;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.XSString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ameliec on 16/05/2016.
 */
@Service
public class SAMLUserDetailsServiceImpl implements SAMLUserDetailsService {


    // Logger
    private static final Logger LOG = LoggerFactory.getLogger(SAMLUserDetailsServiceImpl.class);

    public Object loadUserBySAML(SAMLCredential credential)
            throws UsernameNotFoundException {

        // The method is supposed to identify local account of user referenced by
        // data in the SAML assertion and return UserDetails object describing the user.

        String userID = credential.getNameID().getValue();

        LOG.info(userID + " is logged in");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(authority);

        logAttributes(credential);

        UserDetails user = new UserDetails(userID, "<abc123>", true, true, true, true, authorities)
                .withAttributes(credential);

        // In a real scenario, this implementation has to locate user in a arbitrary
        // dataStore based on information present in the SAMLCredential and
        // returns such a date in a form of application specific UserDetails object.
        return user;
    }

    private void logAttributes(SAMLCredential credential) {
        List<Attribute> attributes = credential.getAttributes();
        LOG.trace("###");
        LOG.trace("###");
        LOG.trace("GOT ATTRIBUTES ? {}", (attributes !=  null) && (attributes.size() > 0));
        for(Attribute attribute : attributes) {
            LOG.trace("Found Attribute {}", attribute.getName());
            List<XMLObject> values = attribute.getAttributeValues();
            LOG.trace("  with friendly name {}", attribute.getFriendlyName());
            for(XMLObject value : values) {
                LOG.trace("   of type [{}]", value.getSchemaType());
                if(value instanceof XSString) {
                    XSString strValue = (XSString) value;
                    LOG.trace("   with value [{}]", strValue.getValue());
                }
            }
        }
        LOG.trace("###");
        LOG.trace("###");
    }


}
