package com.kitchen.AuthController;

import java.security.MessageDigest;

public class SimpleMD5 {

	static StringBuilder builder;;

	public String hashing(String password) {
		String hashString = "";
		if (password != null) {
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				md5.update(password.getBytes());
				byte[] bytes = md5.digest();
				builder = new StringBuilder();

				for (int i = 0; i < bytes.length; i++) {
					builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				hashString = builder.toString();

			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return hashString;
	}

}
