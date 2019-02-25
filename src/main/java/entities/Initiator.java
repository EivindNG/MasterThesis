package entities;

import crypto.*;
import org.bouncycastle.math.ec.ECPoint;
import util.IdMaker;
import util.PublicKeyList;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.util.ArrayList;
import java.util.HashMap;



public class Initiator extends AbstractEntity{

    private HashMap<AbstractEntity, PublicKey> parties;
    private SymmetricKey SharedEncryptionKey;


    public Initiator() throws
            NoSuchAlgorithmException,
            NoSuchProviderException,
            InvalidAlgorithmParameterException {

        this.SkPk = new PublicPrivatKey();
        this.id = IdMaker.getNextId();
        PublicKeyList.getKeyList().put(this, SkPk.getPair().getPublic());



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
            SignatureException, IOException {

        SecureRandom random = new SecureRandom();
        byte KeyBytes[] = new byte[48];
        random.nextBytes(KeyBytes);

        this.SharedEncryptionKey = new SymmetricKey(KeyBytes);

        for (AbstractEntity  entity: PublicKeyList.getKeyList().keySet()){

            if (entity instanceof Responder){
                Responder responder = (Responder) entity;
                Encryption encryptedKey = new Encryption(responder.getPk(), SharedEncryptionKey);

                byte[] signature = Signing.Sign(SkPk.getPair(),encryptedKey.getCipherText());
                responder.ConfirmEncryptionKey(signature,encryptedKey,this);
            }

            else {
                continue;
            }
        }
    }
}
