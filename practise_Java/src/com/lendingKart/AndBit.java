package com.lendingKart;

public class AndBit
{
    public int[] solve(int[] arr){
        int [] data = new int[arr.length];
        for(int i =0;i<arr.length;i++)
        {
            //if(data[i] != 1) {
                for (int j = i + 1; j < arr.length; j++) {
                    int result = arr[i] & arr[j];
                    if (result == 0) {
                        data[i] = 1;
                        data[j] = 1;
                    }
                }
            //}
        }
        return data;
    }

    static public long number_creation(long A, long X, long B){
        return findCost(A,X,B,0, 0);
    }

    private static long findCost(long a, long x, long b, int current, int cost)
    {
        if(current == x)
        {
            return cost;
        }
        if(current < 0)
        {
            return Integer.MAX_VALUE;
        }

        long adCost;
        if(current > x)
        {
            adCost = findCost(a,x,b,current - 1, cost += a);
        }
        else
        {
            adCost = findCost(a,x,b,current+1, (int) (cost+a));
        }

        //long subs1 = findCost(a,x,b,current - 1, (int) (cost + a));
        long mul2 = Integer.MAX_VALUE;
        if(current < x && current!=0)
            mul2 = findCost(a,x,b,current * 2, (int) (cost + b));

        long min = Long.min(adCost, mul2);
        return Long.min(min, mul2);
    }
}
