package io.quarkus.restclient.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenConfigMap {
	
	private final ConcurrentMap<String, TokenConfigurationParams> concurrentMap = new ConcurrentHashMap<String, TokenConfigurationParams>();
	
	private static TokenConfigMap instance=null;
	
	private TokenConfigMap(){
	}
	
	public static synchronized TokenConfigMap getInstance(){
        if(instance == null){
            instance = new TokenConfigMap();
        }
        return instance;
    }
    
	public void setValue(String key, TokenConfigurationParams value) {
		concurrentMap.put(key, value);
	}
	
	public TokenConfigurationParams getValue(String key) {
		return concurrentMap.get(key);
	}

}
