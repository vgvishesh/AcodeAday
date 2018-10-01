package com.Bounce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.abs;
//susheelk@bounceshare.com;

/*
3
4000 1000 3000 0 0 0 4000
2000 0 1000 1000 1500 500 0
 */

class transaction
{
    int name;
    int value;

    transaction(int name, int value)
    {
        this.name = name;
        this.value = value;
    }
}

class SortbyValue implements Comparator<transaction>
{
    // Used for sorting in ascending order of
    // roll Number
    public int compare(transaction a, transaction b)
    {
        return a.value - b.value;
    }
}

public class Split
{
    private int N;
    private int[][] ledger;

    public Split()
    {

    }

    public Split(int n)
    {
        this.N = n;
    }

    public void Input() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter num of people in group");
        N = Integer.parseInt(reader.readLine().split(" ")[0]);
        ledger = new int[N][N];

        for(int i =0;i<N;i++)
            for(int j =0;j<N;j++)
                ledger[i][j] = 0;
    }

    public void setMatrix(int[][] matrix)
    {
        ledger = matrix;
    }

    public void CreateBill() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] data = reader.readLine().split(" ");
        int bill = Integer.parseInt(data[0]);

        List<Integer> spent = new ArrayList<>();
        List<Integer> contribution = new ArrayList<>();
        int i = 1;
        for(;i<=N;i++)
        {
            spent.add(Integer.parseInt(data[i]));
        }

        for(;i<=2*N;i++)
        {
            contribution.add(Integer.parseInt(data[i]));
        }

        splitBill(spent, contribution, bill);
    }

    private void splitBill(List<Integer> spent, List<Integer> contribution, int bill)
    {
        List<transaction> transactions = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int value = contribution.get(i) - spent.get(i);
            transactions.add(new transaction(i, value));
        }

        Collections.sort(transactions, new SortbyValue());

        int i = 0;
        int j = N - 1;

        while (transactions.get(i).value < 0) {
            int amount = transactions.get(i).value;
            int amountPaid = transactions.get(j).value;
            int valueLeft = amountPaid - abs(amount);
            int owedBy = transactions.get(i).name;
            int owedTo = transactions.get(j).name;

            if (valueLeft >= 0) {
                ledger[owedBy][owedTo] += abs(amount);
                i++;
            } else {
                ledger[owedBy][owedTo] += transactions.get(j).value;
                //deduct the amount from total amount owed
                transactions.get(i).value += amountPaid;
                j--;
            }
        }
    }

    public void simplify()
    {
        for(int node =0;node<N;node++)
        {
            //traverse the amount owed list
            for(int child =0;child <N;child++)
            {
                //amount is owed
                if(ledger[node][child] > 0)
                {
                    reduceEdge(node,child);
                }
            }
        }

        printTransactions();
    }

    private void printTransactions()
    {
        for(int i = 0;i<N;i++) {
            for (int j = 0; j < N; j++)
                System.out.print(ledger[i][j] + " ");
            System.out.println("\n");
        }
    }

    private void reduceEdge(int node, int child)
    {
        for(int grandchild = 0; grandchild < N; grandchild++)
        {
            if(ledger[child][grandchild] > 0)
            {
                int edgeDifference = ledger[child][grandchild] - ledger[node][child];
                if(edgeDifference > 0)
                {
                    ledger[child][grandchild] = edgeDifference;
                    ledger[node][grandchild] += ledger[node][child];
                    balanceNewEdge(node, grandchild);
                    ledger[node][child] = 0;
                }

                else{
                    ledger[node][child] = abs(edgeDifference);
                    ledger[node][grandchild] += ledger[child][grandchild];
                    balanceNewEdge(node, grandchild);
                    ledger[child][grandchild] = 0;
                }
            }
        }
    }

    private void balanceNewEdge(int node, int grandchild)
    {
        int balance = ledger[grandchild][node] - ledger[node][grandchild];
        if(balance > 0)
        {
            ledger[node][grandchild] = 0;
            ledger[grandchild][node] = balance;
        }
        else if(balance < 0)
        {
            ledger[node][grandchild] = abs(balance);
            ledger[grandchild][node] = 0;
        }
        else
        {
            ledger[node][grandchild] = 0;
            ledger[grandchild][node] = 0;
        }
    }
}
