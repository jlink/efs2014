package caesar.schritt2;

public class CaesarCipher {

    public static final int ALPHABET_LENGTH = 'z' - 'a' + 1;

    /**
     * @param unencrypted Any character from lower case 'a' to lower case 'z'
     * @param key An integer between 0 and 25
     */
    public char encrypt(char unencrypted, int key) {
        assert unencrypted >= 'a' && unencrypted <= 'z';
        assert key >= 0 && key <= 25;

        int shiftedCharacter = unencrypted + key;
        if (shiftedCharacter > 'z')
            shiftedCharacter -= ALPHABET_LENGTH;
        return (char) shiftedCharacter;
    }
}
