package tech.donau.crypto;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import tech.donau.crypto.data.MultipartBody;
import tech.donau.crypto.service.CurrencyService;

@Path("/echo")
public class CryptoCurrencyResource {

    @Inject
    @RestClient
    CurrencyService service;
 
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String echoFile(String body) {
        return body;
    }
      
    
    @Path("test")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String callEcho() {
        final MultipartBody multipartBody = new MultipartBody();
        multipartBody.file = new ByteArrayInputStream("Hello world".getBytes());
        multipartBody.name = "hello.txt";
        return service.sendFile(multipartBody);
    }
}