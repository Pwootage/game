#version 330 core

//Inputs
in vec2 uv;

//Textures
uniform sampler2D tex;

// Outputs
out vec3 outColor;

void main() {
  outColor = texture(tex, uv).rgb;
  // outColor = inColor.rgb * texture(tex1, inTex).rgb;
}
