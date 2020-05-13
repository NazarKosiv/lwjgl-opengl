package hello.lwjgl.shaders;

public class StaticShader extends ShaderProgram {
    public StaticShader(String vertexShaderPath, String fragmentShaderPath) {
        super(vertexShaderPath, fragmentShaderPath);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
