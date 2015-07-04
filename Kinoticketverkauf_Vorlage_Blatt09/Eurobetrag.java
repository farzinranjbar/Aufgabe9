package de.uni_hamburg.informatik.swt.se2.kino.fachwerte.geldbetrag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Eurobetrag implements Geldbetrag
{
    private static final Pattern GELDBETRAG = Pattern.compile("\\A([\\d]{1,7})((,(\\d\\d)){0,1})\\s*\\z");
    private final int _centbetrag;

    private Eurobetrag(int centbetrag)
    {
        _centbetrag = centbetrag;
    }

    /**
     * Gibt einen Geldbetrag für den eingegebenen String zurück.
     * @param geldbetrag in Stringform
     * @return der Geldbetrag
     */
    public static Eurobetrag valueOf(String geldbetrag)
            throws IllegalArgumentException
    {
        int centbetrag;
        Matcher matcher = GELDBETRAG.matcher(geldbetrag);
        if (matcher.find())
        {
            if ("".equals(matcher.group(4)) || matcher.group(4) == null)
            {
                centbetrag = Integer.valueOf(matcher.group(1)) * 100;
            }
            else
            {
                centbetrag = Integer.valueOf(matcher.group(1)) * 100
                        + Integer.valueOf(matcher.group(4));
            }
        }
        else
        {
            throw new IllegalArgumentException(
                    "Der String ist kein gültiger Geldbetrag");
        }
        return new Eurobetrag(centbetrag);
    }

    /**
    * Gibt einen Geldbetrag für den eingegebenen Integer zurück.
    * @param geldbetrag in Integerform
    * @return der Geldbetrag
    */
    public static Eurobetrag valueOf(Integer geldbetrag)
    {
        return new Eurobetrag(geldbetrag);
    }

    /**
     * Gibt einen Geldbetrag für den eingegebenen int zurück.
     * @param geldbetrag in int
     * @return der Geldbetrag
     */
    public static Eurobetrag valueOf(int geldbetrag)
    {
        return new Eurobetrag(geldbetrag);
    }

    @Override
    public Eurobetrag plus(Geldbetrag that) throws IllegalArgumentException
    {
        if (that instanceof Eurobetrag)
        {
            Eurobetrag thats = (Eurobetrag) that;
            return valueOf(this._centbetrag + thats._centbetrag);
        }
        else
        {
            throw new IllegalArgumentException(
                    "Der Parameter muss zuerst in einen Eurobetrag umgewandelt werden");
        }
    }

    @Override
    public Eurobetrag minus(Geldbetrag that)
    {
        if (that instanceof Eurobetrag)
        {
            Eurobetrag thats = (Eurobetrag) that;
            return valueOf(this._centbetrag - thats._centbetrag);
        }
        else
        {
            throw new IllegalArgumentException(
                    "Der Parameter muss zuerst in einen Eurobetrag umgewandelt werden");
        }
    }

    @Override
    public Eurobetrag times(int factor)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString()
    {
        if (_centbetrag % 100 < 10)
        {
            return _centbetrag / 100 + ",0" + _centbetrag % 100 + "€";
        }
        else
        {
            return _centbetrag / 100 + "," + _centbetrag % 100 + "€";
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Eurobetrag)
        {
            Eurobetrag that = (Eurobetrag) obj;
            return this._centbetrag == that._centbetrag;
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        //TODO noch machen
        return 0;
    }
    

}
