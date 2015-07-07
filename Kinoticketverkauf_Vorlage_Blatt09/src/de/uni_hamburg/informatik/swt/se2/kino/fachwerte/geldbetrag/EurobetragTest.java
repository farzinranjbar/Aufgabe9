package de.uni_hamburg.informatik.swt.se2.kino.fachwerte.geldbetrag;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class EurobetragTest
{
    @Test
    public void konvertierungsTest()
    {
        Geldbetrag zehnKommaDreiEuro = Eurobetrag.valueOf(1003);
        Geldbetrag nullKommaZweiUndFünfzigEuro = Eurobetrag.valueOf(52);
        Geldbetrag ZehnKommaFünfUndFünfzigEuro = Eurobetrag.valueOf(1055);
        Geldbetrag neunKommaEinUndFünfzigEuro = Eurobetrag.valueOf(951);
        Geldbetrag einsKommaVierEuro = Eurobetrag.valueOf(104);
        
        /**
         * Prüfe die valueOf Methoden mit String, Integer & int als 
         * übergegebene Parameter!
         */
        try
        {
            assertEquals(zehnKommaDreiEuro, Eurobetrag.valueOf("10,03"));
            assertEquals(zehnKommaDreiEuro,
                    Eurobetrag.valueOf(new Integer(1003)));
            assertEquals(zehnKommaDreiEuro,
                    Eurobetrag.valueOf(1003));
            assertNotEquals(zehnKommaDreiEuro, Eurobetrag.valueOf("10,02"));
        }
        catch (IllegalArgumentException e)
        {
            fail("Gültiger Parameter erzeugt keinen gültigen Geldbetrag!");
        }

        try
        {
            assertEquals(zehnKommaDreiEuro, Eurobetrag.valueOf("10,22"));
            fail("Ungültiger Parameter erzeugt einen gültigen Geldbetrag!");
        }
//        catch (IllegalArgumentException e)
//        {
//        }
        catch (AssertionError b)
        {
        }
       
        /**
         * Teste plus Operation mit Eurobeträgen
         */
        assertEquals(zehnKommaDreiEuro.plus(nullKommaZweiUndFünfzigEuro), ZehnKommaFünfUndFünfzigEuro);
        assertNotEquals(zehnKommaDreiEuro.plus(nullKommaZweiUndFünfzigEuro), nullKommaZweiUndFünfzigEuro);
       
        /**
         * Teste minus Operation mit Eurobeträgen
         */
        assertEquals(zehnKommaDreiEuro.minus(nullKommaZweiUndFünfzigEuro), neunKommaEinUndFünfzigEuro);
        assertNotEquals(zehnKommaDreiEuro.minus(nullKommaZweiUndFünfzigEuro), nullKommaZweiUndFünfzigEuro);
        
        /**
         * Teste times (mal) Operation mit einem Eurobetrag und eine Zahl (int)
         */
        assertEquals(nullKommaZweiUndFünfzigEuro.times(2), einsKommaVierEuro);
        assertNotEquals(nullKommaZweiUndFünfzigEuro.times(2), nullKommaZweiUndFünfzigEuro);
        
        /**
         * Teste, ob die Hashcode gut generiert wird!
         */
        Set<Geldbetrag> sammlung = new HashSet<Geldbetrag>(); 
        sammlung.add(Eurobetrag.valueOf(192));
        assertTrue(sammlung.contains(Eurobetrag.valueOf(192)));
        assertFalse(sammlung.contains(Eurobetrag.valueOf(191)));
        
        /**
         * Teste, ob die toString Methode richtig funktioniert!
         */
        assertEquals(zehnKommaDreiEuro.toString(), "10,03");
        assertNotEquals(zehnKommaDreiEuro.toString(), "1003");
    }
}
