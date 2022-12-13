package ua.karatnyk.impl;

import java.text.ParseException;
import java.util.Arrays;

public final class CurrencyConvertor {

	public static double convert(double amount, String from, String to, CurrencyConversion conversion) throws ParseException {
		String[] listConvert = {"USD", "CAD", "GBP", "EUR", "CHF", "INR", "AUD"};
		if (!(amount >= 0 && amount <= 10000) || !Arrays.stream(listConvert).anyMatch(from::equals)|| !Arrays.stream(listConvert).anyMatch(to::equals))
			throw new ParseException("Not correct format currency" + "", 0);
		double curencyTo = conversion.getRates().get(to);
		double curencyFrom = conversion.getRates().get(from);
		return amount * (curencyTo / curencyFrom);

	}
}
