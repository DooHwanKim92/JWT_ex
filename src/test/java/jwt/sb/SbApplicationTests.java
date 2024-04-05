package jwt.sb;

import io.jsonwebtoken.security.Keys;
import jwt.sb.global.jwt.JwtProvider;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
class SbApplicationTests {

	@Autowired
	private JwtProvider jwtProvider;

	@Value("${custom.jwt.secretkey}")
	private String originSecretKey;

	@Test
	@DisplayName("Check the secret key")
	void checkSecretKey() {
		assertThat(originSecretKey).isNotNull();
	}

	@Test
	@DisplayName("Secret key encoding test")
	void encodeSecretKey() {
		System.out.println("Original SecretKey : " + originSecretKey);
		String keyBase64Encoded = Base64.getEncoder().encodeToString(originSecretKey.getBytes());

		SecretKey testSecretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());

		assertThat(testSecretKey).isNotNull();
		System.out.println("Encoded SecretKey : " + testSecretKey);
	}

	@Test
	@DisplayName("Create SecretKey by JWT Provider")
	void createSecretKey() {
		SecretKey secretKey = this.jwtProvider.getSecretKey();

		System.out.println(secretKey);
	}

	@Test
	@DisplayName("Test JWT Provider")
	void testSecretKey() {
		SecretKey secretKey1 = this.jwtProvider.getSecretKey();
		SecretKey secretKey2 = this.jwtProvider.getSecretKey();

		System.out.println(secretKey1);
		System.out.println(secretKey2);
	}

	@Test
	@DisplayName("Create Access Token")
	void test6 () {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", 1L);
		claims.put("username", "user1");

		String accessToken = jwtProvider.genToken(claims, 60 * 60 * 3);

		System.out.println("accessToken : " + accessToken);

		assertThat(accessToken).isNotNull();
	}


	@Test
	void contextLoads() {

	}

}
