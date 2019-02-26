package crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.*;

public class Decryption {

    public static SymmetricKey Decrypt(Encryption data, PrivateKey privateKey) throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            NoSuchProviderException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException, IOException, ClassNotFoundException {

        Cipher cipher = Cipher.getInstance("ECIES","BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] combinedDecryptedData = cipher.doFinal(data.getCipherText());

        ByteArrayInputStream inputStream = new ByteArrayInputStream(combinedDecryptedData);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        SymmetricKey decryptedData = (SymmetricKey) objectInputStream.readObject();

        return decryptedData;
    }
}
