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

import me.klez.dearfaun.profile.type.GenderType;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.time.ZonedDateTime;

public record GeneralInfo(String name, String description, Color skinColor, Color hairColor, Color eyesColor,
                          Quantity<Length> height, Quantity<Mass> weight, ZonedDateTime dateOfBirth, GenderType gender,
                          String philosophyDeityReligion) {
}
