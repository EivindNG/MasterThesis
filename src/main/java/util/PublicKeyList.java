package util;

import entities.AbstractEntity;

import java.security.PublicKey;
import java.util.HashMap;

public class PublicKeyList {

    static HashMap<AbstractEntity, PublicKey> KeyList = new HashMap<>();

    public static HashMap<AbstractEntity, PublicKey> getKeyList() {
        return KeyList;
    }
}
