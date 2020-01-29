package io.quarkus.restclient;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "token")
public class TokenConfiguration {
	
	@ConfigProperty(defaultValue = "")
	private String client_id;
	@ConfigProperty(defaultValue = "")
	private String client_secret;
	@ConfigProperty(defaultValue = "")
	private String grant_type;
	@ConfigProperty(defaultValue = "")
	private String scope;
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_secret() {
		return client_secret;
	}
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	


}
