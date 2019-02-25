package entities;

import entities.Initiator;
import crypto.Decryption;
import crypto.Encryption;
import crypto.PublicPrivatKey;
import crypto.VerifySignature;
import util.IdMaker;
import util.PublicKeyList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;


public class Responder extends AbstractEntity{

    private byte[] encryptionKey;
    PublicPrivatKey PkSk;

    public Responder() throws
            InvalidAlgorithmParameterException,
            NoSuchAlgorithmException,
            NoSuchProviderException {

        this.SkPk = new PublicPrivatKey();
        this.id = IdMaker.getNextId();
        PublicKeyList.getKeyList().put(this, SkPk.getPair().getPublic());
    }

    public void ConfirmEncryptionKey(byte[] sign, Encryption data, Initiator initiator) throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchProviderException,
            InvalidKeyException,
            SignatureException {

        if(VerifySignature.Verify(sign, initiator.getPkSk().getPair().getPublic(), data.getCipherText())){

            this.encryptionKey = Decryption.Decrypt(data,PkSk.getPair().getPrivate());
            String key = new String(encryptionKey);
            System.out.println("encryption key: " + key);
        }
    }
}
