package entities;

import java.math.BigInteger;
import java.security.KeyPair;


public abstract class AbstractEntity {
    protected BigInteger id;
    protected KeyPair SkPk;

    public BigInteger getId() {
        return id;
    }

    public KeyPair getSkPk() {
        return SkPk;
    }
}
