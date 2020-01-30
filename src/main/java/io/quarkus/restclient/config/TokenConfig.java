package io.quarkus.restclient.config;

import org.eclipse.microprofile.config.ConfigProvider;

public class TokenConfig {

	private static TokenConfig instance = null;
	private TokenConfigurationParams tokenConfigurationParams;

	public String getClient_id() {
		return tokenConfigurationParams.getClient_id();
	}

	public String getClient_secret() {
		return tokenConfigurationParams.getClient_secret();
	}

	public String getGrant_type() {
		return tokenConfigurationParams.getGrant_type();
	}

	public String getScope() {
		return tokenConfigurationParams.getScope();
	}

	public static synchronized TokenConfig getInstance() {
		if (instance == null) {
			instance = new TokenConfig();
		}
		return instance;
	}

	public TokenConfig getConfig(String prefix) {

		TokenConfigurationParams tokenConfigurationParams = TokenConfigMap.getInstance().getValue(prefix);

		if (tokenConfigurationParams == null) {
			tokenConfigurationParams = new TokenConfigurationParams();
			tokenConfigurationParams.setClient_id(
					ConfigProvider.getConfig().getValue(prefix.toLowerCase() + ".client_id", String.class));
			tokenConfigurationParams.setClient_secret(
					ConfigProvider.getConfig().getValue(prefix.toLowerCase() + ".client_secret", String.class));
			tokenConfigurationParams.setGrant_type(
					ConfigProvider.getConfig().getValue(prefix.toLowerCase() + ".grant_type", String.class));
			tokenConfigurationParams
					.setScope(ConfigProvider.getConfig().getValue(prefix.toLowerCase() + ".scope", String.class));
			TokenConfigMap.getInstance().setValue(prefix, tokenConfigurationParams);
		}

		this.tokenConfigurationParams  = tokenConfigurationParams;
		
		return this;
	}

}
