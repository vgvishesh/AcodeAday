package com.Azavista;

import static java.lang.Math.abs;

class Element
{
    int value;
    int index;
    public Element(int v, int i)
    {
        value = v;
        index = i;
    }
}

public class ChainBreak
{
    public int solution(int[] array) {
        Element min = getMinimumElement(array);
        Element other = getSecondMinimumElement(array, min);
        return min.value + other.value;
    }

    private Element getSecondMinimumElement(int[] array, Element min)
    {
        int i = 1;
        Element tofind = new Element(Integer.MAX_VALUE, -1);
        while (i < array.length)
        {
            if(array[i] >= min.value && array[i] < tofind.value && abs(i - min.index) > 1)
            {
                tofind.value = array[i];
                tofind.index = i;
            }
            ++i;
        }
        return tofind;
    }

    private Element getMinimumElement(int[] array)
    {
        int i =1;
        Element min = new Element(Integer.MAX_VALUE, -1);
        while (i < array.length)
        {
            if(array[i] < min.value)
            {
                min.value = array[i];
                min.index = i;
            }
            ++i;
        }
        return min;
    }
}

/*
class Solution {
    int solution(int[] A, int K) {
        int n = A.length;
        int best = 0;
        int count = 1;
        for (int i = 0; i < n - K - 1; i++) {
            if (A[i] == A[i + 1])
                count = count + 1;
            else
                count = 0;
            if (count > best)
                best = count;
        }
        int result = best + 1 + K;

        return result;
    }
}
 */
