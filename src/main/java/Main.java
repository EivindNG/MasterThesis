import Initiator.Initiator;
import Responder.Responder;

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

        ArrayList<Responder> responders = new ArrayList<Responder>();
        Responder test1 = new Responder();
        responders.add(test1);
        Initiator test = new Initiator(responders);
        test.EncryptAndSendKey();
    }
}