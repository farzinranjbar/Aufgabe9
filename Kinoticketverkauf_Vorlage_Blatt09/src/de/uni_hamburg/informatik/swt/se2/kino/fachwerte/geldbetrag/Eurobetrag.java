package de.uni_hamburg.informatik.swt.se2.kino.fachwerte.geldbetrag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Eurobetrag implements Geldbetrag
{
    private static final Pattern GELDBETRAG = Pattern.compile("((\\d){1,7})(,((\\d){0,2})){0,1}");
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
        if (geldbetrag.matches(".*[a-zA-Z]+.*"))
        {
            throw new IllegalArgumentException(
                    "Der String ist kein gültiger Geldbetrag");
        }

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
                if (matcher.group(4)
                    .length() < 2)
                    centbetrag = Integer.valueOf(matcher.group(1)) * 100
                            + Integer.valueOf(matcher.group(4)) * 10;
                else
                {
                    centbetrag = Integer.valueOf(matcher.group(1)) * 100
                            + Integer.valueOf(matcher.group(4));
                }
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
    public Eurobetrag plus(Geldbetrag obj) throws IllegalArgumentException
    {
        if (obj instanceof Eurobetrag)
        {
            Eurobetrag that = (Eurobetrag) obj;
            return valueOf(this._centbetrag + that._centbetrag);
        }
        else
        {
            throw new IllegalArgumentException(
                    "Der Parameter muss zuerst in einen Eurobetrag umgewandelt werden");
        }
    }

    @Override
    public Eurobetrag minus(Geldbetrag obj)
    {
        if (obj instanceof Eurobetrag)
        {
            Eurobetrag that = (Eurobetrag) obj;
            return valueOf(this._centbetrag - that._centbetrag);
        }
        else
        {
            throw new IllegalArgumentException(
                    "Der Parameter muss zuerst in einen Eurobetrag umgewandelt werden");
        }
    }

    public int getCentbetrag()
    {
        return _centbetrag;
    }

    @Override
    public Eurobetrag times(int factor)
    {
        return valueOf(this.getCentbetrag() * factor);
    }

    @Override
    public String toString()
    {
        int centTeil = _centbetrag % 100;
        int centTeil2 = (_centbetrag % 100) * -1;

        if (_centbetrag >= 0)
        {
            if (centTeil < 10)
            {
                return _centbetrag / 100 + ",0" + centTeil;
            }
            else
            {
                return _centbetrag / 100 + "," + centTeil;
            }
        }

        else
        {
            if (centTeil2 < 10)
            {
                return _centbetrag / 100 + ",0" + centTeil2;
            }
            else
            {
                return _centbetrag / 100 + "," + centTeil2;
            }
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
        return _centbetrag;
    }

}
