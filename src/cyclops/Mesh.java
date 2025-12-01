package cyclops;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.util.Arrays;

import static cyclops.Main.getShaderProgram;
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
    vector[] rootVertices = {};

    float[] vertices = {};

    int vertexSize;

    vector origin = new vector(0, 0, 0);
    vector translation = new vector(0, 0, 0);
    vector rotation = new vector(0, 0, 0);
    vector scale = new vector(1, 1, 1);

    vector[] transform = {origin, translation, rotation, scale};

    Matrix Init = new Matrix(
            new float[]{1, 0, 0, 0},
            new float[]{0, 1, 0, 0},
            new float[]{0, 0, 1, 0},
            new float[]{0, 0, 0, 1}
    );

    Matrix Translation = new Matrix(
            new float[]{1, 0, 0, translation.getX()},
            new float[]{0, 1, 0, translation.getY()},
            new float[]{0, 0, 1, translation.getZ()},
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


    public Mesh(vector... Vertices) {
        this();


        this.Vertices = Vertices;
        this.vertices = vector.toVertices(Vertices);

        this.rootVertices = Arrays.copyOf(Vertices, Vertices.length);

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

//        for (int i = 0; i < this.Vertices.length;  i++) {
//            this.Vertices[i].rotate(0.004, 0.003, -0.001);
//        }

        cyclops.vector speed = new cyclops.vector(0.00f, (float) (0.001*Math.cos(time*0.00000001)), 0.00001f);
        cyclops.vector angularSpeed = new cyclops.vector(0.0001f, 0.0001f, (float) (0.01*Math.cos(time*0.0000000001)));
        cyclops.vector deltaScale = new cyclops.vector(1.0f, (float) (1*Math.sin(time*0.000000001)), (float) (1*Math.cos(time*0.0000000001)));


        translate(speed);
        rotate(angularSpeed);
        scale(deltaScale);

        updateMatrices();

        //MVP.print();




        this.Vertices = Arrays.copyOf(rootVertices, rootVertices.length);

        for (int i = 0; i < this.Vertices.length;  i++) {
            //System.out.print("vertice :");
            //this.Vertices[i].print();

            this.Vertices[i] = this.Vertices[i].multiplyM4(MVP);
        }

        int programID = getShaderProgram();

        int MatrixID = glGetUniformLocation(programID, "MVP");

        glUniformMatrix4fv(MatrixID, false, MVP.toFloat());


        this.vertices = vector.toVertices(this.Vertices);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = MemoryUtil.memAllocFloat(vertices.length);
        buffer.put(vertices).flip();
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        MemoryUtil.memFree(buffer);

    }

    public void draw(){
        vertexSize = Vertices[0].getFull().length;

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

    public void translate(vector translated) {
        this.translation.add(translated);
    }

    public void rotate(vector rotated) {
        this.rotation.add(rotated);
    }

    public void scale(vector scaled) {
        this.scale = scaled;
    }

    public void updateMatrices() {
        Translation = Translation.shift(translation);

        float a=rotation.getX();

        Rotation = Rotation.rotate(rotation);

        Scale = Scale.scale(scale);

        MVP.matrix = Init.matrix;
        MVP.multiply(Translation);
        MVP.multiply(Rotation);
        MVP.multiply(Scale);

    }

}
