//Hopefully this is somewhere close to the mark
//I can't test it on my system for some stupid reason so if you think I'm way off then let me know and I'll try to revise it

package cs4750hw2;

import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Daniel Jakle
 *
 * Parts taken from author Manish Bhojasia, Sanfoundry Technology Education Blog
 * https://www.sanfoundry.com/java-program-implement-iterative-deepening/
 */
public class IterativeDeepeningSearch {
    
    final long startTime = System.currentTimeMillis();
    int maxDepth = 0;

    int[][] startingBoard = {
        {0,0,0,0},
        {0,0,0,0},
        {0,0,0,0},
        {0,0,0,0}
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
    Stack<Node> fringe = new Stack<>();
    Stack<Node> solutionStack = new Stack<>();
    
    fringe.push(rootNode);
    
    //assuming I implemented the Node class correctly and it increments the depth with each iteration, this should limit the
    //depth per turn until a solution is reached
    while(!fringe.empty() && expandedNodes <= 1000000){
        tempNode = fringe.pop();
        
        if(tempNode.depth <= maxDepth){
            if (tempNode.equals(solutionNode)){
                //case for solution
                while (tempNode != null) {
                    solutionStack.add(tempNode);
                    tempNode = tempNode.parent;
                } 
            }
            for (int i = tempArray.length - 1; i >= 0; i--){
                if (tempArray[i] != null){
                    fringe.push(tempArray[i]);
                }
            }
        }
        //increment the depth limiter only after all nodes at the current depth have been searched (if all goes according to plan...)
        maxDepth++;
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
