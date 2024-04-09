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

package me.klez.dearfaun.profile.abilityscores;

import java.util.Objects;

public sealed class ModifierAbilityScore implements AbilityScore permits StrengthAbilityScore, DexterityAbilityScore, ConstitutionAbilityScore, IntelligenceAbilityScore, WisdomAbilityScore, CharismaAbilityScore {
	private static final int MID_RANGE = 10;
	private final String name;
	private final short value;
	private final short modifier;

	public ModifierAbilityScore(String name, short value, short modifier) {
		this.name = name;
		this.value = value;
		this.modifier = modifier;
	}

	public ModifierAbilityScore(String name, short value) {
		short modifier = (short) Math.round(Math.floor((value - MID_RANGE) / 2.0F));
		this(name, value, modifier);
	}

	@Override
	public short value() {
		return (short) (value + modifier);
	}

	public short rawValue() {
		return value;
	}

	public AbilityScore withValue(short value) {
		return new ModifierAbilityScore(name, value);
	}

	@Override
	public String name() {
		return name;
	}

	public short modifier() {
		return modifier;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (ModifierAbilityScore) obj;
		return Objects.equals(this.name, that.name) && this.value == that.value && this.modifier == that.modifier;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, value, modifier);
	}

	@Override
	public String toString() {
		return String.format("%s: %d(%+d)", name, value, modifier);
	}
}
