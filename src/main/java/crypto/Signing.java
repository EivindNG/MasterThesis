package crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;

public class Signing {

    public static byte [] Sign(KeyPair pair, byte[] Stufftosign) throws
            NoSuchAlgorithmException,
            InvalidKeyException,
            SignatureException,
            NoSuchProviderException {

        Security.addProvider(new BouncyCastleProvider());
        Signature sign = Signature.getInstance("SHA256withECDSA","BC");
        sign.initSign(pair.getPrivate());
        sign.update(Stufftosign);

        return sign.sign();
    }
}