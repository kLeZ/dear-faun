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
package me.klez.dearfaun.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.klez.dearfaun.i18n.InternationalizationService;
import me.klez.dearfaun.profile.Character;
import me.klez.dearfaun.profile.GeneralInfo;
import me.klez.dearfaun.profile.Player;
import me.klez.dearfaun.settings.SettingsService;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PromptProviderImpl implements PromptProvider {
	static String promptEnv = "DEAR_FAUN_PROMPT";
	static String promptDefault = "\\s@\\p> ";
	InternationalizationService i18nService;
	SettingsService settingsService;

	private static void appendUsername(AttributedStringBuilder ret) {
		ret.append(System.getProperty("user.name"));
	}

	private static void appendCurrentDirectory(AttributedStringBuilder ret) {
		String currentDir = System.getProperty("user.dir");
		currentDir = currentDir.replace(System.getProperty("user.home"), "~");
		ret.append(currentDir);
	}

	private static void appendHostname(char token, AttributedStringBuilder ret) {
		String os = System.getProperty("os.name");
		String hostname;
		if (os.toLowerCase().contains("windows")) {
			hostname = System.getenv("COMPUTERNAME");
		} else {
			hostname = System.getenv("HOSTNAME");
		}
		int dot = hostname.indexOf('.');
		if (token == 'h' && dot > 0) {
			hostname = hostname.substring(0, dot);
		}
		ret.append(hostname);
	}

	@Override
	public AttributedString getPrompt() {
		String prompt = System.getenv(promptEnv);
		if (prompt == null || prompt.isBlank()) {
			prompt = promptDefault;
		}
		var ret = new AttributedStringBuilder();
		char[] tokens = prompt.toCharArray();
		boolean isCommand = false;
		for (char token : tokens) {
			if (isCommand) {
				isCommand = false;
				appendCommand(token, ret);
			} else if (token == '\\') {
				isCommand = true;
			} else {
				ret.append(token);
			}
		}
		ret.style().backgroundDefault().foreground(AttributedStyle.CYAN);
		return ret.toAttributedString();
	}

	private void appendCommand(char token, AttributedStringBuilder ret) {
		// man 1 bash:1576
		switch (token) {
			case 'u':
				appendUsername(ret);
				break;
			case 'H', 'h':
				appendHostname(token, ret);
				break;
			case 'w':
				appendCurrentDirectory(ret);
				break;
			case 'r':
				ret.append('\r');
				break;
			case 'n':
				ret.append('\n');
				break;
			case 'p':
				appendPlayer(ret);
				break;
			case 's':
				appendCharacter(ret);
				break;
			default:
				ret.append(token);
				break;
		}
	}

	private void appendCharacter(AttributedStringBuilder ret) {
		String character = settingsService.currentCharacter()
		                                  .map(Character::info)
		                                  .map(GeneralInfo::name)
		                                  .orElse(i18nService.translate("no.character.selected"));
		ret.append('[').append(character).append(']');
	}

	private void appendPlayer(AttributedStringBuilder ret) {
		String player = settingsService.currentPlayer()
		                               .map(Player::name)
		                               .orElse(i18nService.translate("no.player.selected"));
		ret.append('[').append(player).append(']');
	}
}
