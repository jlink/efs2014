package caesar.loesung;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CaesarCipherTextEncryptionTest {

    private CaesarCipher cipher;

    @Before
    public void setUp() throws Exception {
        cipher = new CaesarCipher();
    }

    @Test
    public void encryptingEmptyTextYieldsEmptyText() {
        assertEquals("", cipher.encryptText("", anyKey()));
    }

    @Test
    public void encryptingTextWithOnlyKnownCharacters() {
        assertEquals("b", cipher.encryptText("a", 1));
        assertEquals("de", cipher.encryptText("ab", 3));
        assertEquals("zgzkkny", cipher.encryptText("ahalloz", 25));
    }

    @Test
    public void lowerCaseLatinCharsAreInAlphabet() throws Exception {
        assertTrue(cipher.isAccepted('a'));
        assertTrue(cipher.isAccepted('z'));
    }

    @Test
    public void charsNotInLowerCaseLatinAreNotInAlphabet() throws Exception {
        assertFalse(cipher.isAccepted((char) ('a' - 1)));
        assertFalse(cipher.isAccepted((char) ('z' + 1)));
    }

    @Test
    public void charsNotAcceptedAreIgnored() throws Exception {
        assertEquals("fge", cipher.encryptText("a bäöü+-\tz", 5));
    }

    @Test
    public void upperCaseCharactersAreEncryptedAsLowerCase() throws Exception {
        assertEquals("zgzkkny", cipher.encryptText("aHalLoZ", 25));
    }

    private int anyKey() {
        return 1;
    }
}
