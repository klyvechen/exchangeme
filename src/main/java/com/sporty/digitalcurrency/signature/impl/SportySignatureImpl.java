package com.sporty.digitalcurrency.signature.impl;

import com.sporty.digitalcurrency.signature.SportySignature;
import lombok.extern.log4j.Log4j2;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Log4j2
public class SportySignatureImpl implements SportySignature {

    public static final String SHA_256_SIGN_ALGORITHMS = "SHA256withRSA";

    private static final String ALGORITHM = "RSA";

    @Override
    public KeyPair generatorKeys() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String sing(String message, String privateKey) {
        try {
            byte[] privateBytes = Base64.getDecoder().decode(privateKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PrivateKey priKey = keyFactory.generatePrivate(keySpec);
            Signature sigEng = Signature.getInstance(SHA_256_SIGN_ALGORITHMS);
            sigEng.initSign(priKey);
            sigEng.update(message.getBytes(StandardCharsets.UTF_8.name()));
            byte[] signature = sigEng.sign();
            return Base64.getEncoder().encodeToString(signature);
        } catch (Exception e) {
            log.error("#Sign error.Cause:" + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean verify(String content, String sign, String publicKey) {
        try {
            byte[] publicBytes = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance(SHA_256_SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8.name()));
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            log.error("#DoCheck sign error.Cause:" + e.getMessage(), e);
        }
        return false;
    }

}
