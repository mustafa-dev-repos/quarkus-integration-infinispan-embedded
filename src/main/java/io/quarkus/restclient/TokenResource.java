package io.quarkus.restclient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.infinispan.embedded.listener.InfinispanStartupListener;

@Path("/service")
public class TokenResource {
	
    @Inject
    @RestClient
    private TokenService tokenService;
    
    @Inject 
    TokenConfiguration tokenConfiguration;
    
	@Inject
	InfinispanStartupListener infinispanStartupListener;
    
    @GET
    @Path("/token/{keyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String token(@PathParam(value = "keyName") String keyName) {

    	String keyValue = infinispanStartupListener.getCache(keyName);

    	if (keyValue==null) {
    		TokenResponse response = tokenService.getToken(tokenConfiguration.getClient_id(), tokenConfiguration.getClient_secret(), 
            		tokenConfiguration.getGrant_type(), tokenConfiguration.getScope());
    		keyValue = response.getAccessToken();
    		infinispanStartupListener.putCache(keyName, keyValue);
    	}
    	
    	return keyValue;

    }
    
}
