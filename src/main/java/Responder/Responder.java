package Responder;

import crypto.Encryption;
import crypto.PublicPrivatKey;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


public class Responder {

    private Key encryptionKey;
    PublicPrivatKey PkSk;

    public Responder() throws
            InvalidAlgorithmParameterException,
            NoSuchAlgorithmException,
            NoSuchProviderException {

        this.PkSk = new PublicPrivatKey();
    }

    public void ConfirmEncryptionKey(byte[] sign, Encryption data){

    }
}
