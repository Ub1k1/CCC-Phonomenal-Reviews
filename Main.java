package com.company;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class Main {
    static LinkedList<Integer>[] tree;
    static boolean[] phoRests;

    static int Prune(LinkedList<Integer>[] arrOfList){
        //arrOfList will be tree
        int prunedNodes = arrOfList.length;
        for (int i = 0; i < arrOfList.length; i++){
            int node = i;
            while (arrOfList[node].size() == 1 && !phoRests[node]){
                int temp = arrOfList[node].remove();
                arrOfList[temp].remove((Integer)node);
                prunedNodes--;
                node = temp;
            }
        }
        /*while(true){
            boolean pruned = false;
            for (int i = 0; i < arrOfList.length; i++){
                if (arrOfList[i].size() == 1 && !phoRests[i]){
                    int temp = arrOfList[i].getFirst();
                    arrOfList[i].remove();
                    arrOfList[temp].remove((Integer)i);
                    prunedNodes--;
                    pruned = true;
                }
            }
            if (pruned == false){
                break;
            }
        }*/
        //return # of nodes after pruned
        return prunedNodes;
    }

    static int[] findFarthest(int start){
        Queue<Integer> qu = new LinkedList<>();
        boolean[] visited = new boolean[tree.length];
        int[] level = new int[tree.length];
        int node = 0;
        int distance = 0;
        qu.add(start);
        level[start] = 0;
        while (!qu.isEmpty()){
            node = qu.remove();
            visited[node] = true;
            for (int child : tree[node]){
                if (!visited[child]){
                    distance = level[node] + 1;
                    qu.add(child);
                    level[child] = distance;
                }
            }
        }
        int[] result = {node, distance};
        return result;
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
        //make first pho restaurant the start node
        int startNode = Integer.parseInt(arrOfLineTwo[0]);
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

        int nodesAfterPruning = Prune(tree);

        System.out.println("after pruning");

        for (int i = 0; i < tree.length; i++){
            System.out.println(tree[i].toString());
        }

        int firstFind = findFarthest(startNode)[0];
        int secFind = findFarthest(firstFind)[1];

        int distance = secFind + (nodesAfterPruning - (secFind + 1))*2;

        System.out.println(distance);
    }
}
