#version 330 core

layout(points) in;
layout(line_strip, max_vertices = 4) out;

in vec2 xy_start[];
in vec2 xy_end[];
in vec2 uv_start[];
in vec2 uv_end[];

void main() {
  gl_Position = vec4(xy_start[0].x, xy_start[0].y, 1.0f, 1.0f);
  EmitVertex();
  gl_Position = vec4(xy_start[0].x, xy_end[0].y, 1.0f, 1.0f);
  EmitVertex();
  gl_Position = vec4(xy_end[0].x, xy_start[0].y, 1.0f, 1.0f);
  EmitVertex();
  gl_Position = vec4(xy_end[0].x, xy_end[0].y, 1.0f, 1.0f);
  EmitVertex();

  // gl_Position = vec4(xy_start[0].x, xy_start[0].y, 1.0f, 1.0f);
  // EmitVertex();
  // gl_Position = vec4(xy_start[0].x, xy_start[0].y + 1.0f, 1.0f, 1.0f);
  // EmitVertex();
  // gl_Position = vec4(xy_start[0].x + 1.0f, xy_start[0].y, 1.0f, 1.0f);
  // EmitVertex();
  // gl_Position = vec4(xy_start[0].x + 1.0f, xy_start[0].y + 1.0f, 1.0f, 1.0f);
  // EmitVertex();

  // gl_Position = vec4(0.0f, 0.0f, 1.0f, 1.0f);
  // EmitVertex();
  // gl_Position = vec4(0.0f, 1.0f, 1.0f, 1.0f);
  // EmitVertex();
  // gl_Position = vec4(1.0f, 0.0f, 1.0f, 1.0f);
  // EmitVertex();
  // gl_Position = vec4(1.0f, 1.0f, 1.0f, 1.0f);
  // EmitVertex();


  EndPrimitive();
}
