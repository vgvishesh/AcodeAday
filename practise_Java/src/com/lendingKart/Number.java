package com.lendingKart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Number
{
    private int t;
    public void Run() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.t = Integer.parseInt(reader.readLine());
        while(t-->0)
        {
            String[] data = reader.readLine().split(" ");
            int n = Integer.parseInt(data[0]);
            int k = Integer.parseInt(data[1]);
            process(n,k);
        }
    }

    private void process(int n, int k)
    {
        int sum = k;
        char [] data = new char[n];
        int index = n-1;
        while(sum >= 26 && index >= 0)
        {
            data[index--] = 'z';
            sum-=26;
        }

        for(int j = 0;j<index; j++)
        {
            data[j] = 'a';
            sum-=1;
        }

        if(index > 0 && sum>0)
            data[index] = (char)(96+sum);

        //data.toString();
        System.out.println(new String(data));
    }
}
