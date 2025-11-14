import java.util.Arrays;

public class Circle extends Mesh {

    public Circle(float radius, int division){
        super();
        init(radius, division);
    }

    public void init(float radius, int division) {
        vector v1 = new vector();
        vector[] addV = new vector[division];
        float x, y, z, angle;

        for (int i = 0; i<division; i++) {

            System.out.println(i);
            angle = (float) (Math.PI*2/division) * i;
            x = (float) (radius * Math.cos(angle));
            y = (float) (radius * Math.sin(angle));
            z = 0;

            addV[i] = new vector(x, y, z);


        }

        Vertices = Arrays.copyOf(addV, addV.length);


        for(vector vec: addV) {
            vec.print();
        }

        System.out.println("EXPOSED:");

        for(vector vec: Vertices) {
            vec.print();
        }

    }

}
