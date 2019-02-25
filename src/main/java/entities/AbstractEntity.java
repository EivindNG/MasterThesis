package entities;

import crypto.PublicPrivatKey;

import java.math.BigInteger;
import java.security.PublicKey;


public abstract class AbstractEntity {
    protected BigInteger id;
    protected PublicPrivatKey SkPk;

    public BigInteger getId() {
        return id;
    }

    public PublicKey getPk() {
        return SkPk.getPair().getPublic();
    }
}
