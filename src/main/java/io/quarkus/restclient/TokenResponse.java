package io.quarkus.restclient;

import javax.enterprise.context.RequestScoped;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;

import io.quarkus.json.adapter.DateTimeAdapter;

@RequestScoped
public class TokenResponse {

	@JsonbProperty(value = "access_token")
	private String accessToken;
	private String token_type;
	private int expires_in;
	private String scope;
	
	@JsonbTypeAdapter(DateTimeAdapter.class)
	private LocalDateTime tokenTime;

	public LocalDateTime getTokenTime() {
		if (tokenTime == null) {
			tokenTime = new LocalDateTime();
		}
		return tokenTime;
	}

	public void setTokenTime(LocalDateTime tokenTime) {
		this.tokenTime = tokenTime;
	}

	public TokenResponse() {
	}

	public boolean isExpiry() {
		System.out.println(new DateTime() + "    " + getTokenTime());
		Duration duration = new Duration(new LocalDateTime().toDateTime(), getTokenTime().plusSeconds(expires_in).toDateTime());
		return (duration == null ? true : duration.getMillis() / 1000 >= expires_in);
	}

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

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return accessToken;
	}

}
