package caesar.spock

import spock.lang.Specification
import spock.lang.Unroll


class CaesarCipherSpock extends Specification {

    def cipher

    void setup() throws Exception {
        cipher = new CaesarCipher()
    }

    @Unroll
    def "encrypting #unencrypted with key #key leaves character unchanged"() {
        expect:
            assertEncryption(unencrypted, key, encrypted)

        where:
            unencrypted | key   | encrypted
            'a'         | 0     | 'a'
            'z'         | 0     | 'z'
    }

    @Unroll
    def "encrypting #unencrypted with positive key #key shifts character to #encrypted"() {
        expect:
            assertEncryption(unencrypted, key, encrypted)

        where:
            unencrypted | key   | encrypted
            'a'         | 1     | 'b'
            'a'         | 3     | 'd'
            'g'         | 2     | 'i'
            'k'         | 15    | 'z'
    }

    @Unroll
    def "shifting #unencrypted with key #key wraps around in alphabet to #encrypted"() {
        expect:
            assertEncryption(unencrypted, key, encrypted)

        where:
            unencrypted | key   | encrypted
            'z'         | 1     | 'a'
            'y'         | 5     | 'd'
            'b'         | 25    | 'a'
    }

    private void assertEncryption(unencrypted, key, encrypted) {
        assert cipher.encrypt(unencrypted as char, key) == encrypted as char
    }

}