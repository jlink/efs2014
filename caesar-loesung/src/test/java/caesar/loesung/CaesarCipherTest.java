package caesar.loesung;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarCipherTest {

    private CaesarCipher cipher;

    @Before
    public void setUp() throws Exception {
        cipher = new CaesarCipher();
    }

    @Test
    public void encryptingWithKey0LeavesCharacterUnchanged() {
        assertEquals('a', cipher.encrypt('a', 0));
        assertEquals('z', cipher.encrypt('z', 0));
    }

    @Test
    public void encryptingWithPositiveKeyShiftsByKeyInAlphabet() {
        assertEquals('b', cipher.encrypt('a', 1));
        assertEquals('d', cipher.encrypt('a', 3));
        assertEquals('i', cipher.encrypt('g', 2));
        assertEquals('z', cipher.encrypt('k', 15));
    }

    @Test
    public void shiftingBeyondZWillWrapAroundInAlphabet() {
        assertEquals('a', cipher.encrypt('z', 1));
        assertEquals('d', cipher.encrypt('y', 5));
        assertEquals('a', cipher.encrypt('b', 25));
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

    private int anyKey() {
        return 1;
    }
}
