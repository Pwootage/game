/*
 * Copyright (C) 2015 Christopher Freestone
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.pwootage.game.utils.gl

import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL30._

object GLErrors {
  def stringify(i: Int) = i match {
    case GL_NO_ERROR => "No Error"
    case GL_INVALID_ENUM => "Invalid Enum"
    case GL_INVALID_VALUE => "Invalid Value"
    case GL_INVALID_OPERATION => "Invalid Operation"
    case GL_INVALID_FRAMEBUFFER_OPERATION => "Invalid Framebuffer Operation"
    case GL_OUT_OF_MEMORY => "Out of Memory"
  }

}
