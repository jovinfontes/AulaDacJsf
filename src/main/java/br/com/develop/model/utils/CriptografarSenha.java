package br.com.develop.model.utils;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class CriptografarSenha implements Serializable{

	private static final long serialVersionUID = 1L;

	public CriptografarSenha() {

    }

    @SuppressWarnings("restriction")
    public String encripta(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(senha.getBytes());            
			BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(digest.digest());
        } catch (NoSuchAlgorithmException ns) {
            ns.printStackTrace();
            return senha;
        }
    }
}
