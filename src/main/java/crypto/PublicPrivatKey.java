package crypto;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;

import java.security.*;


public class PublicPrivatKey {

    private KeyPair pair;

    public PublicPrivatKey() throws
            NoSuchProviderException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {

        KeyPairGenerator g = KeyPairGenerator.getInstance("EC", "BC");
        g.initialize(Constants.CURVE_SPEC, new SecureRandom());
        this.pair = g.generateKeyPair();
    }
    public KeyPair getPair() {
        return pair;
    }
}
