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

package com.pwootage.game

import org.lwjgl.glfw._
import org.lwjgl.glfw.GLFW._
import org.lwjgl.opengl._
import org.lwjgl.opengl.GL11._
import org.lwjgl.system.MemoryUtil._

class Game {
  private var running = true
  val errCB = Callbacks.errorCallbackPrint(System.err)
  val keyCB =  new GLFWKeyCallback {
    override def invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int): Unit = {
      //Enter keyup
      if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
        running = false
      }
    }
  }

  glfwSetErrorCallback(errCB)
  if (glfwInit() != GL_TRUE) throw new Error("Failed to initialize GLFW")
  glfwDefaultWindowHints()
  //  glfwWindowHint(GLFW_VISIBLE, GL_FALSE)
  glfwWindowHint(GLFW_RESIZABLE, GL_TRUE)

  val window = glfwCreateWindow(800, 600, "Game", NULL, NULL)
  if (window == NULL) throw new Error("Failed to create window")

  glfwSetKeyCallback(window, keyCB)

  glfwMakeContextCurrent(window)

  //Vsync?
  //  glfwSwapInterval(1)


  def run(): Unit = {
    GLContext.createFromCurrent()

    glClearColor(0.0f, 1.0f, 0.0f, 0.0f)
    while (running) {
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
      glfwSwapBuffers(window)
      glfwPollEvents()
      if (glfwWindowShouldClose(window) == GL_TRUE) {
        running = false
      }
    }

    closeHandler()
  }

  def closeHandler(): Unit = {
    glfwDestroyWindow(window)
    keyCB.release()
  }
}
