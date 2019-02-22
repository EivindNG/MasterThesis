import entities.Initiator;
import entities.Responder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
            NoSuchPaddingException {

        Security.addProvider(new BouncyCastleProvider());

        ArrayList<Responder> responders = new ArrayList<Responder>();
        Responder test1 = new Responder();
        responders.add(test1);
        Initiator test = new Initiator(responders);
        test.EncryptAndSendKey();
    }
}