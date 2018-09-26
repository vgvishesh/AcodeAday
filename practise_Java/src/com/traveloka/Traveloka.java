package com.traveloka;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Counter
{
    public int count = 0;
    public void increase()
    {
        ++count;
    }
    public int getValue()
    {
        return count;
    }
}
public class Traveloka
{

    public int kDifference(List<Integer> numbers, int k)
    {
        HashSet<Integer> hash = new HashSet<>();
        numbers.forEach(num -> hash.add(num));

        Counter count = new Counter();
        numbers.forEach(num -> {
            int target = num + k;
            if (hash.contains(target)) {
                count.increase();
            }
        });
        return count.getValue();
    }

    public static void printPattern(int n)
    {
        List<Character> base = new ArrayList<>();
        boolean x = true;
        int total = (n - 1) * 2 + 1;
        for (int i = 0; i <= total / 2; i++) {
            if (i % 2 != 0) {
                base.add(' ');
                continue;
            }
            if (x) {
                base.add('x');
                x = false;
            } else {
                base.add('o');
                x = true;
            }
        }
        if (base.get(base.size() - 1) != ' ')
            base.add(' ');

        base.forEach(val -> {
            System.out.print(val + " ");
        });
    }
}
