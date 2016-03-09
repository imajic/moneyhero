package moneyhero.ui.conversion;

import com.vaadin.data.util.converter.Converter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by ivo on 3/9/16.
 */

public class LongToMoneyConverter implements Converter<String, Long> {

    private final DecimalFormat moneyFormat = new DecimalFormat("0.00");

    @Override
    public Long convertToModel(String text, Class<? extends Long> targetType, Locale locale) throws ConversionException {
        if (text == null || text.isEmpty()) {
            return null;
        }
        try {
            long value = Math.round(moneyFormat.parse(text).doubleValue() * 100d);
            return value;
        }
        catch (ParseException e){
            throw new ConversionException("Can not convert text to a currency.");
        }
    }

    @Override
    public String convertToPresentation(Long value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        if (value == null) {
            return null;
        } else {
            return moneyFormat.format((double) value / 100d);
        }
    }

    public Class<Long> getModelType() {
        return Long.class;
    }

    public Class<String> getPresentationType() {
        return String.class;
    }
}