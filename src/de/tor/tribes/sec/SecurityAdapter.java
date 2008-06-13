/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.sec;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.ssl.OpenSSL;
import org.apache.log4j.Logger;

/**
 *
 * @author Jejkal
 */
public class SecurityAdapter {

    private static Logger logger = Logger.getLogger(SecurityAdapter.class);

    public static String encryptString(String pData, String pPassword) {
        try {
            return new String(OpenSSL.encrypt("des3", pPassword.toCharArray(), pData.getBytes()));
        } catch (Exception e) {
            logger.error("Unknown error while encrypting string", e);
            return null;
        }
    }

    public static String descryptString(String pData, String pPassword) {
        try {
            // Decrypt
            return new String(OpenSSL.decrypt("des3", pPassword.toCharArray(), pData.getBytes()));
        } catch (Exception e) {
            logger.error("Unknown error while decrypting string", e);
            return null;
        }
    }

    public static String hashStringMD5(String pData) {
        String hashed = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pData.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            hashed = hash.toString(16);
        } catch (NoSuchAlgorithmException nsae) {
            // ignore
            logger.warn("Unknown error while hashing string (ignored)", nsae);
        }
        return hashed;
    }
}
