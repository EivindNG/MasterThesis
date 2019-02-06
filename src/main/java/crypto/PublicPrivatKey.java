package crypto;
import java.math.BigInteger;
import java.security.*;

import java.security.spec.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.interfaces.IESKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;


import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher;


public class PublicPrivatKey {

    private KeyPair kpU;

    public PublicPrivatKey() throws
            NoSuchProviderException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {


        Security.addProvider(new BouncyCastleProvider());
        IESCipher c1 = new org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher.ECIES();

        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");
        KeyPairGenerator g = KeyPairGenerator.getInstance("EC", "BC");
        g.initialize(ecSpec, new SecureRandom());
        KeyPair pair = g.generateKeyPair();


        Cipher cipher = Cipher.getInstance("ECIES","BC");

        cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
        byte [] data = cipher.doFinal("hei".getBytes());
        System.out.println("kryptert"+ new String(data));

        Cipher cipher2 = Cipher.getInstance("ECIES","BC");
        cipher2.init(Cipher.DECRYPT_MODE, pair.getPrivate());

        System.out.println("dekryptert" + new String(cipher2.doFinal(data)));
    }
}

/*
http://www.bouncycastle.org/wiki/display/JA1/Elliptic+Curve+Key+Pair+Generation+and+Key+Factories
https://www.bouncycastle.org/specifications.html
https://people.eecs.berkeley.edu/~jonah/bc/org/bouncycastle/crypto/params/IESParameters.html

https://github.com/bcgit/bc-java/blob/master/prov/src/test/java/org/bouncycastle/jce/provider/test/ECIESTest.java
*/