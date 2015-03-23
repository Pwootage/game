#version 330 core

// Inputs
//in vec2 inTex;
//in vec4 inColor;

//Textures
uniform sampler2D tex1;

// Outputs
out vec3 outColor;

void main() {
  outColor = vec3(1.0f,1.0f,1.0f);
  // outColor = inColor.rgb * texture(tex1, inTex).rgb;
}
