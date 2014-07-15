package caesar.spock

import org.junit.Before
import org.junit.Test

public class CaesarCipherTest {

    CaesarCipher cipher

    @Before
    public void setUp() throws Exception {
        cipher = new CaesarCipher()
    }

    @Test
    public void encryptingWithKey0LeavesCharacterUnchanged() {
        assert cipher.encrypt('a' as char, 0) == 'a' as char
        assert cipher.encrypt('z' as char, 0) == 'z' as char
    }

    @Test
    public void encryptingWithPositiveKeyShiftsByKeyInAlphabet() {
        assert cipher.encrypt('a' as char, 1) == 'b' as char
        assert cipher.encrypt('a' as char, 3) == 'd' as char
        assert cipher.encrypt('g' as char, 2) == 'i' as char
        assert cipher.encrypt('k' as char, 15) == 'z' as char
    }

    @Test
    public void shiftingBeyondZWillWrapAroundInAlphabet() {
        assert cipher.encrypt('z' as char, 1) == 'a' as char
        assert cipher.encrypt('y' as char, 5) == 'd' as char
        assert cipher.encrypt('b' as char, 25) == 'a' as char
    }
}
