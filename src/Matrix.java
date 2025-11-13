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


    public void shift(vector targetVector){

        Matrix shiftMatrix = new Matrix(
                new float[]{1, 0, 0, targetVector.getX()},
                new float[]{0, 1, 0, targetVector.getY()},
                new float[]{0, 0, 1, targetVector.getZ()},
                new float[]{0, 0, 0, 1}
        );

    }

    public void rotate(vector targetVector, vector rotation){



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
//        Matrix M1 = new Matrix(
//                new float[]{1, 2, 3},
//                new float[]{4, 5, 6},
//                new float[]{7, 8, 9},
//                new float[]{10, 11, 12}
//        );
//        Matrix M2 = new Matrix(
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
//        vertices = util.concatenate(vertices, M1.toFloat());
//        vertices = util.concatenate(vertices, M2.toFloat());
//
//        for (float vertice : vertices)
//            System.out.print(vertice+", " );
//
//    }


}
