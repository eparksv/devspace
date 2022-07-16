package our.portfolio.devspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DevspaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevspaceApplication.class, args);
    }

}
