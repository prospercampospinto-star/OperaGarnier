package cyclops;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_BLUE_BITS;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_GREEN_BITS;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_RED_BITS;
import static org.lwjgl.glfw.GLFW.GLFW_REFRESH_RATE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    private long monitor;
    private GLFWVidMode mode;

    public GLFWVidMode getMode() {
        return mode;
    }

    public long getMonitor() {
        return monitor;
    }

    public void setMonitor(long monitor) {
        this.monitor = monitor;
    }

    public Renderer() {
        this.initGL();
    }


    public void initGL() {

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        monitor = glfwGetPrimaryMonitor();

        mode = glfwGetVideoMode(monitor);



        glfwWindowHint(GLFW_RED_BITS, mode.redBits());
        glfwWindowHint(GLFW_GREEN_BITS, mode.greenBits());
        glfwWindowHint(GLFW_BLUE_BITS, mode.blueBits());
        glfwWindowHint(GLFW_REFRESH_RATE, mode.refreshRate());

        // Request OpenGL 3.3 Core Profile
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // Needed for Mac



        Main.window = glfwCreateWindow(mode.width(), mode.height(), "cyclops.Main", 0, 0);

        if (Main.window == 0) {
            throw new RuntimeException("Failed to create window");
        }

        glfwMakeContextCurrent(Main.window);
        GL.createCapabilities();
        glClearColor(0.1f, 0.1f, 0.2f, 1.0f);


    }


}
