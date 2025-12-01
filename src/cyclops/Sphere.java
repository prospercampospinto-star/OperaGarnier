package cyclops;

import java.util.Arrays;

public class Sphere extends Mesh{


    public Sphere(float size, int rows, int cols){


    }

    public void init(float radius, float density) {
        vector direction;

        if (1 < density || density < 0) {
            throw new IllegalArgumentException(
                    "Invalid density value : Expected number between 0 and 1 Recieved "+ density
            );
        }

        int resolution = (int) (1/(1-density));

        vector v1 = new vector();



        vector[] addV = new vector[resolution];
        float x, y, z, angleX, angleY, distance;



        for (int i = 0; i<resolution; i++) {
            angleX = (float) ((Math.random()-0.5)*Math.PI*2);
            angleY = (float) ((Math.random()-0.5)*Math.PI*2);

            direction = new vector((float) (Math.cos(angleX)*Math.cos(angleY)), (float) (Math.cos(angleY)*Math.sin(angleX)), (float) (Math.sin(angleY)));
            distance = (float) (radius*Math.random());
            distance = 1;
            x = (float) (direction.getX()*distance);
            y = (float) (direction.getY()*distance);
            z = (float) (direction.getZ()*distance);

            addV[i] = new vector(x, y, z);


        }

        Vertices = Arrays.copyOf(addV, addV.length);


    }
}
