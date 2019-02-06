package crypto;

import org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class Decryption {

    public static byte[] Decrypt(Encryption data, PrivateKey privateKey) throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            NoSuchProviderException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {

        Security.addProvider(new BouncyCastleProvider());
        IESCipher c1 = new org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher.ECIES();

        Cipher cipher = Cipher.getInstance("ECIES","BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data.getCipherText());
    }
}
