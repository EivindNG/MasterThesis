package crypto;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class SymmetricKey {

    private SecretKeySpec SEK;
    private IvParameterSpec IV;

    public SymmetricKey(byte[] KeyBytes) {

        byte[] SEKbytes = Arrays.copyOfRange(KeyBytes, 0, 32);
        byte[] IVbytes = Arrays.copyOfRange(KeyBytes, 32, 48);

        this.SEK = new SecretKeySpec(SEKbytes, 0, SEKbytes.length, "AES");
        this.IV = new IvParameterSpec(IVbytes);
    }

    public SecretKeySpec getSEK() {
        return SEK;
    }

    public IvParameterSpec getIV() {
        return IV;
    }
}
