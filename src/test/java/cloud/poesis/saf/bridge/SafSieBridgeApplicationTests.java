package cloud.poesis.saf.bridge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Proves the application context wires before any sourcing logic exists.
 *
 * @author Clément Cazaud
 * @since 1.0.0
 */
@SpringBootTest
class SafSieBridgeApplicationTests {

    @Test
    void contextLoads() {
        assertThat(SafSieBridgeApplication.class).isNotNull();
    }
}
