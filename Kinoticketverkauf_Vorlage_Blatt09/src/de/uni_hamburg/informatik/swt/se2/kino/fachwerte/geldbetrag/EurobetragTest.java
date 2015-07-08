package de.uni_hamburg.informatik.swt.se2.kino.fachwerte.geldbetrag;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class EurobetragTest {
	private Eurobetrag zehnKommaDreiEuro = Eurobetrag.valueOf(1003);
	private Eurobetrag nullKommaZweiUndFünfzigEuro = Eurobetrag.valueOf(52);
	private Eurobetrag ZehnKommaFünfUndFünfzigEuro = Eurobetrag.valueOf(1055);
	private Eurobetrag neunKommaEinUndFünfzigEuro = Eurobetrag.valueOf(951);
	private Eurobetrag einsKommaVierEuro = Eurobetrag.valueOf(104);

	@Test
	public void konvertierungsTest() {

		/**
		 * Prüfe die valueOf Methoden mit String, Integer & int als übergegebene
		 * Parameter!
		 */
		try {
			assertEquals(zehnKommaDreiEuro, Eurobetrag.valueOf("10,03"));
			assertEquals(zehnKommaDreiEuro,
					Eurobetrag.valueOf(new Integer(1003)));
			assertEquals(zehnKommaDreiEuro, Eurobetrag.valueOf(1003));
			assertNotEquals(zehnKommaDreiEuro, Eurobetrag.valueOf("10,02"));
			assertNotEquals(zehnKommaDreiEuro, Eurobetrag.valueOf("10,22"));
		} catch (IllegalArgumentException e) {
			fail("Gültiger Parameter erzeugt keinen gültigen Geldbetrag!");
		}

	}

	/**
	 * Teste plus Operation mit Eurobeträgen
	 */
	@Test
	public void plusTest() {
		assertEquals(zehnKommaDreiEuro.plus(nullKommaZweiUndFünfzigEuro),
				ZehnKommaFünfUndFünfzigEuro);
		assertNotEquals(zehnKommaDreiEuro.plus(nullKommaZweiUndFünfzigEuro),
				nullKommaZweiUndFünfzigEuro);
		assertEquals(
				Eurobetrag.valueOf(10),
				Eurobetrag.valueOf(Integer.MAX_VALUE - 10).plus(
						Eurobetrag.valueOf(Integer.MAX_VALUE - 20)));
	}

	/**
	 * Teste minus Operation mit Eurobeträgen
	 */
	@Test
	public void minusTest() {
		assertEquals(zehnKommaDreiEuro.minus(nullKommaZweiUndFünfzigEuro),
				neunKommaEinUndFünfzigEuro);
		assertNotEquals(zehnKommaDreiEuro.minus(nullKommaZweiUndFünfzigEuro),
				nullKommaZweiUndFünfzigEuro);
	}

	/**
	 * Teste times (mal) Operation mit einem Eurobetrag und eine Zahl (int)
	 */
	@Test
	public void timesTest() {
		assertEquals(nullKommaZweiUndFünfzigEuro.times(2), einsKommaVierEuro);
		assertNotEquals(nullKommaZweiUndFünfzigEuro.times(2),
				nullKommaZweiUndFünfzigEuro);
	}

	/**
	 * Teste, ob die Hashcode gut generiert wird!
	 */
	@Test
	public void hashCodeTest() {
		Set<Eurobetrag> sammlung = new HashSet<Eurobetrag>();
		sammlung.add(Eurobetrag.valueOf(192));
		assertTrue(sammlung.contains(Eurobetrag.valueOf(192)));
		assertFalse(sammlung.contains(Eurobetrag.valueOf(191)));
	}

	/**
	 * Teste, ob die toString Methode richtig funktioniert!
	 */
	@Test
	public void toStringTest() {
		assertEquals(zehnKommaDreiEuro.toString(), "10,03€");
		assertNotEquals(zehnKommaDreiEuro.toString(), "1003€");
	}
}
