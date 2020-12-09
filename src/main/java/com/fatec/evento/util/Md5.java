package com.fatec.evento.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5 {

	public static String criptografar(String senha) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, messageDigest.digest(senha.getBytes()));
			return hash.toString(16);
		} catch (Exception e) {
			return "";
		}
	}
	
}
