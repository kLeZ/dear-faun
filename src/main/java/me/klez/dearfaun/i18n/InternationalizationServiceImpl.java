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

package me.klez.dearfaun.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
public class InternationalizationServiceImpl implements InternationalizationService {
	private final MessageSource messageSource;

	public InternationalizationServiceImpl() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(STR."\{getClass().getPackage().getName()}/messages");
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		this.messageSource = messageSource;
	}

	@Override
	public String translate(String key, Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}

	@Override
	public String translate(String key, Locale locale, Object... args) {
		return messageSource.getMessage(key, args, locale);
	}

	@Override
	public String translate(String key) {
		return translate(key, Locale.getDefault());
	}

	@Override
	public String translate(String key, Object... args) {
		return messageSource.getMessage(key, args, Locale.getDefault());
	}
}
