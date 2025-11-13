import java.util.Arrays;

public class Circle extends Mesh {

    public Circle(float radius, int division){
        init(radius, division);
    }

    public void init(float radius, int division) {
        vector v1 = new vector();
        vector[] addV = new vector[]{v1};
        float x, y, z, angle;

        for (int i = 0; i<division; i++) {

            System.out.println(i);
            angle = (float) (Math.PI*2/division) * i;
            x = (float) (radius * Math.cos(angle));
            y = (float) (radius * Math.sin(angle));
            z = 0;

            v1.set(x, y, z);
            addV = new vector[]{v1};
            Vertices = Arrays.copyOf(addV, Vertices.length+1);
        }

    }

}
