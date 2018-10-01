import com.Bounce.Split;
import com.lendingKart.AndBit;
import com.lendingKart.Number;
import com.sun.tools.corba.se.idl.constExpr.And;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException
    {
//        Split split = new Split(4);
//        //int [][] mat = {{0,10,20, 0}, {0,0,0,0}, {0,10,0,0}, {10,0,0,0}};
//        int [][] mat = {{0,20,0, 0}, {0,0,10,0}, {0,0,0,2}, {5,0,0,0}};
//
//        //split.Input();
//        //split.CreateBill();
//        split.setMatrix(mat);
//        //split.CreateBill();
//        split.simplify();
//        int A = Integer.MAX_VALUE;
//        for(int i = 1;i<=26;i++)
//        {
//            System.out.println((char)(96+i) + "\t" + i);
//        }

//        Number N = new Number();
//        N.Run();
//        AndBit AB = new AndBit();
//        int [] mat = {1,3,5,500,6,9,3,8,9,75};
//        int [] result = AB.solve(mat);
//        System.out.println(result);

        System.out.println(AndBit.number_creation(1,4,4));
    }
}

/*
import java.util.*;
import java.io.*;
import java.math.*;


 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int firstInitInput = in.nextInt();
        int secondInitInput = in.nextInt();
        int thirdInitInput = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        // game loop
        while (true) {
            String firstInput = in.nextLine();
            String secondInput = in.nextLine();
            String thirdInput = in.nextLine();
            String fourthInput = in.nextLine();
            for (int i = 0; i < thirdInitInput; i++) {
                int fifthInput = in.nextInt();
                int sixthInput = in.nextInt();
            }
            in.nextLine();

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println("A");
        }
    }
}

2	4
5	4
10	10
11	11
3	12
 */