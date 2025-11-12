import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;

public class shader {
    private int shaderProgram;

    public void init() {



         //Create shaders
        String vertexShader = """
            #version 330 core
            
            
            
            layout (location = 0) in vec3 aPos;

            void main() {
                gl_Position = vec4(aPos, 1.0);
            }
            """;




        String fragmentShader = """
            #version 330 core
            out vec4 frag_Color;
            
            void main() {
                frag_Color = vec4(1.0, 1.0, 0.7, 1.0); // Orange color
            }
            """;

        int vs = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vs, vertexShader);
        glCompileShader(vs);

        int fs = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fs, fragmentShader);
        glCompileShader(fs);

        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vs);
        glAttachShader(shaderProgram, fs);
        glLinkProgram(shaderProgram);

        glDeleteShader(vs);
        glDeleteShader(fs);
    }

    public void set() {
        glUseProgram(shaderProgram);
    }

    public void cleanup() {
        glDeleteProgram(shaderProgram);
    }

}