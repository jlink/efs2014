package caesar.loesung;

public class CaesarCipher {

    public static final int ALPHABET_LENGTH = 'z' - 'a' + 1;

    /**
     * @param unencrypted Any character from lower case 'a' to lower case 'z'
     * @param key An integer between 0 and 25
     */
    public char encrypt(char unencrypted, int key) {
        int shiftedCharacter = unencrypted + key;
        if (shiftedCharacter > 'z')
            shiftedCharacter -= ALPHABET_LENGTH;
        return (char) shiftedCharacter;
    }

    /**
     * @param clearText Any string
     * @param key An integer between 0 and 25
     */
    public String encryptText(String clearText, int key) {
        String encryptedText = "";
        for (char unencrypted : clearText.toCharArray()) {
            encryptedText += encrypt(unencrypted, key);
        }
        return encryptedText;
    }
}
