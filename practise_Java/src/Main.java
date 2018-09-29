import com.Bounce.Split;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException
    {
        Split split = new Split(4);
        //int [][] mat = {{0,10,20, 0}, {0,0,0,0}, {0,10,0,0}, {10,0,0,0}};
        int [][] mat = {{0,20,0, 0}, {0,0,10,0}, {0,0,0,2}, {5,0,0,0}};

        //split.Input();
        //split.CreateBill();
        split.setMatrix(mat);
        //split.CreateBill();
        split.simplify();
    }
}
