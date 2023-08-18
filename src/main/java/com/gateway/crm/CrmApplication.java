package com.gateway.crm;

import com.gateway.crm.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class
})
@EnableConfigurationProperties({ApplicationProperties.class})
public class CrmApplication implements WebMvcConfigurer {
	private static final Logger log = LoggerFactory.getLogger(CrmApplication.class);

	public static void main(String[] args) {
//		SpringApplication.run(AxisApplication.class, args);
		SpringApplication app = new SpringApplication(CrmApplication.class);
		Environment env = app.run(args).getEnvironment();
		logApplicationStartup(env);
	}

	private static void logApplicationStartup(Environment env) {
		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if (StringUtils.hasText(contextPath)) {
			contextPath = "/";
		}
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("Hostname of this machine could not be determined, using `localhost` as fallback");
		}

		String textBlock = "\n--------------------------------------------------------------------------------------------\n\t"
				+ "Application '{}' is running! Access URLs:\n\t"
				+ "\t\thttp://localhost:{}{}\n\t"
				+ "External: \thttp://{}:{}{}\n\t"
				+ "External: \thttps://{}:{}{}\n\t"
				+ "Profile(s): \t{}\n"
				+ "--------------------------------------------------------------------------------------------------";
		log.info(textBlock,
				env.getProperty("application.name"),
				contextPath,
				serverPort, contextPath,
				hostAddress, serverPort, contextPath,
				hostAddress, serverPort, contextPath,
				env.getActiveProfiles());
	}
}
