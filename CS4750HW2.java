/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4750hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Joey Crowe
 */
public class CS4750HW2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //start of timer
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
        Stack<Node> fringe = new Stack<>();
        Stack<Node> solutionStack = new Stack<>();
        
        fringe.push(rootNode);
        while (!fringe.empty() && expandedNodes <= 1000000){
            tempNode = fringe.pop();
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
                        fringe.push(tempArray[i]);
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
    
}
