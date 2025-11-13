import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static long window;
    public GLFWVidMode mode;
    private Triangle triangle, triangle2;

    private shader shader = new shader();
    private List<Mesh> meshes = new ArrayList<>();


    private vector v1, v2, v3, v4, v5, v6, v7, v8, v9, v10;



    private vector s1 = new vector(0.0f, 0f, 0.01f);

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        renderer.initGL();

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


        Triangle triangle = new Triangle(v1, v2, v3, v4, v5);
        Triangle triangle2 = new Triangle(v6, v7, v8, v9, v10);
        Circle circle = new Circle(0.7f, 20);


        meshes.add(triangle);
        meshes.add(triangle2);
        meshes.add(circle);


        for (Mesh mesh: meshes){
            mesh.init();
        }

        shader.init();

    }




    private void update() {
    }

    private void loop() {


        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


            // Draw Meshes

            for (Mesh mesh: meshes){
                mesh.update();
                mesh.draw();
            }

            shader.set();

            glfwSwapBuffers(window);
            glfwPollEvents();

        }
    }


    private void cleanup() {


        for (Mesh mesh: meshes) {
            mesh.cleanup();
        }
        shader.cleanup();
        glfwDestroyWindow(window);
        glfwTerminate();
    }





    public static void main(String[] args) {
        new Main().run();
    }
}