package com.prueba.user_api.Util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtil {
    private static final String KEY = "1234567890123456";

    public static String encrypt(String password){

        try{

            SecretKeySpec key =
                    new SecretKeySpec(KEY.getBytes(),"AES");

            Cipher cipher =
                    Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE,key);

            return Base64.getEncoder()
                    .encodeToString(
                            cipher.doFinal(password.getBytes())
                    );

        }catch(Exception e){

            throw new RuntimeException(e);

        }

    }
}
