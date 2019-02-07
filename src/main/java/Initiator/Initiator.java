package Initiator;

import Responder.Responder;
import crypto.Encryption;
import crypto.PublicPrivatKey;
import crypto.Signing;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.ArrayList;

public class Initiator {

    private PublicPrivatKey PkSk;
    private ArrayList<Responder> responders;
    private Key encryptionKey;
    private String testKey = "testtesttesttest";

    public Initiator(ArrayList<Responder> responders) throws
            NoSuchAlgorithmException,
            NoSuchProviderException,
            InvalidAlgorithmParameterException {

        this.PkSk = new PublicPrivatKey();
        this.responders = responders;

    }

    public void EncryptAndSendKey() throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchProviderException,
            InvalidKeyException,
            SignatureException {

        for(Responder responder: responders) {
            Encryption encryptedKey = new Encryption(responder.getPkSk().getPair().getPublic(),testKey.getBytes());
            byte [] signature = Signing.Sign(PkSk.getPair(),encryptedKey.getCipherText());

            responder.ConfirmEncryptionKey(signature,encryptedKey,this);
        }
    }

    public PublicPrivatKey getPkSk() {
        return PkSk;
    }
}
