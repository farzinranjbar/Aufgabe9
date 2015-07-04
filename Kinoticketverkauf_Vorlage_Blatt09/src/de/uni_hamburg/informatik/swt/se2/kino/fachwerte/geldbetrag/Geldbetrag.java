package de.uni_hamburg.informatik.swt.se2.kino.fachwerte.geldbetrag;

public interface Geldbetrag
{
    /**
     * Addiert diesen Geldbetrag mit dem Übergebenen.
     * @param that der zu addierende Geldbetrag
     * @return Die Summe
     */
    public Geldbetrag plus(Geldbetrag that);
    
    /**
     * Subtrahiert den übergebenen von diesem Geldbetrag
     * @param that der zu subtrahierenden Geldbetrag
     * @return Die Differenz
     */
    public Geldbetrag minus(Geldbetrag that);

    /**
     * Multipliziert diesen Geldbetrag mit der übergebenen Zahl.
     * @param der Faktor
     * @return Das Produkt
     */
    public Geldbetrag times(int factor);

    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);
    
    @Override
    public int hashCode();
}
