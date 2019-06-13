package com.jogtrack.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.jogtrack.exception.ApplicationException;
import com.jogtrack.exception.ErrorCode;

/**
 * This class has utility methods for generating auth token and hashed passwords
 * 
 * @author raj
 *
 */
@Component
public class PasswordUtil {
	/**
	 * A simple auth token generator
	 * 
	 * @return
	 */
	public String generateAuthToken() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] bytes = new byte[24];
		secureRandom.nextBytes(bytes);
		return java.util.Base64.getEncoder().encodeToString(bytes);
	}

	/**
	 * Generate a hashed password, given a plain-text input password
	 * 
	 * @param passwordToHash
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String generateHashedPassword(String passwordToHash) throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[16];
		secureRandom.nextBytes(salt);

		return getHashedPassword(passwordToHash, salt);
	}

	/**
	 * Generate a hashed password, given a plain-text input password and salt
	 * 
	 * @param passwordToHash
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String getHashedPassword(String passwordToHash, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(JogTrackConstants.SHA_512);
		md.update(salt);

		byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
		String hash = Base64.getEncoder().encodeToString(bytes);
		String saltString = Base64.getEncoder().encodeToString(salt);
		// Store SALT:HASH in the 'PASSWORD' column in the database
		return saltString + JogTrackConstants.COLON + hash;
	}

	public boolean verifyUserPassword(String hashedPassword, String inputPassword) throws ApplicationException {
		boolean isValidUser = false;
		try {
		String[] arr = hashedPassword.split(JogTrackConstants.COLON);
		String saltString = arr[0];
		String storedPasswordHash = arr[1];
		byte[] salt = Base64.getDecoder().decode(saltString);

		String inputPasswordHash = getHashedPassword(inputPassword, salt);
		if (inputPasswordHash.equals(saltString + ":" + storedPasswordHash))
			isValidUser = true;
		} catch (NoSuchAlgorithmException e) {
			ApplicationException ae = new ApplicationException(ErrorCode.SERVICE_UNEXPECTED_ERROR, ErrorCode.SERVICE_UNEXPECTED_ERROR.name(), e);
			throw ae;
		}
		
		return isValidUser;
	}

}
