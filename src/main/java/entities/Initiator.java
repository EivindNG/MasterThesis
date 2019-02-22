package entities;

import crypto.Constants;
import crypto.Encryption;
import crypto.PublicPrivatKey;
import crypto.Signing;
import org.bouncycastle.math.ec.ECPoint;
import util.IdMaker;
import util.PublicKeyList;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Initiator extends AbstractEntity{


    private Key encryptionKey;
    private String testKey = "testtesttesttest";
    private HashMap<AbstractEntity, PublicKey> parties;


    public Initiator() throws
            NoSuchAlgorithmException,
            NoSuchProviderException,
            InvalidAlgorithmParameterException {

        PublicPrivatKey publicPrivatKey = new PublicPrivatKey();

        this.SkPk = publicPrivatKey.getPair();
        this.id = IdMaker.getNextId();
        PublicKeyList.getKeyList().put(this, SkPk.getPublic());



        ECPoint ponit = Constants.CURVE_SPEC.getG().multiply(new BigInteger(256, new SecureRandom()));

        System.out.println("punkt" + ponit);
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

    public KeyPair getPkSk() {
        return SkPk;
    }
}
