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

import java.nio.FloatBuffer

import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL15._
import org.lwjgl.opengl.GL20._
import org.lwjgl.opengl.GL30._

class GLBuffer {
  val id = glGenBuffers()
  var size = 0

  def bufferVertexData(data: FloatBuffer): Unit = {
    this.size = data.limit / 8
    glBindBuffer(GL_ARRAY_BUFFER, id)
    glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW)
  }

  def draw(): Unit = {
    glEnableVertexAttribArray(0)
    glEnableVertexAttribArray(1)
    glEnableVertexAttribArray(2)
    glEnableVertexAttribArray(3)
    glBindBuffer(GL_ARRAY_BUFFER, id)
    // TL x/y
    glVertexAttribPointer(0, 2, GL_FLOAT, false, 32, 0)
    // BR x/y
    glVertexAttribPointer(1, 2, GL_FLOAT, false, 32, 8)
    // TL u/v
    glVertexAttribPointer(2, 2, GL_FLOAT, false, 32, 16)
    // BR u/v
    glVertexAttribPointer(3, 2, GL_FLOAT, false, 32, 24)
    glDrawArrays(GL_POINTS, 0, size)
    glDisableVertexAttribArray(3)
    glDisableVertexAttribArray(2)
    glDisableVertexAttribArray(1)
    glDisableVertexAttribArray(0)
  }
}
