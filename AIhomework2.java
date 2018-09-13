/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aihomework2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author kellygalakatos
 */
public class AIhomework2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        final long startTime = System.currentTimeMillis();
        
        int[][] startingBoard = {
            {1,2,7,3},
            {5,6,11,4},
            {9,10,15,8},
            {13,14,12,0}
        };
        int[][] solutionBoard = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
        };
        
        int expandedNodes = 0;
        int solutionLength = 0;
        
        Node solutionNode = new Node(solutionBoard);
        Node rootNode = new Node(startingBoard);
        Node tempNode;
        
        Node[] tempArray;
        HashSet<Node> closed = new HashSet<>();
 
        Stack<Node> solutionStack = new Stack<>();
        ArrayList<Node> aStarOpened = new ArrayList<Node>();  
        
      
        aStarOpened.add(rootNode); 
        while (!aStarOpened.isEmpty() && expandedNodes <= 1000000 && solutionStack.isEmpty()){  
           
            tempNode = getMinHeuristic(aStarOpened);
            aStarOpened.remove(getMinHeuristic(aStarOpened));
            
            if (tempNode.equals(solutionNode)){
                //case for solution
                while (tempNode != null) {
                    solutionStack.add(tempNode);
                    tempNode = tempNode.parent;
                } 
            }
            if (!closed.contains(tempNode)){
                closed.add(tempNode);
                tempArray = tempNode.expand();
                expandedNodes++;
                if (expandedNodes % 100 == 0){
                    System.out.println(expandedNodes);
                }
                if (expandedNodes <= 5){
                    System.out.println(Arrays.deepToString(tempNode.board));
                }
                for (int i = tempArray.length - 1; i >= 0; i--){
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
                System.out.println(Arrays.deepToString(tempNode.board));
                solutionLength++;
            }
            System.out.println("The total number of moves is: " + solutionLength);
        }
        System.out.println("Number of nodes expanded: " + expandedNodes);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) );
        
    }
    
    
    public static Node getMinHeuristic(ArrayList<Node> aStarOpened) {
        int min = manhattanHeuristic(aStarOpened.get(0));
        Node tempNode = aStarOpened.get(0);
        
        for (int i = 0; i < aStarOpened.size(); i++) {
            if(manhattanHeuristic(aStarOpened.get(i)) < min) {
                min = manhattanHeuristic(aStarOpened.get(i));
                tempNode = aStarOpened.get(i);
            }
        }    
        
        return tempNode;
    }
        
    public static int manhattanHeuristic(Node node) {
        int mhSum = 0;
        
        for (int i=0; i <= 3; i++){
            for (int j = 0; j <= 3; j++){
              
                switch (node.board[i][j]) {
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
        
        return mhSum+node.depth;
    }
    
}
