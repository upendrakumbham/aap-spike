package uk.ac.ebi.tsi.aap.rest.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.tsi.aap.rest.repository.model.Domain;
import uk.ac.ebi.tsi.aap.rest.service.DomainService;

/**
 * Created by ukumbham on 03/06/2016.
 */
@Transactional
public class DomainRestControllerTest extends AAPControllerTest {

    @Autowired
    private DomainService domainService;

    private String uri = "/domains/1";

    private Domain domain;

    private MediaType contentType = MediaType.parseMediaType("application/hal+json");

    @Before
    public void setUp(){
        super.setUp();
        //this.domain = domainService.createDomain(new Domain(Long.valueOf(1),"Usha","Description"));
    }

    @Test
    public void testGetDomain() throws Exception {

//        mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType));
//               // .andExpect(jsonPath("$.id",is(Integer.valueOf(20))))
               // .andExpect((ResultMatcher) jsonPath("$.domainName",is(this.domain.getDomainName())));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        int status =  mvcResult.getResponse().getStatus();
        String contentType = mvcResult.getResponse().getContentType();

        Assert.assertEquals("failure- Expected http status code is 200",200,status);
        Assert.assertNotNull("failure- Expected http response body is not null",content.trim().length()>0);
        Assert.assertNotNull("failure- Expected http contentType is not null",contentType.trim().length()>0);
        Assert.assertEquals("failure- Expected contentType is application/json;charset=utf-8",MediaType.APPLICATION_JSON_UTF8.toString(),contentType);

    }

    @Test
    public void testCreateDomain() throws Exception {
        String uri = "/domains/testName/testDescription";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON_UTF8))
                              .andReturn();

        String content =  mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();
        Boolean charset =  mvcResult.getResponse().isCharset();
        Assert.assertEquals("failure- Expected http status code is 200",200,status);
        Assert.assertNotNull("failure- Expected http response body is not null",content.trim().length()>0);
        Assert.assertTrue("utf8",charset);
    }

    @Test
   public void testDeleteDomain() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        String content =  mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("failure- Expected http status code is 200",200,status);
        Assert.assertNotNull("failure- Expected http response body is not null",content.trim().length()>0);
   }

    @Test
    public void testGetAllDomains() throws Exception {
        String uri = "/domains";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        String content =  mvcResult.getResponse().getContentAsString();
        int status = mvcResult.getResponse().getStatus();
        String contentType =  mvcResult.getResponse().getContentType();
        Assert.assertEquals("failure- Expected http status code is 200",200,status);
        Assert.assertNotNull("failure- Expected http response body is not null",content.trim().length()>0);
        Assert.assertEquals("failure- Expected contentType is application/json;charset=utf-8",MediaType.APPLICATION_JSON_UTF8.toString(),contentType);
    }

}