package cloud.poesis.saf.bridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot entry point for the SAF SIE Bridge.
 *
 * @author Clément Cazaud
 * @since 1.0.0
 */
@SpringBootApplication
public class SafSieBridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafSieBridgeApplication.class, args);
    }
}
