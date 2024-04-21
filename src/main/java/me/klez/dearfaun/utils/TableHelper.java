/*
 * Copyright (c) 2024 Alessandro 'kLeZ' Accardo.
 *
 * This file is part of dear-faun.
 *
 * dear-faun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * dear-faun is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with dear-faun.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
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
			return new String[]{DATE_TIME_FORMATTER.format(localDateTime)};
		}
		if (value instanceof LocalDate localDate) {
			return new String[]{DATE_TIME_FORMATTER.format(localDate)};
		}
		return new DefaultFormatter().format(value);
	}

	protected static String[] formatValueEnum(Object value) {
		if (value instanceof ValueEnum<?> e) {
			return new String[]{e.value()};
		}
		return new DefaultFormatter().format(value);
	}

	protected void display(final String rendered) {
		terminal().writer().println(rendered);
		terminal().writer().flush();
	}
}
