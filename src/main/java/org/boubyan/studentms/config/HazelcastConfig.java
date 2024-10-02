package org.boubyan.studentms.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelcastConfig {

	private Integer cacheTimeToLiveSeconds = 60;

	@Bean
	public HazelcastInstance hazelcastInstance() {
		Config config = new Config();
		// Add custom configuration here
		config.addMapConfig(new MapConfig().setName("courses").setTimeToLiveSeconds(cacheTimeToLiveSeconds));
		return Hazelcast.newHazelcastInstance(config);
	}
}
