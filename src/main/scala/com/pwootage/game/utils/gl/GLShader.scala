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

import java.nio.file.Files

import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL20._
import org.lwjgl.opengl.GL32._

import scala.io.Source

class GLShader(vertPath: String, fragPath: String, geoPath: Option[String] = None) {
  val progID = {
    def compileShader(srcPath: String, typ: Int): Int = {
      val id = glCreateShader(typ)
      val src = {
        val src = Source.fromURL(ClassLoader.getSystemResource(srcPath), "utf-8")
        val res = src.getLines().mkString("\n")
        src.close()
        res
      }
      glShaderSource(id, src)
      glCompileShader(id)
      if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
        println(s"Failed to compile shader $srcPath")
      }
      val log = glGetShaderInfoLog(id)
      if (log.length > 0) {
        println(s"Message when compiling shader $srcPath: $log")
      }
      id
    }

    val vid = compileShader(vertPath, GL_VERTEX_SHADER)
    val fid = compileShader(fragPath, GL_FRAGMENT_SHADER)
    val gid = if (geoPath.isDefined) {
      compileShader(geoPath.get, GL_GEOMETRY_SHADER)
    } else -1
    val pid = glCreateProgram()
    glAttachShader(pid, vid)
    glAttachShader(pid, fid)
    if (gid > 0) glAttachShader(pid, gid)
    glLinkProgram(pid)

    if (glGetProgrami(pid, GL_LINK_STATUS) == GL_FALSE) {
      println(s"Failed to link program!")
    }
    val log = glGetProgramInfoLog(pid)
    if (log.length > 0) {
      println(s"Message when linking program: $log")
    }

    glDeleteShader(vid)
    glDeleteShader(fid)
    if (gid > 0) glDeleteShader(gid)

    pid
  }
}
