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

import java.awt.image.BufferedImage
import java.nio.ByteBuffer
import javax.imageio.ImageIO
import com.pwootage.game.utils.SeqUtils._

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL12._
import org.lwjgl.glfw.GLFW._

class GLTexture(path: String) {
  val tid = {
    val tid = glGenTextures()
    glBindTexture(GL_TEXTURE_2D, tid)
    val src = ClassLoader.getSystemResource(path)
    val img = ImageIO.read(src)
    val data = BufferUtils.createByteBuffer(img.getWidth * img.getHeight * 4)
    val arrData = img.getRGB(0, 0, img.getWidth, img.getHeight, null, 0, img.getWidth)
    data.asIntBuffer.put(arrData)
//    for ((x, y, i) <- arrLoopRowOrder(img.getWidth, img.getHeight)) {
//      val pixel = arrData(i)
////      data.put((pixel >> 16).toByte)
////      data.put((pixel >> 8).toByte)
////      data.put((pixel >> 0).toByte)
////      data.put((pixel >> 24).toByte)
//      data.put(255.toByte)
//      data.put(0.toByte)
//      data.put(0.toByte)
//      data.put(255.toByte)
//    }
    data.rewind()
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, img.getWidth, img.getHeight, 0, GL_BGRA, GL_UNSIGNED_BYTE, data)

    val err = glGetError()
    if (err != GL_NO_ERROR) System.err.println(s"Error creating texture: $err ${GLErrors.stringify(err)}")

    tid
  }

}
