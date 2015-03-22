# Game

This is a neat game-thingy. At some point I'll have to update this file with more details,
when there are more details :)

# Building

I'll set up proper sbt building at some point, I promise!

* Download lwjgl from [http://build.lwjgl.org/stable/lwjgl.zip](http://build.lwjgl.org/stable/lwjgl.zip)
* Extract into a folder named "lwjl" in lib (i.e. lib/lwjgl/{jar/native/doc})
* Open as SBT project in intellij, open GameMain.scala, run it, add appropriate "-Djava.library.path" to launchconfig

### Example library paths:
* -Djava.library.path=lib/lwjgl/native/windows/x64
* -Djava.library.path=lib/lwjgl/native/macosx/x64
* -Djava.library.path=lib/lwjgl/native/linux/x64

# License
Copyright (C) 2015 Christopher Freestone

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.