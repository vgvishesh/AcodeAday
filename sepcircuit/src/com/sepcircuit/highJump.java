package com.sepcircuit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class obstacle
{
    double p,h;
    double b,e;

    obstacle()
    {
        p = 0;
        this.h = 0;
        this.b = 0;
        this.e = 0;
    }

    obstacle(double position , double height, double begin, double end)
    {
        p = position;
        this.h = height;
        this.b = begin;
        this.e = end;
    }
}

class highJump
{
    //position, height
    private List<obstacle> positinHeight = new ArrayList<>();
    private Integer N;
    private Integer M;
    private Integer L;
    private Integer A;

    public void run() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] data = reader.readLine().split(" ");
        this.N = Integer.parseInt(data[0]);
        this.M = Integer.parseInt(data[1]);
        this.L = Integer.parseInt(data[2]);
        this.A = Integer.parseInt(data[3]);

        for(int i =0;i<this.N;i++)
        {
            data = reader.readLine().split(" ");
            int position = Integer.parseInt(data[0]);
            int height = Integer.parseInt(data[1]);
            double begin = getStartingX(position, height);
            double end = getEndingX(position, height);
            positinHeight.add(new obstacle(position, height, begin, end));
        }

        String result = process()? "Yes" : "No";
        System.out.println(result);
    }

    private boolean process()
    {
        obstacle currentMajor = positinHeight.get(0);
        int index = 1;
        while (index<positinHeight.size())
        {
            if(currentMajor.b < 0)
                return false;

            obstacle _this = positinHeight.get(index);
            if(currentMajor.b < _this.b)
            {
                if(currentMajor.e <= _this.b)
                    currentMajor = _this;
                else
                {
                    currentMajor = getIntersectionPoint(currentMajor, _this);
                    if(currentMajor.h > M)
                        return false;
                }
            }
            else
            {
                currentMajor = _this;
            }
            ++index;

        }
        return true;
    }

    private obstacle getIntersectionPoint(obstacle currentMajor, obstacle _this)
    {
        double x = (currentMajor.b + _this.e)/(double)2;
        double y = A*(x - currentMajor.b);
        return new obstacle(x,y,currentMajor.b, _this.e);
    }

    private double getStartingX(int pi, int hi)
    {
        return (double)(pi*A - hi)/(double)A;
    }

    private double getEndingX(int pi, int hi)
    {
        return (double)(pi*A + hi)/(double)A;
    }
}
