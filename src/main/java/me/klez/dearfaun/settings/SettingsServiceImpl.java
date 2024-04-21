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
package me.klez.dearfaun.settings;

import jakarta.annotation.Nonnull;
import me.klez.dearfaun.profile.Character;
import me.klez.dearfaun.profile.Player;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingsServiceImpl implements SettingsService {
	private boolean debug;

	@Nonnull
	@Override
	public Optional<Player> currentPlayer() {
		return Optional.empty();
	}

	@Nonnull
	@Override
	public Optional<Character> currentCharacter() {
		return Optional.empty();
	}

	@Override
	public void save() {
	}

	@Override
	public void load() {
	}

	@Override
	public boolean isDebug() {
		return debug;
	}

	@Override
	public boolean setDebug(boolean debug) {
		this.debug = debug;
		return isDebug();
	}
}
