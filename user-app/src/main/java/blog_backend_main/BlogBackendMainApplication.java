package blog_backend_main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class BlogBackendMainApplication {

	public static void main(String[] args) {
		try {
			log.info("Blog Backend Main Service starting");
			SpringApplication.run(BlogBackendMainApplication.class, args);
			log.info("Blog Backend Main Service started Successfully....");
		} catch (Exception e) {
			log.error("Error while starting blog backend service");
		}
	}

}
