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

        Security.addProvider(new BouncyCastleProvider());
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");
        KeyPairGenerator g = KeyPairGenerator.getInstance("EC", "BC");
        g.initialize(ecSpec, new SecureRandom());
        this.pair = g.generateKeyPair();
    }
    public KeyPair getPair() {
        return pair;
    }
}

/*
http://www.bouncycastle.org/wiki/display/JA1/Elliptic+Curve+Key+Pair+Generation+and+Key+Factories
https://www.bouncycastle.org/specifications.html
https://people.eecs.berkeley.edu/~jonah/bc/org/bouncycastle/crypto/params/IESParameters.html

https://github.com/bcgit/bc-java/blob/master/prov/src/test/java/org/bouncycastle/jce/provider/test/ECIESTest.java
*/