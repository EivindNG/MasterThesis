package crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;

public class VerifySignature {

    public static boolean Verify(byte[] signature, PublicKey publicKey, byte[] data) throws
            NoSuchAlgorithmException,
            InvalidKeyException,
            SignatureException,
            NoSuchProviderException {

        Security.addProvider(new BouncyCastleProvider());
        Signature sign = Signature.getInstance("SHA256withECDSA","BC");
        sign.initVerify(publicKey);
        sign.update(data);

        return sign.verify(signature);

    }
}
