package com.company;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static LinkedList<Integer>[] tree;
    static boolean[] phoRests;

    static void Prune(LinkedList<Integer>[] arrOfList){
        //arrOfList will be tree
        while(true){
            boolean pruned = false;
            for (int i = 0; i < arrOfList.length; i++){
                if (arrOfList[i].size() == 1 && !phoRests[i]){
                    int temp = arrOfList[i].getFirst();
                    arrOfList[i].remove();
                    arrOfList[temp].remove((Integer)i);
                    pruned = true;
                }
            }
            if (pruned == false){
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String lineOne = scanner.nextLine();
        String[] arrOfLineOne = lineOne.split(" ");
        int N = Integer.parseInt(arrOfLineOne[0]);
        tree = new LinkedList[N];
        int M = Integer.parseInt(arrOfLineOne[1]);
        String lineTwo = scanner.nextLine();
        String[] arrOfLineTwo = lineTwo.split(" ");
        phoRests = new boolean[N];

        for (int i = 0; i < arrOfLineTwo.length; i++){
            phoRests[Integer.parseInt(arrOfLineTwo[i])] = true;
        }

        int[][] moreInputs = new int[N-1][2];

        for (int i = 0; i < tree.length; i++){
            tree[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < N-1; i++){
            String input = scanner.nextLine();
            String[] arrOfInput = input.split(" ");
            int x = Integer.parseInt(arrOfInput[0]);
            int y = Integer.parseInt(arrOfInput[1]);
            tree[x].add(y);
            tree[y].add(x);
        }

        for (int i = 0; i < tree.length; i++){
            System.out.println(tree[i].toString());
        }

        Prune(tree);

        System.out.println("after pruning");

        for (int i = 0; i < tree.length; i++){
            System.out.println(tree[i].toString());
        }

    }
}
