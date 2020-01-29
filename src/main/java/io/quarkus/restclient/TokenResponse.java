package io.quarkus.restclient;

import javax.json.bind.annotation.JsonbProperty;

public class TokenResponse {

	@JsonbProperty(value = "access_token")
	private String accessToken;
	
	private String token_type;
	private String expires_in;
	private String scope;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
}
