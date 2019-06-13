package com.jogtrack.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.Test;

import com.jogtrack.exception.ApplicationException;

public class PasswordUtilTest {
	PasswordUtil passwordUtil = new PasswordUtil();

	@Test
	public void shouldGenerateAuthToken() {
		String authToken = passwordUtil.generateAuthToken();
		assertTrue(!authToken.isEmpty());
		assertTrue(authToken.length() == 32);
	}

	@Test
	public void shouldGetHashedPassword() throws NoSuchAlgorithmException {
		String passwordToHash = "abc";
		String saltString = "0rFkJ0TIXYhPMlvcLfR/aw==";
		byte[] salt = Base64.getDecoder().decode(saltString);
		String hashedPassword = passwordUtil.getHashedPassword(passwordToHash, salt);
		assertEquals(
				"0rFkJ0TIXYhPMlvcLfR/aw==:zcBcZhk7MJrl55PplI+ESd1dc3Ac6oOInNxTFS/n0Jpg24W5hChc7U8eiNDt7DYJTHvq62qeBcjBz/i1VOcf8g==",
				hashedPassword);
	}

	@Test
	public void shouldGenerateHashedPassword() {

	}

	@Test
	public void shouldVerifyUserPassword() throws ApplicationException {
		String hashedPassword = "0rFkJ0TIXYhPMlvcLfR/aw==:zcBcZhk7MJrl55PplI+ESd1dc3Ac6oOInNxTFS/n0Jpg24W5hChc7U8eiNDt7DYJTHvq62qeBcjBz/i1VOcf8g==";
		String correctPassword = "abc";
		assertTrue(passwordUtil.verifyUserPassword(hashedPassword, correctPassword));
		String incorrectPassword = "def";
		assertFalse(passwordUtil.verifyUserPassword(hashedPassword, incorrectPassword));
	}
}
