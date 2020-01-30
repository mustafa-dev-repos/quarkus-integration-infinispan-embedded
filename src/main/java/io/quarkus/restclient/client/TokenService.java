package io.quarkus.restclient.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.restclient.TokenResponse;


@Path("/connect")
@RegisterRestClient
public interface TokenService {
	
	@POST
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	TokenResponse getToken(@FormParam("client_id") String clientId, 
						  @FormParam("client_secret") String clientSecret,
						  @FormParam("grant_type") String grantType, 
						  @FormParam("scope") String scope);

}
