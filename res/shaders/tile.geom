#version 330 core

layout(points) in;
layout(triangle_strip, max_vertices = 4) out;

in vec2 xy_start[];
in vec2 xy_end[];
in vec2 uv_start[];
in vec2 uv_end[];

out vec2 uv;

void main() {
  uv = vec2(uv_start[0].x, uv_end[0].y);
  gl_Position = vec4(xy_start[0].x, xy_start[0].y, 1.0f, 1.0f);
  EmitVertex();
  uv = vec2(uv_start[0].x, uv_start[0].y);
  gl_Position = vec4(xy_start[0].x, xy_end[0].y, 1.0f, 1.0f);
  EmitVertex();
  uv = vec2(uv_end[0].x, uv_end[0].y);
  gl_Position = vec4(xy_end[0].x, xy_start[0].y, 1.0f, 1.0f);
  EmitVertex();
  uv = vec2(uv_end[0].x, uv_start[0].y);
  gl_Position = vec4(xy_end[0].x, xy_end[0].y, 1.0f, 1.0f);
  EmitVertex();
  EndPrimitive();
}
