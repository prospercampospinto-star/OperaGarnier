package cyclops;

import java.util.Arrays;

public class debug {

    public static void main(String args[]){

        vector v1 = new vector(10f, 10f, 10f);
        vector v2 = new vector(11f, 11f, 11f);



        float[] coords = util.concatenate(v1.get(), v2.get());

        System.out.println("v1");
        System.out.println(Arrays.toString(coords));


    }


}
