package io.quarkus.restclient;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.infinispan.embedded.listener.InfinispanStartupListener;
import io.quarkus.restclient.client.TokenService;
import io.quarkus.restclient.config.TokenConfig;

@Path("/service")
public class TokenResource {

	@Inject
	@RestClient
	private TokenService tokenService;

	@Inject
	InfinispanStartupListener infinispanStartupListener;
	
	@Inject
	TokenResponse tokenResponse;
	
	@GET
	@Path("/token/{keyName}")
	@Produces(MediaType.APPLICATION_JSON)
	public TokenResponse token(@PathParam(value = "keyName") String keyName) {
		
		String keyValue = infinispanStartupListener.getCacheValue(keyName);
		if (keyValue != null) {
			tokenResponse = JsonbBuilder.create(new JsonbConfig()).fromJson(keyValue, TokenResponse.class);
		}

		if (keyValue == null || (tokenResponse != null && tokenResponse.isExpiry())) {
			
			tokenResponse = tokenService.getToken(
					TokenConfig.getInstance().getConfig("token").getClient_id(),
					TokenConfig.getInstance().getConfig("token").getClient_secret(),
					TokenConfig.getInstance().getConfig("token").getGrant_type(),
					TokenConfig.getInstance().getConfig("token").getScope());
			
			if (tokenResponse.getAccessToken() != null || !"".equals(tokenResponse.getAccessToken())) {
				infinispanStartupListener.putCache(keyName,
						JsonbBuilder.create(new JsonbConfig()).toJson(tokenResponse));
			}
		}

		return tokenResponse;

	}

}
