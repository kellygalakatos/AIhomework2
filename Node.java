/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4750homeworknumber2;

import java.util.Arrays;

/**
 *
 * @author crowe_000
 */
public class Node {
        public int[][] board;  //game board
    
    public int numberMoved;//used to break ties and for move sequence
    
    public Node parent = null;//need to know parent to remember the solution
    
    public int depth;//Used for DFS
    
    public int zeroRow, zeroColumn;//keep and update zero value so you do not have to search every time
    
    public Node(int[][] board){//must pass a valid 4X4 array, not checking for for invalid input
        this.parent = null;
        this.depth = 0;
        this.board = new int[4][4];
        
        for (int i=0; i <= 3; i++){//copy board and set zeroRow and Column
            for (int j = 0; j <= 3; j++){
                this.board[i][j] = board[i][j];
                if (board[i][j] == 0 ){
                    this.zeroRow = i;
                    this.zeroColumn = j;
//                    System.out.println(Arrays.deepToString(board));
//                    System.out.println(zeroRow);
//                    System.out.println(zeroColumn);
                }
            }
        }
        
    }
    
    public Node(Node rental, int direction){//1 up, 2 right, 3 down, 4 left, should use enum
        //also, can only call when valid to make a node with the given direction
        //for example, cannot call up when zero is in the first row
//        System.out.println("test");
//        System.out.print(rental);
        this.parent=rental;
        this.board = new int[4][4];
        this.depth = rental.depth + 1;
        int swapRow = rental.zeroRow;
        int swapColumn = rental.zeroColumn;
        
        for (int i=0; i <= 3; i++){//copy board
            for (int j = 0; j <= 3; j++){
                this.board[i][j] = rental.board[i][j];
            }
        }
        
        //move number and set zeroRow, zeroColumn, and number moved
        switch (direction) {
            case 1:
                //move up
                this.numberMoved = this.board[swapRow - 1][swapColumn];
                this.zeroRow = swapRow - 1;
                this.zeroColumn = swapColumn;
                this.board[swapRow][swapColumn]= this.numberMoved;
                this.board[this.zeroRow][this.zeroColumn] = 0;
                break;
            case 2:
                //right
                this.numberMoved = this.board[swapRow][swapColumn + 1];
                this.zeroRow = swapRow;
                this.zeroColumn = swapColumn + 1;
                this.board[swapRow][swapColumn]= this.numberMoved;
                this.board[this.zeroRow][this.zeroColumn] = 0;
                break;
            case 3:
                //down
                this.numberMoved = this.board[swapRow + 1][swapColumn];
                this.zeroRow = swapRow + 1;
                this.zeroColumn = swapColumn;
                this.board[swapRow][swapColumn]= this.numberMoved;
                this.board[this.zeroRow][this.zeroColumn] = 0;
                break;
            case 4:
            default:
                //left
                this.numberMoved = this.board[swapRow][swapColumn - 1];
                this.zeroRow = swapRow;
                this.zeroColumn = swapColumn - 1;
                this.board[swapRow][swapColumn]= this.numberMoved;
                this.board[this.zeroRow][this.zeroColumn] = 0;
                break;
        }
             
         
       
        
    }
    
    public Node[] expand(){
        Node[] array = new Node[4];
        int i = 0;
       // System.out.print(this);
        //check expand up valid
        if (this.zeroRow > 0){
            array[i]= new Node(this,1);
            i++;
        }
        //check expand right valid
        if (this.zeroColumn < 3){
            array[i]= new Node(this,2);
            i++;
        }
        //check expand down valid
        if (this.zeroRow < 3){
            array[i]= new Node(this,3);
            i++;
        }
        //check expand up valid
        if (this.zeroColumn > 0){
            array[i]= new Node(this,4);
            i++;
        }
        //now sort by ascending order (i.e 2 before 4)
        Node temp;
        for (int j = 0; j < i - 1; j++){//Bubble Sort!!!
            for (int k = 0; k < i - j - 1; k++){
                if (array[k].numberMoved > array[k+1].numberMoved){
                    temp = array[k];
                    array[k]=array[k+1];
                    array[k+1]=temp;
                }
            }
        }
        return array;
    }
    
    @Override
    public boolean equals(Object obj){
        Node temp = (Node)obj;
        for (int i =0; i <= 3 ; i++){
            for (int j=0; j <= 3; j++){
                if (temp.board[i][j] != this.board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Arrays.deepHashCode(this.board);
        return hash;
    }
}
