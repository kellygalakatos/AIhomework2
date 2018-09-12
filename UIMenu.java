import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Daniel Jakle
 */
 
public class MenuChooser {
    
    Scanner scan = new Scanner(System.in);    
    private int searchChoice = 1;
    private int[] puzzleArray;
    
    public MenuChooser(){
        this.puzzleArray = new int[16];
    }
    
    //UI material
    public void printMenu(){
        System.out.print("Please select the type of search to perform:\n\n[1] Iterative Deepening Tree Search\n[2] Depth First Graph Search\n[3] A* Tree Search\n\nPlease enter your choice: ");
    }
    
    //user chooses which search they want to use
    public void setSearchChoice(){
        printMenu();
        this.searchChoice = scan.nextInt();
        while(searchChoice != 1 && searchChoice != 2 && searchChoice != 3){
            System.out.println("\nError: Must enter a valid choice!");
            printMenu();
            this.searchChoice = scan.nextInt();
        }
    }
    
    //to send to your main class
    public int getSearchChoice(){
        return this.searchChoice;
    }
    
    //UI material
    public void puzzleEntryMenu(){
        System.out.print("Please enter numbers 0-15 in any order (0 will be the 'blank' space). Do not repeat numbers, do not use punctuation. Press ENTER when done.\n");
    }
    
    //builds the array using user input
    public void setPuzzleArray(){
        int i, j;
        boolean duplicates = false;
        boolean outOfBounds = false;
        
        inputArray();
        
        //error check for repeats
        for(i = 0; i < puzzleArray.length; i++){
            for(j = i+1; j < puzzleArray.length; j++){
                if(puzzleArray[j] == puzzleArray[i]){
                    duplicates = true;
                }
            }
        }

        //error check for numbers that aren't 0-15
        for(i = 0; i < puzzleArray.length; i++){
            if(puzzleArray[i] < 0 || puzzleArray[i] > 15){
                outOfBounds = true;
            }
        }

        //prompt again while array is not 0-15
        while(outOfBounds == true){
            System.out.println("\nError: must enter only numbers 0-15.");
            inputArray();
            outOfBounds = false;
            for(i = 0; i < puzzleArray.length; i++){
                if(puzzleArray[i] < 0 || puzzleArray[i] > 15){
                    outOfBounds = true;
                }
            }
        }

        //prompt again while there are duplicates still being entered
        while(duplicates == true){
            System.out.println("\nError: numbers must not repeat");
            inputArray();
            duplicates = false;
            for(i = 0; i < puzzleArray.length; i++){
                for(j = i+1; j < puzzleArray.length; j++){
                    if(puzzleArray[j] == puzzleArray[i]){
                        duplicates = true;
                    }
                }
            }
        }
    }
     
    //just to avoid duplicating code 
    public void inputArray(){
        int i;
        
        puzzleEntryMenu();
            
        for(i = 0; i < puzzleArray.length; i++){
            puzzleArray[i] = scan.nextInt();
        }
    }
    
    //to send to your main class
    public int[] getPuzzleArray(){
        return this.puzzleArray;
    }
}
