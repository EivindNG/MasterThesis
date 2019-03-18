import entities.Initiator;
import entities.Responder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException,
            NoSuchProviderException,
            IllegalBlockSizeException,
            InvalidKeyException,
            BadPaddingException,
            SignatureException,
            NoSuchPaddingException,
            IOException,
            ClassNotFoundException {

        Security.addProvider(new BouncyCastleProvider());

        Responder test1 = new Responder();

        Initiator test = new Initiator();
        test.EncryptAndSendKey();
    }
}