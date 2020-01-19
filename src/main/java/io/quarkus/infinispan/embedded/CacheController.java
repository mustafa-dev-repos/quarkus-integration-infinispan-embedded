package io.quarkus.infinispan.embedded;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.infinispan.embedded.listener.InfinispanStartupListener;

@Path("/cache")
public class CacheController {

	@Inject
	InfinispanStartupListener infinispanStartupListener;

	@Path("add_entry/{key}/{value}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String customCluster(@PathParam("key") String key, @PathParam("value") String value) throws IOException {

		infinispanStartupListener.putCache(key, value);

		return "Success";
	}

	@Path("close_cluster")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String closeCluster() throws IOException {
		infinispanStartupListener.cacheManagerClose();
		return "Success";
	}

	@Path("Print_Local_Cluster_Cache_Values")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String listCluster() throws IOException {
		infinispanStartupListener.getCacheContents()
				.forEach(entry -> System.out.println("Sonuc : " + entry.getKey() + " " + entry.getValue()));
		return "Success";
	}

}
