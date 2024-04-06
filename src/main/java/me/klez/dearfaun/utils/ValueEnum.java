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
		return Arrays.stream(type.getEnumConstants()).map(e -> (ValueEnum<?>) e).filter(e -> e.value().equalsIgnoreCase(value)).map(e -> (E) e).findFirst();
	}

	String value();

	Class<? super E> enumClass();
}
