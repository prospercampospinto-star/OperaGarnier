import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class vector {

    public float x;
    public float y;
    public float z;
    public float args[];

    public int size;

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
        float[] coords;

        if (this.args!=null) {
            coords = Arrays.copyOf(this.args, this.args.length + 3);

            coords[0] = this.x;
            coords[1] = this.y;
            coords[2] = this.z;


            for (int i = 3; i < coords.length; i++) {
                coords[i] = this.args[i - 3];
            }
        } else {
            coords = new float[]{this.x, this.y, this.z};
        }




        return coords;
    }

    public void set(float... coords) {
        this.x = coords[0];
        this.y = coords[1];
        this.z = coords[2];

        if (coords.length>3) {
            float[] newargs = new float[coords.length-3];
            for (int i = 0; i<coords.length-3; i++) {
                newargs[i] = coords[i];
            }
            this.args = newargs;
        }

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

    public vector(float[] coords){
        this();
        this.x = coords[0];
        this.y = coords[1];
        this.z = coords[2];
    }

    public vector(float[] coords, float args[]){
        this();
        this.x = coords[0];
        this.y = coords[1];
        this.z = coords[2];
        this.args = args;
    }

    public void add(vector delta){

        this.x += delta.x;
        this.y += delta.y;
        this.z += delta.z;

    }


    public void multiply(vector delta) {


        this.x = this.x * delta.x;
        this.y = this.y * delta.y;
        this.z = this.z * delta.z;

    }

    public vector multiplyM4(Matrix matrix) {
        vector extended = new vector(this.x, this.y, this.z, new float[]{1});

        //extended.args = new float[]{1};
//        if (this.args!=null) {
//            extended.set(this.x, this.y, this.z, this.args[0]);
//        }


        Matrix Mresult = new Matrix(extended.get());


        Mresult.flip();

//        Mresult.print();
//        matrix.print();




        return new vector(Matrix.multiply(matrix, Mresult).toFloat());
    }

//    public void multiplyM(Matrix matrix) {
//        Matrix Mresult = new Matrix(this.get());
//
//        Mresult.multiply(matrix);
//
//        this.set(Mresult.toFloat());
//    }



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

    public void print(){
        float[] coords;

        if (this.args!=null) {
            coords = Arrays.copyOf(this.args, this.args.length + 3);

            coords[0] = this.x;
            coords[1] = this.y;
            coords[2] = this.z;


            for (int i = 3; i < coords.length; i++) {
                coords[i] = this.args[i - 3];
            }
        } else {
            coords = new float[]{this.x, this.y, this.z};
        }

        for (float c : coords){
            System.out.println(c);
        }
    }
}
