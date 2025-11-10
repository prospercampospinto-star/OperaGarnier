import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private long window;
    public GLFWVidMode mode;

    private shader shader = new shader();
    private triangle triangle = new triangle();
    private triangle triangle2 = new triangle();


    private vector v1, v2, v3, v4, v5, v6, v7, v8, v9, v10;

    private vector s1 = new vector(0.0f, 0f, 0.01f);

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        long monitor = glfwGetPrimaryMonitor();

        GLFWVidMode mode = glfwGetVideoMode(monitor);

        glfwWindowHint(GLFW_RED_BITS, mode.redBits());
        glfwWindowHint(GLFW_GREEN_BITS, mode.greenBits());
        glfwWindowHint(GLFW_BLUE_BITS, mode.blueBits());
        glfwWindowHint(GLFW_REFRESH_RATE, mode.refreshRate());



        // Request OpenGL 3.3 Core Profile
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // Needed for Mac

         window = glfwCreateWindow(mode.width(), mode.height(), "Main", 0, 0);

        if (window == 0) {
            throw new RuntimeException("Failed to create window");
        }

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glClearColor(0.1f, 0.1f, 0.2f, 1.0f);

        v1 = new vector(0.0f, 0.5f, -0.4f);
        v2 = new vector(0.48f, 0.15f, -0.4f);
        v3 = new vector(0.29f, -0.40f, -0.4f);
        v4 = new vector(-0.29f, -0.40f, -0.4f);
        v5 = new vector(-0.48f, 0.15f, -0.4f);

        v6 = new vector(0.0f, 0.5f, 0.4f);
        v7 = new vector(0.48f, 0.15f, 0.4f);
        v8 = new vector(0.29f, -0.40f, 0.4f);
        v9 = new vector(-0.29f, -0.40f, 0.4f);
        v10 = new vector(-0.48f, 0.15f, 0.4f);

       // v6.add();

        triangle.init(v1, v2, v3, v4, v5);
        triangle2.init(v6, v7, v8, v9, v10);


        shader.init();

    }




    private void update() {
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Draw triangle
            triangle.drawTriangle();
            triangle2.drawTriangle();


            shader.set();

            glfwSwapBuffers(window);
            glfwPollEvents();

            triangle.updateTriangle();
            triangle2.updateTriangle();

        }
    }


    private void cleanup() {
        triangle.cleanupTriangle();
        shader.cleanup();
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}