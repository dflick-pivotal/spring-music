package org.cloudfoundry.samples.music.config.data;

import java.util.Collections;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.cloudfoundry.samples.music.domain.Album;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.cache.GemfireCacheManager;
import org.springframework.data.gemfire.client.ClientCacheFactoryBean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.client.PoolFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.support.ConnectionEndpoint;

@Configuration
@Profile("gemfire-local")
@EnableGemfireRepositories("org.cloudfoundry.samples.music.repositories.gemfire")
public class GemfireLocalConfig {

	private static final Log logger = LogFactory.getLog(GemfireLocalConfig.class);

	@Bean
	Properties gemfireProperties(@Value("${gemfire.log.level:config}") String logLevel) {
		Properties gemfireProperties = new Properties();
		gemfireProperties.setProperty("log-level", logLevel);
		return gemfireProperties;
	}

	@Bean
	PoolFactoryBean gemfirePool(@Value("${gemfire.cache.locator.host:localhost}") String host,
			@Value("${gemfire.cache.locator.port:10334}") int port) {
		PoolFactoryBean gemfirePool = new PoolFactoryBean();
		gemfirePool.setLocators(Collections.singletonList(new ConnectionEndpoint(host, port)));
		return gemfirePool;
	}

	@Bean
	ClientCacheFactoryBean gemfireCache(Properties gemfireProperties) {
		ClientCacheFactoryBean gemfireCache = new ClientCacheFactoryBean();
		gemfireCache.setProperties(gemfireProperties);
		return gemfireCache;
	}

	@Bean(name = "Album")
	ClientRegionFactoryBean<String, Album> albumRegion(ClientCacheFactoryBean gemfireCache, PoolFactoryBean gemfirePool)
			throws Exception {
		ClientRegionFactoryBean<String, Album> albumRegion = new ClientRegionFactoryBean<>();

		albumRegion.setCache(gemfireCache.getObject());
		albumRegion.setName("Album");
		albumRegion.setPool(gemfirePool.getPool());
		albumRegion.setShortcut(ClientRegionShortcut.PROXY);
		logger.info("GEMFIRE LOCAL CONFIG");
		return albumRegion;
	}

	@Bean
	GemfireCacheManager cacheManager(ClientCacheFactoryBean gemfireCache) throws Exception {
		GemfireCacheManager cacheManager = new GemfireCacheManager();
		cacheManager.setCache(gemfireCache.getObject());
		return cacheManager;
	}

}
