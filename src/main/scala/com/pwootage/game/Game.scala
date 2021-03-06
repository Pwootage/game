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

import com.pwootage.game.input.InputHandler
import com.pwootage.game.utils.gl.{GLBuffer, GLShader, GLTexture}
import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW._
import org.lwjgl.glfw._
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL20._
import org.lwjgl.opengl.GL30._
import org.lwjgl.opengl._
import org.lwjgl.system.MemoryUtil._

class Game {
  private var running = true
  val errCB = Callbacks.errorCallbackPrint(System.err)
  val ih = new InputHandler
  val resizeCB = new GLFWWindowSizeCallback {
    override def invoke(window: Long, width: Int, height: Int): Unit = {
      Game.this.width = width
      Game.this.height = height
      Game.this.aspect = width.toFloat / height.toFloat
      glViewport(0, 0, width, height)
    }
  }

  var width = 600
  var height = 400
  var aspect = width.toFloat / height.toFloat

  glfwSetErrorCallback(errCB)
  if (glfwInit() != GL_TRUE) throw new Error("Failed to initialize GLFW")
  glfwDefaultWindowHints()
  //  glfwWindowHint(GLFW_VISIBLE, GL_FALSE)
  glfwWindowHint(GLFW_RESIZABLE, GL_TRUE)

  val window = glfwCreateWindow(width, height, "Game", NULL, NULL)
  if (window == NULL) throw new Error("Failed to create window")

  glfwSetKeyCallback(window, ih.glfwEventHandler)

  glfwMakeContextCurrent(window)

  //Vsync?
  //  glfwSwapInterval(1)

  glfwSetWindowSizeCallback(window, resizeCB)

  var drawBuff: GLBuffer = _

  def run(): Unit = {
    GLContext.createFromCurrent()


    val vao = glGenVertexArrays()
    glBindVertexArray(vao)

    val tex = new GLTexture("fonts/press_start_2p.png")
    val shader = new GLShader("shaders/tile.vert", "shaders/tile.frag", Some("shaders/tile.geom"))
    glUseProgram(shader.progID)

    val toDraw = BufferUtils.createFloatBuffer(16)
    toDraw.put(Array(
      0.5f, 0.5f,
      1.0f, 1.0f,
      0.0f, 0.0f,
      1.0f, 1.0f
    ))
    toDraw.put(Array(
      0.0f, 0.0f,
      0.5f, 0.5f,
      1.0f / 16.0f, 1.0f / 16.0f,
      2.0f / 16.0f, 2.0f / 16.0f
    ))
    toDraw.flip()
    drawBuff = new GLBuffer
    drawBuff.bufferVertexData(toDraw)

    glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
    while (running) {
      //Clear is slow, so we're going to avoid it!
      //glClear(GL_COLOR_BUFFER_BIT)

      //Set uniforms for vert shader
      if (aspect < 1) {
        glUniform1f(shader.getLoc("aspectX"), 1)
        glUniform1f(shader.getLoc("aspectY"), 1 / aspect)
      } else {
        glUniform1f(shader.getLoc("aspectX"), aspect)
        glUniform1f(shader.getLoc("aspectY"), 1)
      }

      main()

      glfwSwapBuffers(window)
      glfwPollEvents()
      if (glfwWindowShouldClose(window) == GL_TRUE) {
        running = false
      }
    }

    closeHandler()
  }

  def main(): Unit = {
    drawBuff.draw()
  }

  def closeHandler(): Unit = {
    glfwDestroyWindow(window)
    ih.glfwEventHandler.release()
    resizeCB.release()
  }
}
