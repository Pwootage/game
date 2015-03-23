#version 330 core

uniform float aspectX;
uniform float aspectY;

in vec2 in1;
in vec2 in2;
in vec2 in3;
in vec2 in4;

out vec2 xy_start;
out vec2 xy_end;
out vec2 uv_start;
out vec2 uv_end;

void main() {
  xy_start = vec2(in1.x / aspectX, in1.y / aspectY);
  xy_end = vec2(in2.x / aspectX, in2.y / aspectY);
  uv_start = in3;
  uv_end = in4;
}
