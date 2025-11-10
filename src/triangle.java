import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static java.lang.System.nanoTime;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class triangle {

    int vao, vbo;

    private vector[] Vertices = {};

    private float[] vertices = {};
    private float[] rootvertices = {};
    private shader shader = new shader();

    float time = nanoTime();

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    private void addVertex(vector vertex) {
        this.vertices = util.concatenate(this.vertices, vertex.get());
    }



    public void init(vector v1,vector v2,vector v3,vector v4,vector v5) {
        // Triangle vertices (x, y, z)
        Vertices = new vector[]{
            v1, v2, v3, v4, v5

        };




//        vertices = new float[]{
//                0.0f, 0.5f, 0.0f,   // Top
//                0.48f, 0.15f, 0.0f, // Top right
//                0.29f, -0.40f, 0.0f, // Bottom right
//                -0.29f, -0.40f, 0.0f, // Bottom left
//                -0.48f, 0.15f, 0.0f  // Top left
//        };

        vertices = vector.toVertices(Vertices);

        System.out.println(vertices.toString());

        rootvertices = vertices;

        // Create VAO
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // Create VBO and upload data
        vbo = glGenBuffers();


    }

    public void updateTriangle() {
        time=nanoTime();

        for (int i = 0; i < this.Vertices.length;  i++) {
            this.Vertices[i].rotate(0.01, 0.005, -0.002);

        }

        this.vertices = vector.toVertices(this.Vertices);

//        int i;
//
//        for (i = 0; i < this.vertices.length;  i+=3) {
//            this.vertices[i] = (float) (this.vertices[i] + 0.004f*(Math.cos(time*0.000000001)));
//
//        }
//
//        for (i = 1; i < this.vertices.length;  i+=3) {
//            this.vertices[i] = (float) (this.vertices[i] + 0.004f*(Math.sin(time*0.000000001)));
//
//        }
//
//
//        for (i = 2; i < this.vertices.length;  i+=3) {
//            this.vertices[i] = (float) (this.vertices[i] + 0.0008f*(Math.cos(time*0.000000001)));
//
//        }

    }

    public void drawTriangle(){

        glBindVertexArray(vao);
        glDrawArrays(GL_TRIANGLE_FAN, 0, 5);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = MemoryUtil.memAllocFloat(vertices.length);
        buffer.put(vertices).flip();
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        MemoryUtil.memFree(buffer);

        // Set up vertex attributes
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
    }

    public void cleanupTriangle() {

        glDeleteVertexArrays(vao);
        glDeleteBuffers(vbo);
    }


}
