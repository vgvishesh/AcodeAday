package com.lendingKart;

import javafx.util.Pair;

import java.util.*;

public class Questions
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
            adCost = findCost(a,x,b,current - 1, (int) (cost + a));
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

    static int triangle(int a, int b, int c) {
        int ab = a + b;
        int bc = b + c;
        int ca = c + a;

        boolean isValid = (a > 0) && b>0 && c>0;

        if(!isValid)
            return 0;

        boolean isEquilateral  = (a == b) && (b ==c);
        if(isEquilateral)
        {
            return 1;
        }

        boolean isTriangle = (ab > c) && (bc > a) && (ca > b);
        return isTriangle ? 2 : 0;
    }

    static int[] delta_encode(int[] array) {
        /*
         * Write your code here.
         */
        //int [] data = new int[array.length * 2];
        List<Integer> data = new ArrayList<>();
        data.add(array[0]);
        for(int i=1;i<array.length;i++)
        {
            int diff = array[i] - array[i-1];
            if(-127 <= diff && diff <= 127)
            {
                data.add(diff);
            }
            else
            {
                data.add(-128);
                data.add(diff);
            }
        }
        return data.stream().mapToInt(i->i).toArray();
    }

    static List<Integer> sort_hotels(String keywords, List<Integer> hotel_ids, List<String> reviews) {

        String[] keywordData = keywords.split(" ");
        HashMap<Integer, String> map = new HashMap<>();
        for(int i=0;i<hotel_ids.size();i++)
        {
            int id = hotel_ids.get(i);
            StringBuilder review = new StringBuilder(reviews.get(i));
            String value = map.get(id);
            if(value != null) {
                review.append(" ");
                review.append(value);
            }
            map.put(id, review.toString());
        }

        List<Pair<Integer, Integer>> order = new ArrayList<>();

        map.forEach((key, value) -> {
            HashMap<String, Integer> wordFrequency = new HashMap<>();
            String[] words = value.split(" ");
            for(int i = 0;i<words.length; i++)
            {
                String word = words[i];
                if(wordFrequency.containsKey(word)) {
                    int count = wordFrequency.get(word);
                    wordFrequency.put(word, ++count);
                }
                else
                {
                    wordFrequency.put(word, 1);
                }
            }

            int score = 0;
            for (String keyword : keywordData) {
                if(wordFrequency.containsKey(keyword))
                {
                    score += wordFrequency.get(keyword);
                }
            }

            order.add(new Pair<>(key, score));
        });

        Collections.sort(order, (a, b) -> b.getValue() - a.getValue());

        List<Integer> sortedList = new ArrayList<>();
        order.forEach(node -> {
            sortedList.add(node.getKey());
        });

        return sortedList;
    }
}
