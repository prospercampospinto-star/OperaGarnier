import java.util.Vector;

public class vector {

    public int size;
    private float x, y, z;
    public float args[];

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float[] getArgs() {
        return args;
    }

    public void setArgs(float[] args) {
        this.args = args;
    }


    public float[] get() {

        float[] coords = new float[]{this.x, this.y, this.z};

        return coords;
    }


    public vector() {
    }


    public vector(float x, float y, float z) {
        this();
        this.x = x;
        this.y = y;
        this.z = z;

        this.size = 3;
    }

    public vector(float x, float y, float z,float args[]) {
        this();
        this.x = x;
        this.y = y;
        this.z = z;
        this.args = args;

        this.size = 3 + args.length;
    }

    public void add(vector delta){

        this.x += delta.x;
        this.y += delta.y;
        this.z += delta.z;

    }

    public void rotate(double angleX, double angleY, double angleZ){

        vector rotatedX = util.rotateX(this, angleX);

        vector rotatedY = util.rotateY(rotatedX, angleY);

        vector rotatedZ = util.rotateZ(rotatedY, angleZ);


        this.x = rotatedZ.x;
        this.y = rotatedZ.y;
        this.z = rotatedZ.z;

    }

    public static float[] toVertices(vector[] Vertices) {

        float[] fvertices = new float[]{};

        for (int i = 0; i<Vertices.length; i++) {

            fvertices = util.concatenate(fvertices, Vertices[i].get());

        }

        return fvertices;

    }


}