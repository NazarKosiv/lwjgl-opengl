package hello.lwjgl.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.lwjgl.opengl.GL20.*;

public abstract class ShaderProgram {
    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    public ShaderProgram(String vertexShaderPath, String fragmentShaderPath) {
        programID = glCreateProgram();
        vertexShaderID = loadShader(GL_VERTEX_SHADER, vertexShaderPath);
        fragmentShaderID = loadShader(GL_FRAGMENT_SHADER, fragmentShaderPath);
        glAttachShader(programID, vertexShaderID);
        glAttachShader(programID, fragmentShaderID);
        glLinkProgram(programID);
        glValidateProgram(programID);
    }

    public void start() {
        glUseProgram(programID);
    }

    public void stop() {
        glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        glDetachShader(programID, vertexShaderID);
        glDetachShader(programID, fragmentShaderID);
        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
        glDeleteProgram(programID);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName) {
        glBindAttribLocation(programID, attribute, variableName);
    }

    private static String parseShader(String path) {
        StringBuilder shaderString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                shaderString.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not parse shader.");
            System.exit(1);
        }
        return shaderString.toString();
    }

    private static int loadShader(int type, String path) {
        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, parseShader(path));
        glCompileShader(shaderID);
        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println(glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader.");
            System.exit(-1);
        }
        return shaderID;
    }
}
