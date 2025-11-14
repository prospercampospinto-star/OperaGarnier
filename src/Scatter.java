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
        float x, y, z, angle, distance;



        for (int i = 0; i<resolution; i++) {
            angle = (float) ((Math.random()-0.5)*6.20);

            direction = new vector((float) (Math.cos(angle)), (float) (Math.sin(angle)), (float) (Math.tan(angle)));
            distance = (float) (radius*Math.random());

            x = (float) (direction.getX()*distance);
            y = (float) (direction.getY()*distance);
            z = (float) (direction.getZ()*distance);

            addV[i] = new vector(x, y, z);


        }

        Vertices = Arrays.copyOf(addV, addV.length);


    }
}
