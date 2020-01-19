package io.quarkus.infinispan.embedded.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.context.Flag;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import io.quarkus.infinispan.embedded.configuration.InfinispanConfiguration;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class InfinispanStartupListener {

	@Inject
	private InfinispanConfiguration infinispanConfiguration;
	
	private EmbeddedCacheManager cacheManager;
	private Cache<String, String> cache;

	
    public void onStartup(@Observes StartupEvent event) throws IOException {
		cacheManager = createCacheManager(infinispanConfiguration.getNodeName(), infinispanConfiguration.isUseXmlConfig());
		cache = cacheManager.getCache(infinispanConfiguration.getCacheName());
		cache.addListener(new CacheListener());
		System.out.println("Application started.");
    }
    
	private EmbeddedCacheManager createCacheManager(String nodeName, boolean useXmlConfig) throws IOException {
		if (useXmlConfig) {
			return createCacheManagerFromXml(nodeName);
		} else {
			return createCacheManagerProgrammatically(nodeName);
		}
	}

	private EmbeddedCacheManager createCacheManagerProgrammatically(String nodeName) {
		System.out.println("Starting a cache manager with a programmatic configuration");
		DefaultCacheManager cacheManager = new DefaultCacheManager(
				GlobalConfigurationBuilder.defaultClusteredBuilder().defaultCacheName("nodeName").transport()
						.nodeName(nodeName).addProperty("configurationFile", "jgroups.xml").build(),
				new ConfigurationBuilder().clustering().cacheMode(CacheMode.REPL_SYNC).build());
		cacheManager.defineConfiguration("dist",
				new ConfigurationBuilder().clustering().cacheMode(CacheMode.DIST_SYNC).hash().numOwners(2).build());
		return cacheManager;
	}

	private EmbeddedCacheManager createCacheManagerFromXml(String nodeName) throws IOException {
		System.out.println("Starting a cache manager with an XML configuration");
		System.setProperty("nodeName", nodeName);
		return new DefaultCacheManager("infinispan.xml");
	}

	public ArrayList<Map.Entry<String, String>> getCacheContents() {
		ArrayList<Map.Entry<String, String>> entries = new ArrayList<>(
				cache.getAdvancedCache().withFlags(Flag.CACHE_MODE_LOCAL).entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<String, String>>() {
			@Override
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		return entries; 
	}	
	
	public void cacheManagerClose() throws IOException {
		cacheManager.close();
	}

	public void putCache(String key, String value) {
		cache.put(key, value);
	}

}
