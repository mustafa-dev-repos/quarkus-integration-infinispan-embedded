package io.quarkus.infinispan.embedded.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.arc.config.ConfigProperties;


@ConfigProperties(prefix = "infinispan")
public class InfinispanConfiguration {

	@ConfigProperty(defaultValue = "repl")
	private String cacheName;
	
	@ConfigProperty(defaultValue = "nodename-1")
	private String nodeName;
	
	@ConfigProperty(defaultValue = "false")
	private boolean useXmlConfig;
	

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public boolean isUseXmlConfig() {
		return useXmlConfig;
	}

	public void setUseXmlConfig(boolean useXmlConfig) {
		this.useXmlConfig = useXmlConfig;
	}
	
	
}
