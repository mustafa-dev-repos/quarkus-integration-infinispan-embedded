package io.quarkus.restclient.config;

public class TokenConfigurationParams {
	
	private String client_id;
	private String client_secret;
	private String grant_type;
	private String scope;
	
	public TokenConfigurationParams(){}
	
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getClient_id() {
		return client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public String getScope() {
		return scope;
	}

}
