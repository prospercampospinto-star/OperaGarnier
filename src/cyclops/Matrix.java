package cyclops;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Matrix {

    public float[][] matrix;


    public Matrix(float[]... matrix){
        this.matrix = matrix;
    }



    public float[][] get() {
        return matrix;
    }

    public void set(float[][] matrix) {
        this.matrix = matrix;
    }

    public float[] toFloat(){
        float[] result  = new float[]{};

        for (int i = 0; i<matrix.length; i++){
            result = util.concatenate(result, matrix[i]);

        }

        return result;
    }

    public void setCell(float arg, int row, int col) {
        this.matrix[row][col] = arg;
    }

    public static float multiplyCell(float[][] M1, float[][] M2, int row, int col) {
        float cell = 0;
        for (int i = 0; i < M2.length; i++) {
            cell += M1[row][i] * M2[i][col];
        }
        return cell;
    }

    public void multiply(Matrix matrix2) {
        this.matrix = multiply(this, matrix2).get();
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2){

        float[][] M1 = matrix1.get();
        float[][] M2 = matrix2.get();


        float[][] M3 = new float[M1.length][M2[0].length];

        if (M1[0].length != M2.length) {

            throw new IllegalArgumentException(
                    "Cannot multiply: columns of first matrix (" + M1[0].length +
                            ") must equal rows of second matrix (" + M2.length + ")"
            );
        }

        for (int i = 0; i < M1.length; i++) {
            for (int j = 0; j < M2[0].length; j++) {
                M3[i][j] = multiplyCell(M1, M2, i, j);
            }
        }


        return new Matrix(M3);

    }


    public void print(){

        for (int i = 0; i < this.matrix.length; i++){
            System.out.print("[ ");
            for (int j = 0; j < this.matrix[i].length; j++) {

                if (j+1 < this.matrix[i].length) {
                    System.out.print(this.matrix[i][j] + ", ");
                } else {
                    System.out.print(this.matrix[i][j] + " ");
                }

            }
            System.out.println("]");
        }
    }


    public Matrix shift(vector targetVector){

        return new Matrix(
                new float[]{1, 0, 0, targetVector.getX()},
                new float[]{0, 1, 0, targetVector.getY()},
                new float[]{0, 0, 1, targetVector.getZ()},
                new float[]{0, 0, 0, 1}
        );

    }

    public Matrix rotate(vector rotation){
        double a=rotation.getX(), b= rotation.getY(), y= rotation.getZ();


        return new Matrix(
                new float[][]{  {(float) (cos(a) * cos(b) * cos(y) - sin(a) * sin(y)),  (float) (- cos(a) * cos(b) * sin(y) - sin(a) * cos(y)), (float) (cos(a) * sin(b)),  0},
                                {(float) (sin(a) * cos(b) * cos(y) + cos(a) * sin(y)),  (float) (- sin(a) * cos(b) * sin(y) + cos(a) * cos(y)), (float) (sin(a) * sin(b)),  0},
                                {(float) (- sin(b) * cos(y)),                           (float) (sin(b) * sin(y)),                              (float) cos(b),             0},
                                {0,                                                     0,                                                      0,                          0}}
        );

    }

    public Matrix scale(vector targetVector){

        return new Matrix(
                new float[]{targetVector.getX(),    0, 0, 0},
                new float[]{0,                      targetVector.getY(), 0, 0},
                new float[]{0, 0, targetVector.getZ(), 0},
                new float[]{0, 0, 0, 1}
        );

    }

    public void flip() {



        float[][] flipped = new float[matrix[0].length][matrix.length];


        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {


                flipped[j][i] = matrix[i][j];

            }
        }
        this.matrix = flipped;
    }


//    public static void main(String args[]){
//        cyclops.Matrix M1 = new cyclops.Matrix(
//                new float[]{1, 2, 3},
//                new float[]{4, 5, 6},
//                new float[]{7, 8, 9},
//                new float[]{10, 11, 12}
//        );
//        cyclops.Matrix M2 = new cyclops.Matrix(
//                new float[]{1, 0, 0, 0},
//                new float[]{0, 1, 0, 0},
//                new float[]{0, 0, 1, 0},
//                new float[]{0, 0, 0, 1}
//
//        );
//
//        M2.multiply(M1);
//        M2.print();
//        M2.flip();
//        M2.print();
//
//        float[] vertices= new float[]{};
//
//        vertices = cyclops.util.concatenate(vertices, M1.toFloat());
//        vertices = cyclops.util.concatenate(vertices, M2.toFloat());
//
//        for (float vertice : vertices)
//            System.out.print(vertice+", " );
//
//    }


}
