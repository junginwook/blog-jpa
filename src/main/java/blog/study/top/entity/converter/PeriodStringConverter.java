package blog.study.top.entity.converter;

import jakarta.persistence.AttributeConverter;
import java.time.Period;

public class PeriodStringConverter implements AttributeConverter<Period, String> {

	@Override
	public String convertToDatabaseColumn(Period attribute) {
		return attribute.toString();
	}

	@Override
	public Period convertToEntityAttribute(String dbData) {
		return Period.parse(dbData);
	}
}
