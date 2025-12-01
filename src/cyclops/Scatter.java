package cyclops;

import java.util.Arrays;

public class Scatter extends Mesh{

    public Scatter(float radius, float density){
        super();
        init(radius, density);
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

            angleX = (float) ((Math.random())*Math.PI*2);
            angleY = (float) ((Math.random())*Math.PI*2);



            direction = new vector(
                    (float) (Math.cos(angleX)*Math.cos(angleY)),
                    (float) (Math.cos(angleY)*Math.sin(angleX)),
                    (float) (Math.sin(angleY))
            );
            distance = (float) (radius-radius*(Math.log(Math.random()+1)));
            distance = radius;
            x = (float) (direction.getX()*distance);
            y = (float) (direction.getY()*distance);
            z = (float) (direction.getZ()*distance);

            addV[i] = new vector(x, y, z, new float[]{1.0f});


        }

        Vertices = Arrays.copyOf(addV, addV.length);
        rootVertices = Arrays.copyOf(Vertices, Vertices.length);


    }
}
