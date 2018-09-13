/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4750homeworknumber2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author crowe_000
 */
public class CS4750HomeworkNumber2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                final long startTime = System.currentTimeMillis();
        
//        int[][] startingBoard = {
//            {1,2,7,3},
//            {5,6,11,4},
//            {9,10,15,8},
//            {13,14,12,0}
//        };
        int[][] startingBoard = {
            {5,1,7,3},
            {9,2,11,4},
            {13,6,15,8},
            {0,10,14,12}
        };   
        int[][] solutionBoard = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
        };
        
        int expandedNodes = 0;
        int solutionLength = 0;
        
        Node solutionNode = new Node(solutionBoard);  //goal state
        Node rootNode = new Node(startingBoard);      //starting state
        Node tempNode;  //current state
        
        Node[] tempArray;
        HashSet<Node> closed = new HashSet<>();   //nodes that have already been visited
 
        Stack<Node> solutionStack = new Stack<>();   // solution path
        ArrayList<Node> aStarOpened = new ArrayList<Node>();  //fringe for A*
        
      
        aStarOpened.add(rootNode); //add starting board to the fringe
        while (!aStarOpened.isEmpty() && expandedNodes <= 1000000){  
           
            tempNode = getMinHeuristic(aStarOpened);  //get node with the best Heuristic
            aStarOpened.remove(getMinHeuristic(aStarOpened)); //remove that node from the fringe
            
            if (tempNode.equals(solutionNode)){
                //case for solution
                while (tempNode != null) {  //add solution and all its parents to the solution stack
                    solutionStack.add(tempNode);
                    tempNode = tempNode.parent;
                } 
                break;
            }
            if (!closed.contains(tempNode)){  //if node has not yet been evaluated
                closed.add(tempNode);
                tempArray = tempNode.expand();  //expand all options
                expandedNodes++;
              
                if (expandedNodes <= 5){
                    System.out.println(Arrays.deepToString(tempNode.board)); //print out first five states
                }  
                for (int i = tempArray.length - 1; i >= 0; i--){   //add expanded nodes to fringe
                    if (tempArray[i] != null){
                        aStarOpened.add(tempArray[i]);
                    }
                }
            }
        }
        //after while loop
        if (solutionStack.isEmpty()){
            System.out.println("No solution found");
        } else {
            System.out.println("Solution Found!!!!!");
            while (!solutionStack.isEmpty()){
                tempNode = solutionStack.pop();
                System.out.println(Arrays.deepToString(tempNode.board));   //print out solution path
                solutionLength++;
            }
            System.out.println("The total number of moves is: " + solutionLength);
        }
        System.out.println("Number of nodes expanded: " + expandedNodes);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) );
        
    }
        public static Node getMinHeuristic(ArrayList<Node> aStarOpened) {  //sent the array of fringe
        int min = manhattanHeuristic(aStarOpened.get(0));  //use the first node to compare 
        Node tempNode = aStarOpened.get(0);
        
        for (int i = 0; i < aStarOpened.size(); i++) {  //find the node in fringe with the lowest overall heuristic
            if(manhattanHeuristic(aStarOpened.get(i)) < min) {
                min = manhattanHeuristic(aStarOpened.get(i));
                tempNode = aStarOpened.get(i);
            }
        }    
        
        return tempNode;  //return node with lowest overall heuristic
    }
        
    public static int manhattanHeuristic(Node node) {  //evaluates the heuristic of each node based on Manhattan distance and path cost
        int mhSum = 0;
        
        for (int i=0; i <= 3; i++){
            for (int j = 0; j <= 3; j++){
              
                switch (node.board[i][j]) {  //compares where each number is to where is should be: Manhattan distance
                    case 1:
                        mhSum += Math.abs(i-0) + Math.abs(j-0);
                        break;
                    case 2:
                        mhSum += Math.abs(i-0) + Math.abs(j-1);
                        break;
                    case 3:
                        mhSum += Math.abs(i-0) + Math.abs(j-2);
                        break;
                    case 4:
                        mhSum += Math.abs(i-0) + Math.abs(j-3);
                        break;
                    case 5:
                        mhSum += Math.abs(i-1) + Math.abs(j-0);
                        break;
                    case 6:
                        mhSum += Math.abs(i-1) + Math.abs(j-1);
                        break;
                    case 7:
                        mhSum += Math.abs(i-1) + Math.abs(j-2);
                        break;
                    case 8:
                        mhSum += Math.abs(i-1) + Math.abs(j-3);
                        break;
                    case 9:
                        mhSum += Math.abs(i-2) + Math.abs(j-0);
                        break;
                    case 10:
                        mhSum += Math.abs(i-2) + Math.abs(j-1);
                        break;
                    case 11:
                        mhSum += Math.abs(i-2) + Math.abs(j-2);
                        break;
                    case 12:
                        mhSum += Math.abs(i-2) + Math.abs(j-3);
                        break;
                    case 13:
                        mhSum += Math.abs(i-3) + Math.abs(j-0);
                        break;
                    case 14:
                        mhSum += Math.abs(i-3) + Math.abs(j-1);
                        break;
                    case 15:
                        mhSum += Math.abs(i-3) + Math.abs(j-2);
                        break;
                    default:
                        break;
                        
                }
   
            }
        }
        
        return mhSum+node.depth;  // f(n) = g(n) + h(n)
    }
}
