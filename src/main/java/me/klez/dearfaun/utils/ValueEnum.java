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

import java.io.Serializable;
import java.lang.constant.Constable;
import java.util.Arrays;
import java.util.Optional;

public interface ValueEnum<E extends Enum<E>> extends Constable, Comparable<E>, Serializable {
	static <E extends Enum<E>> E parse(Class<? extends ValueEnum<E>> type, String value) {
		//noinspection unchecked
		return (E) tryParse(type, value).orElseThrow();
	}

	static <E extends Enum<E>> Optional<E> tryParse(Class<?> type, String value) {
		//noinspection unchecked
		return Arrays.stream(type.getEnumConstants())
		             .map(e -> (ValueEnum<?>) e)
		             .filter(e -> e.value().equalsIgnoreCase(value))
		             .map(e -> (E) e)
		             .findFirst();
	}

	String value();

	Class<? super E> enumClass();
}
