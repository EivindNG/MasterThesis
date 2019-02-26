package crypto;

import org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.*;

public class Encryption {
    private byte[] cipherText;

    public Encryption(PublicKey pubkey, SymmetricKey symmetricKey) throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            NoSuchProviderException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException, IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(outputStream);
        objectStream.writeObject(symmetricKey);

        byte dataToBeEncrypted[] = outputStream.toByteArray();


        Cipher cipher = Cipher.getInstance("ECIES","BC");
        cipher.init(Cipher.ENCRYPT_MODE, pubkey);
        this.cipherText = cipher.doFinal(dataToBeEncrypted);

    }

    public byte[] getCipherText() {
        return cipherText;
    }

    /*
    https://ieeexplore.ieee.org/document/6024664
    https://www.codeproject.com/Tips/1071190/Encryption-and-Decryption-of-Data-using-Elliptic-C

    https://link.springer.com/content/pdf/10.1007%2F3-540-39799-X_12.pdf
    https://www.sciencedirect.com/science/article/pii/S1877050915006705 public key encryption protocol

    */
}
