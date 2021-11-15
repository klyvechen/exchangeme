package com.sporty.digitalcurrency.signature;

import java.security.KeyPair;

public interface SportySignature {

    /**
     * generate the public/private key pair
     * @return public/private key pair
     */
    KeyPair generatorKeys();

    /**
     * return the signature for message signed by the private key
     * @param message message for sign
     * @param privateKey key to sign the message
     * @return String
     */
    String sing(String message, String privateKey);

    /**
     * verify the message by the public key with the signature
     * @param message
     * @param signature
     * @param publicKey
     * @return true if the message is signed by the private key
     */
    boolean verify(String message, String signature, String publicKey);

}
