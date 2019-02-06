package Initiator;

import Responder.Responder;
import crypto.PublicPrivatKey;


import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;

public class Initiator {

    PublicPrivatKey PkSk;
    ArrayList<Responder> responders;
    Key encryptionKey;

    public Initiator(ArrayList<Responder> responders) throws
            NoSuchAlgorithmException,
            NoSuchProviderException,
            InvalidAlgorithmParameterException {

        this.PkSk = new PublicPrivatKey();
        this.responders = responders;

    }

    public PublicPrivatKey getPkSk() {
        return PkSk;
    }
}
