package cyclops;

import java.lang.reflect.Array;

public class util{


//    public static cyclops.vector rotate(cyclops.vector targetVector, cyclops.vector anchorVector, cyclops.vector rotateVector) {
//
//        return targetVector;
//
//    }

    public static vector rotateX(vector vector, double angle) {
        float x = vector.getX();
        float y = vector.getY();
        float z = vector.getZ();

        vector.setX(x);
        vector.setY((float) (y*Math.cos(angle)+z*Math.sin(angle)));
        vector.setZ((float) ((-y*Math.sin(angle)+z*Math.cos(angle))));

        return vector;
    }

    public static vector rotateY(vector vector, double angle) {
        float x = vector.getX();
        float y = vector.getY();
        float z = vector.getZ();

        vector.setX((float) (x*Math.cos(angle) - z*Math.sin(angle)));
        vector.setY(y);
        vector.setZ((float) (x*Math.sin(angle) + z*Math.cos(angle)));

        return vector;
    }

    public static vector rotateZ(vector vector, double angle) {
        float x = vector.getX();
        float y = vector.getY();
        float z = vector.getZ();

        vector.setX((float) (x*Math.cos(angle)+y*Math.sin(angle)));
        vector.setY((float) (-x*Math.sin(angle)+y*Math.cos(angle)));
        vector.setZ(z);



        return vector;
    }

    public static float[] concatenate(float[] a, float[] b) {
        int aLen = a.length;
        int bLen = b.length;

        float[] c = (float[]) Array.newInstance(a.getClass().getComponentType(), aLen+bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    public static vector[] concatenateV(vector[] a, vector[] b) {
        int aLen = a.length;
        int bLen = b.length;

        vector[] c = (vector[]) Array.newInstance(a.getClass().getComponentType(), aLen+bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

}
