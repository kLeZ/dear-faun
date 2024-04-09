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

package me.klez.dearfaun.profile;

import me.klez.dearfaun.profile.abilityscores.AbilityScore;
import me.klez.dearfaun.profile.action.Action;
import me.klez.dearfaun.profile.skill.Skill;

import java.util.HashSet;
import java.util.Set;

public record Character(Player owner, GeneralInfo info, Set<Action> actions, Set<Skill> skills,
                        Set<AbilityScore> abilityScores) {
	public Character(Player owner, GeneralInfo info) {
		this(owner, info, new HashSet<>(), new HashSet<>(), new HashSet<>());
	}
}
