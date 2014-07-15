package test1;

import org.junit.*;

import static org.mockito.Mockito.*;

public class MeinTest {
	@Test
	public void einTest() {
		Assert.assertEquals("hallo", new MeineKlasse().hallo());
	}

	@Test
	public void nochEinTest() {
		MeineKlasse mock = mock(MeineKlasse.class);
		when(mock.hallo()).thenReturn("ciao");
		Assert.assertEquals("ciao", mock.hallo());
	}
}