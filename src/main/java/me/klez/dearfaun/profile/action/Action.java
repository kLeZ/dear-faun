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
package me.klez.dearfaun.profile.action;

/**
 * This interface represents an action that can be performed by an RPG
 * Character.<br/>
 * There are many action types, two of which must be "Talk" and "Attack",
 * because of the mandatory actions that an RPG Character can necessarily
 * perform.
 *
 * @author kLeZ
 */
public sealed interface Action permits TalkAction, AttackAction {
	/**
	 * Performs the action of this action object.
	 */
	void perform();
}
