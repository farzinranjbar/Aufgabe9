package de.uni_hamburg.informatik.swt.se2.kino.fachwerte.geldbetrag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Eurobetrag implements Comparable<Eurobetrag> {
	private static final Pattern GELDBETRAG = Pattern
			.compile("((\\d){1,7})(,((\\d){0,2})){0,1}");
	private final int _centbetrag;

	/**
	 * 
	 * @param centbetrag
	 */
	private Eurobetrag(int centbetrag) {
		_centbetrag = centbetrag;
	}

	/**
	 * Gibt einen Geldbetrag für den eingegebenen String zurück.
	 * 
	 * @param geldbetrag
	 *            in Stringform
	 * @return der Geldbetrag
	 */
	public static Eurobetrag valueOf(String geldbetrag)
			throws IllegalArgumentException {
		// Prüft, ob die Eingabe nur unserem regulären Ausdruck entspricht, wenn
		// nicht
		// dann wirft er IllegalArgumentException raus.
		Matcher matcher = GELDBETRAG.matcher(geldbetrag);
		if (matcher.matches()) {
			// Wandelt, da die Eingabe unserem regulären Ausdruck entspricht
			// Diese von String in Eurobetrag um!
			int centbetrag;
			if ("".equals(matcher.group(4)) || matcher.group(4) == null) {
				centbetrag = Integer.valueOf(matcher.group(1)) * 100;
			} else {
				if (matcher.group(4).length() < 2)
					centbetrag = Integer.valueOf(matcher.group(1)) * 100
							+ Integer.valueOf(matcher.group(4)) * 10;
				else {
					centbetrag = Integer.valueOf(matcher.group(1)) * 100
							+ Integer.valueOf(matcher.group(4));
				}
			}
			return new Eurobetrag(centbetrag);
		} else {
			throw new IllegalArgumentException(
					"Der String ist kein gültiger Geldbetrag");
		}
	}

	/**
	 * Gibt einen Geldbetrag für den eingegebenen Integer zurück.
	 * 
	 * @param geldbetrag
	 *            in Integerform
	 * @return der Geldbetrag
	 */
	public static Eurobetrag valueOf(Integer geldbetrag) {
		return new Eurobetrag(geldbetrag);
	}

	/**
	 * Gibt einen Geldbetrag für den eingegebenen int zurück.
	 * 
	 * @param geldbetrag
	 *            in int
	 * @return der Geldbetrag
	 */
	public static Eurobetrag valueOf(int geldbetrag) {
		return new Eurobetrag(geldbetrag);
	}

	public Eurobetrag plus(Eurobetrag obj) {
		Eurobetrag that = obj;
		return valueOf(this._centbetrag + that._centbetrag);
	}

	public Eurobetrag minus(Eurobetrag obj) {
		Eurobetrag that = obj;
		return valueOf(this._centbetrag - that._centbetrag);
	}

	public int getCentbetrag() {
		return _centbetrag;
	}

	public Eurobetrag times(int factor) {
		return valueOf(this.getCentbetrag() * factor);
	}

	@Override
	public String toString() {
		int centTeil = _centbetrag % 100;
		int centTeil2 = (_centbetrag % 100) * -1;

		if (_centbetrag >= 0) {
			if (centTeil < 10) {
				return _centbetrag / 100 + ",0" + centTeil + "€";
			} else {
				return _centbetrag / 100 + "," + centTeil + "€";
			}
		} else {
			if (centTeil2 < 10) {
				return "-" + (_centbetrag / 100) * -1 + ",0" + centTeil2 + "€";
			} else {
				return "-" + (_centbetrag / 100) * -1 + "," + centTeil2 + "€";
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Eurobetrag) {
			Eurobetrag that = (Eurobetrag) obj;
			return this._centbetrag == that._centbetrag;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return _centbetrag;
	}

	public Eurobetrag abs() {
		if (_centbetrag < 0) {
			return Eurobetrag.valueOf(_centbetrag * -1);
		} else {
			return this;
		}
	}

	@Override
	public int compareTo(Eurobetrag o) {
		if (_centbetrag > o._centbetrag) {
			return 1;
		} else if (_centbetrag < o._centbetrag) {
			return -1;
		} else {
			return 0;
		}
	}

}
