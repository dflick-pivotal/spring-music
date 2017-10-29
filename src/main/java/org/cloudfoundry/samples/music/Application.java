package org.cloudfoundry.samples.music;

import org.cloudfoundry.samples.music.config.SpringApplicationContextInitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Configuration
	@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
    @Profile("gemfire")
    protected class Config {
    }
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).initializers(new SpringApplicationContextInitializer())
				.application().run(args);
	}
}