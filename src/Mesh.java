import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static java.lang.System.nanoTime;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Mesh {
    float time = nanoTime();

    int vao, vbo;

    vector[] Vertices = {};

    float[] vertices = {};
    private float[] rootvertices = {};

    int vertexSize;

    Matrix Translation = new Matrix(
            new float[]{1, 0, 0, 0},
            new float[]{0, 1, 0, 0},
            new float[]{0, 0, 1, 0},
            new float[]{0, 0, 0, 1}
    );

    Matrix Rotation = new Matrix(
            new float[]{1, 0, 0, 0},
            new float[]{0, 1, 0, 0},
            new float[]{0, 0, 1, 0},
            new float[]{0, 0, 0, 1}
    );

    Matrix Scale = new Matrix(
            new float[]{1, 0, 0, 0},
            new float[]{0, 1, 0, 0},
            new float[]{0, 0, 1, 0},
            new float[]{0, 0, 0, 1}
    );

    Matrix MVP = new Matrix(
            new float[]{1, 0, 0, 0},
            new float[]{0, 1, 0, 0},
            new float[]{0, 0, 1, 0},
            new float[]{0, 0, 0, 1}
    );


    private shader shader = new shader();


    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }
    private void addVertex(vector vertex) {
        this.vertices = util.concatenate(this.vertices, vertex.get());
    }

    public Mesh() {
    }


    public Mesh(vector... vertices) {
        this();
        this.Vertices = vertices;


        System.out.println(this.Vertices);
        this.vertices = vector.toVertices(vertices);


        rootvertices = this.vertices;

    }

    public void init(){


        // Create VAO
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // Create VBO and upload data
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

    }

    public void update() {
        time=nanoTime();

        for (int i = 0; i < this.Vertices.length;  i++) {
            this.Vertices[i].rotate(0.01, 0.005, -0.002);

        }

        this.vertices = vector.toVertices(this.Vertices);

        int programID = glCreateProgram();

        int MatrixID = glGetUniformLocation(programID, "MVP");

        glUniformMatrix4fv(MatrixID, false, MVP.toFloat());

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = MemoryUtil.memAllocFloat(vertices.length);
        buffer.put(vertices).flip();
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        MemoryUtil.memFree(buffer);

    }



    public void draw(){

        vertexSize = Vertices[0].size;

        glPointSize(10.0f);

        glBindVertexArray(vao);
        glDrawArrays(GL_POINTS, 0, vertices.length/vertexSize);




        // Set up vertex attributes
        glVertexAttribPointer(0, 3, GL_FLOAT, false, vertexSize * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
    }

    public void cleanup() {

        glDeleteVertexArrays(vao);
        glDeleteBuffers(vbo);
    }



}
