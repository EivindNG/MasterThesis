package entities;

import crypto.*;
import org.bouncycastle.math.ec.ECPoint;
import util.IdMaker;
import util.PublicKeyList;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.util.ArrayList;
import java.util.HashMap;

import static com.sun.tools.doclint.Entity.Tau;

public class Initiator extends AbstractEntity{


    private Key encryptionKey;
    private String testKey = "testtesttesttest";
    private HashMap<AbstractEntity, PublicKey> parties;
    private SymmetricKey SharedEncryptionKey;


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

        SecureRandom random = new SecureRandom();
        byte KeyBytes[] = new byte[48];
        random.nextBytes(KeyBytes);

        this.SharedEncryptionKey = new SymmetricKey(KeyBytes);

        for (AbstractEntity  entity: PublicKeyList.getKeyList().keySet()){

            if (entity instanceof Responder){
                Responder responder = (Responder) entity;
                Encryption encryptedKey = new Encryption(responder.getPkSk().getPair().getPublic(), SharedEncryptionKey);

            }
            else {
                continue;
            }
        }



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
