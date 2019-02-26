package entities;

import crypto.*;
import entities.Initiator;
import util.IdMaker;
import util.PublicKeyList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.*;
import java.util.Base64;


public class Responder extends AbstractEntity{

    private SymmetricKey SharedEncryptionKey;


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
            SignatureException,
            IOException,
            ClassNotFoundException {

        if(VerifySignature.Verify(sign, PublicKeyList.getKeyList().get(initiator), data.getCipherText())){

            this.SharedEncryptionKey = Decryption.Decrypt(data,this.SkPk.getPair().getPrivate());

            System.out.println("encryption key: "
                    + Base64.getEncoder().encodeToString(SharedEncryptionKey.getSEK().getEncoded())
                    + "\nIV: "
                    + Base64.getEncoder().encodeToString((new IvParameterSpec(SharedEncryptionKey.getIVbytes()).getIV()))
            );
        }
    }
}
