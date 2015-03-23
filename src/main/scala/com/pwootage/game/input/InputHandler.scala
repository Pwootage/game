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

package com.pwootage.game.input

import org.lwjgl.glfw.GLFW._
import org.lwjgl.glfw.GLFWKeyCallback

class InputHandler {
  var pressedKeys = Set[Int]()

  val glfwEventHandler = new GLFWKeyCallback {
    override def invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int): Unit = {
      if (action == GLFW_RELEASE) {
        keyUp(key)
      } else if (action == GLFW_PRESS) {
        keyDown(key)
      }
    }
  }

  def keyUp(key: Int): Unit = {
    pressedKeys = pressedKeys + key
  }

  def keyDown(key: Int): Unit = {
    pressedKeys = pressedKeys - key
  }
}
