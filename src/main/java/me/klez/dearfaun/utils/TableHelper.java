package me.klez.dearfaun.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.jline.terminal.Terminal;
import org.springframework.shell.table.DefaultFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@SuperBuilder
@RequiredArgsConstructor
@Accessors(fluent = true, makeFinal = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TableHelper {
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("ccc dd MMMM yyyy[ HH[:mm]]");
	Terminal terminal;

	protected static String[] formatDateTime(Object value) {
		if (value instanceof LocalDateTime localDateTime) {
			return new String[] { DATE_TIME_FORMATTER.format(localDateTime) };
		}
		if (value instanceof LocalDate localDate) {
			return new String[] { DATE_TIME_FORMATTER.format(localDate) };
		}
		return new DefaultFormatter().format(value);
	}

	protected static String[] formatValueEnum(Object value) {
		if (value instanceof ValueEnum<?> e) {
			return new String[] { e.value() };
		}
		return new DefaultFormatter().format(value);
	}

	protected void display(final String rendered) {
		terminal().writer().println(rendered);
		terminal().writer().flush();
	}
}
