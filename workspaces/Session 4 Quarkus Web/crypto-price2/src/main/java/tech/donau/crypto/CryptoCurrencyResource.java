package tech.donau.crypto;

import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import tech.donau.crypto.data.Currency;
import tech.donau.crypto.service.CurrencyService;

@Path("/crypto")
public class CryptoCurrencyResource {

    @Inject
    @RestClient
    CurrencyService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Currency> getCrypto(@QueryParam("id") String id) {
        return service.getCurrency(id);
    }
}