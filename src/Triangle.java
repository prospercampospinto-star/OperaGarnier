import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Triangle extends Mesh {

    public Triangle(vector... vertices){
        super(vertices);
    }

}
