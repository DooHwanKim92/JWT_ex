package jwt.sb;

import io.jsonwebtoken.security.Keys;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
class SbApplicationTests {

	@Value("${custom.jwt.secretkey}")
	private String secretKey;

	@Test
	@DisplayName("Check Secret Key")
	void checkSecretKey() {
		String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKey.getBytes());

		SecretKey testSecretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());

		assertThat(testSecretKey).isNotNull();
		System.out.println(testSecretKey);
	}


	@Test
	void contextLoads() {
	}

}
