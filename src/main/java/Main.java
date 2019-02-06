import Initiator.Initiator;
import Responder.Responder;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Responder> responders;

    public static void main(String[] args) throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException,
            NoSuchProviderException {


        Initiator test = new Initiator(responders);

    }
}