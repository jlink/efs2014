package caesar.schritt2;

public class CaesarCipher {

    public static final int ALPHABET_LENGTH = 'z' - 'a' + 1;

    public char encrypt(char unencrypted, int key) {
        int shiftedCharacter = unencrypted + key;
        if (shiftedCharacter > 'z')
            shiftedCharacter -= ALPHABET_LENGTH;
        return (char) shiftedCharacter;
    }
}
