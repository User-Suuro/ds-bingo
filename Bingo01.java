import java.util.Random;
import java.util.Scanner;

public class Bingo01 {
    private static Random rand = new Random();
    private static Scanner scanner = new Scanner(System.in);
    // -- MAIN METHOD -- //
    public static void main(String[] args) {
        Utils.clrscr();
        
        System.out.println("1: Traditonal Bingo Mode ");
        System.out.println("2: Modern Bingo Mode ");

        String userChoice = scanner.nextLine();
        
        switch (userChoice) {
            case "1":
                Utils.clrscr(); 
                traditionalBingoMode();
                break;
            case "2":
                Utils.clrscr();
                modernBingoMode();   
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
       
        
    }
    public static void modernBingoMode(){
         // FIXED FOR NOW, (GAWAN NYU TO METHOD UWU FOR AUTOMATION)
         int[][] playerCard = {
            {1,  11,   3,   4,   5},
            {2,  12,   8,   9,  17},
            {3,  13,  -1,  14,  35},
            {4,  14,  18,  19,  47},
            {5,  15,  23,  24,  75},
        };

        // CONSTANT
        boolean[][] cardMarked = {
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, true , false, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
        };
     
        // CREATE OF INSTANCE OF PLAYER
        Player player01 = new Player("Player", playerCard, cardMarked,  false);
        Player computer01 =  new Player("Computer 1", playerCard, cardMarked, false);
        Player computer02 =  new Player("Computer 2", playerCard, cardMarked, false);
        Player computer03 =  new Player("Computer 3", playerCard, cardMarked, false);

        int rouletteArr[] = new int[75];  
        for (int i = 0; i < rouletteArr.length; i++) {
            rouletteArr[i] = i + 1;
        }

        System.out.println("Roullete: ");
        display1DArrInt(rouletteArr);
        Utils.cont();

        // shuffle roullete
        rouletteArr = shuffle(rouletteArr);
        System.out.println("Shuffled Roullete: ");
        display1DArrInt(rouletteArr);
        Utils.cont();

        // ALSO PRINT ALL DISTRIBUTED CARDS BEFORE GOING TO MAIN LOOP



        // -- MAIN LOOP -- // 
        while (true){
            // create roulette -> should be: once the number called it must be not to be called againq
        

            int roulette = rouletteArr[0]; // get the first element -> implemented FIFO
            rouletteArr = intRemoveOneElement(rouletteArr, roulette); // remove the first element pag nabunot na ung number
          
            // -- MARKING SYSTEM -- //
            
            // PLAYER01
            if(isPlayerCardNumberMatched(player01.playerCard, roulette)){
               
                player01.playerMarkArr = markCard(player01.playerCard, player01.playerMarkArr, roulette);
               
                if(checkIfPlayerWin(player01.playerMarkArr)){
                    player01.isPlayerWin = true;
                    System.out.println("Player: BINGO! ");
                    displayMarkedCard(player01.playerCard, player01.playerMarkArr);
                }
            }

          

            // COMPUTER01
            if(isPlayerCardNumberMatched(computer01.playerCard, roulette)){
                
                computer01.playerMarkArr = markCard(computer01.playerCard, computer01.playerMarkArr, roulette);
    
                if(checkIfPlayerWin(computer01.playerMarkArr)){
                    computer01.isPlayerWin = true;
                    System.out.println("Computer 1: BINGO! ");
                    displayMarkedCard(computer01.playerCard, computer01.playerMarkArr);
                }
               
            }

          

            // COMPUTER02
            if(isPlayerCardNumberMatched(computer02.playerCard, roulette)){
              
                computer02.playerMarkArr = markCard(computer02.playerCard, computer02.playerMarkArr, roulette);
               
                if(checkIfPlayerWin(computer02.playerMarkArr)){
                    computer02.isPlayerWin = true;
                    System.out.println("Computer 2: BINGO! ");
                    displayMarkedCard(computer02.playerCard, computer02.playerMarkArr);
                }
            }

           
            // COMPUTER03
            if(isPlayerCardNumberMatched(computer03.playerCard, roulette)){
                computer03.playerMarkArr = markCard(computer03.playerCard, computer03.playerMarkArr, roulette);
               
                if(checkIfPlayerWin(computer03.playerMarkArr)){
                    computer03.isPlayerWin = true;
                    System.out.println("Computer 3: BINGO! ");
                    displayMarkedCard(computer03.playerCard, computer03.playerMarkArr);
                }
            }

           
            
            // break the main loop if there is a winner
            if (player01.isPlayerWin || computer01.isPlayerWin || computer02.isPlayerWin || computer03.isPlayerWin){
                break;
            }

           
        }
    }

    public static void traditionalBingoMode(){
        // FIXED FOR NOW, (GAWAN NYU TO METHOD UWU FOR AUTOMATION)
        int[][] playerCard = {
            {1,  11,   3,   4,   5},
            {2,  12,   8,   9,  17},
            {3,  13,  -1,  14,  35},
            {4,  14,  18,  19,  47},
            {5,  15,  23,  24,  75},
        };

        // CONSTANT
        boolean[][] cardMarked = {
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, true , false, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
        };
     
        // CREATE OF INSTANCE OF PLAYER
        Player player01 = new Player("Player", playerCard, cardMarked,  false);
        Player computer01 =  new Player("Computer 1", playerCard, cardMarked, false);
        Player computer02 =  new Player("Computer 2", playerCard, cardMarked, false);
        Player computer03 =  new Player("Computer 3", playerCard, cardMarked, false);

        int rouletteArr[] = new int[75];  
        for (int i = 0; i < rouletteArr.length; i++) {
            rouletteArr[i] = i + 1;
        }

        System.out.println("Roullete: ");
        display1DArrInt(rouletteArr);
        Utils.cont();

        // shuffle roullete
        rouletteArr = shuffle(rouletteArr);
        System.out.println("Shuffled Roullete: ");
        display1DArrInt(rouletteArr);
        Utils.cont();

        // -- MAIN LOOP -- // 
        while (true){
            // create roulette -> should be: once the number called it must be not to be called againq
            System.out.println("Current Roullete: ");
            display1DArrInt(rouletteArr);
            Utils.cont();

            int roulette = rouletteArr[0]; // get the first element -> implemented FIFO
            rouletteArr = intRemoveOneElement(rouletteArr, roulette); // remove the first element pag nabunot na ung number
            System.out.println("Rollete Result: " + roulette);
            Utils.cont();
            // -- MARKING SYSTEM -- //
            
            // PLAYER01
            if(isPlayerCardNumberMatched(player01.playerCard, roulette)){
                System.out.println("Player Card has the number: " + roulette);
                player01.playerMarkArr = markCard(player01.playerCard, player01.playerMarkArr, roulette);
               
                if(checkIfPlayerWin(player01.playerMarkArr)){
                    player01.isPlayerWin = true;
                    System.out.println("Player: BINGO! ");
                }
            }else{
                System.out.println("Player don't have that number");
            }

            displayMarkedCard(player01.playerCard, player01.playerMarkArr);
            Utils.cont();

            // COMPUTER01
            if(isPlayerCardNumberMatched(computer01.playerCard, roulette)){
                System.out.println("Computer 1 Card has the number: " + roulette);
                computer01.playerMarkArr = markCard(computer01.playerCard, computer01.playerMarkArr, roulette);
    
                if(checkIfPlayerWin(computer01.playerMarkArr)){
                    computer01.isPlayerWin = true;
                    System.out.println("Computer 1: BINGO! ");
                }
               
            }else{
                System.out.println("Computer 1 don't have that number"); 
            }

            displayMarkedCard(computer01.playerCard, computer01.playerMarkArr);
            Utils.cont();

            // COMPUTER02
            if(isPlayerCardNumberMatched(computer02.playerCard, roulette)){
                System.out.println("Computer 2 Card has the number: " + roulette);
                computer02.playerMarkArr = markCard(computer02.playerCard, computer02.playerMarkArr, roulette);
               
                if(checkIfPlayerWin(computer02.playerMarkArr)){
                    computer02.isPlayerWin = true;
                    System.out.println("Computer 2: BINGO! ");
                }
            }else{
                System.out.println("Computer 2 don't have that number");
            }

            displayMarkedCard(computer02.playerCard, computer02.playerMarkArr);
            Utils.cont();

            // COMPUTER03
            if(isPlayerCardNumberMatched(computer03.playerCard, roulette)){
                System.out.println("Computer 3 Card has the number: " + roulette);
                computer03.playerMarkArr = markCard(computer03.playerCard, computer03.playerMarkArr, roulette);
               
                if(checkIfPlayerWin(computer03.playerMarkArr)){
                    computer03.isPlayerWin = true;
                    System.out.println("Computer 3: BINGO! ");
                }
            }else{
                System.out.println("Computer 3 don't have that number");
            }

            displayMarkedCard(computer03.playerCard, computer03.playerMarkArr);
            
            // break the main loop if there is a winner
            if (player01.isPlayerWin || computer01.isPlayerWin || computer02.isPlayerWin || computer03.isPlayerWin){
                break;
            }

            Utils.cont();
        }
        
        // FINALIZE -> GET ALL PLAYER WIN THEN CALL THEM
    }

    // method to check if the player won
    public static boolean checkIfPlayerWin(boolean[][] playerMark){

        // MAP ALL POSSIBLE PATTERNS
        boolean[] horiztontalPattern01 = {playerMark[0][0], playerMark[0][1], playerMark[0][2], playerMark[0][3], playerMark[0][4]};
        boolean[] horiztontalPattern02 = {playerMark[1][0], playerMark[1][1], playerMark[1][2], playerMark[1][3], playerMark[1][4]};
        boolean[] horiztontalPattern03 = {playerMark[2][0], playerMark[2][1], playerMark[2][2], playerMark[2][3], playerMark[2][4]};
        boolean[] horiztontalPattern04 = {playerMark[3][0], playerMark[3][1], playerMark[3][2], playerMark[3][3], playerMark[3][4]};
        boolean[] horiztontalPattern05 = {playerMark[4][0], playerMark[4][1], playerMark[4][2], playerMark[4][3], playerMark[4][4]};
        
        boolean[] verticalPattern01 = {playerMark[0][0], playerMark[1][0], playerMark[2][0], playerMark[3][0], playerMark[4][0]};
        boolean[] verticalPattern02 = {playerMark[0][1], playerMark[1][1], playerMark[2][1], playerMark[3][1], playerMark[4][1]};
        boolean[] verticalPattern03 = {playerMark[0][2], playerMark[1][2], playerMark[2][2], playerMark[3][2], playerMark[4][2]};
        boolean[] verticalPattern04 = {playerMark[0][3], playerMark[1][3], playerMark[2][3], playerMark[3][3], playerMark[4][3]};
        boolean[] verticalPattern05 = {playerMark[0][4], playerMark[1][4], playerMark[2][4], playerMark[3][4], playerMark[4][4]};

        boolean[] diagonalPattern01 = {playerMark[0][0], playerMark[1][1], playerMark[2][2], playerMark[3][3], playerMark[4][4]};
        boolean[] diagonalPattern02 = {playerMark[0][4], playerMark[1][3], playerMark[2][2], playerMark[3][1], playerMark[4][0]};

        // RETURN TRUE IF THE PATTERN IS PRESENT TO PLAYER MARK
        
        // check if array are all true
        if (checkArrIfAllTrue(horiztontalPattern01)){
            return true;
        }

        if (checkArrIfAllTrue(horiztontalPattern02)){
            return true;
        }

        if (checkArrIfAllTrue(horiztontalPattern03)){
            return true;
        }

        if (checkArrIfAllTrue(horiztontalPattern04)){
            return true;
        }

        if (checkArrIfAllTrue(horiztontalPattern05)){
            return true;
        }

        if (checkArrIfAllTrue(verticalPattern01)){
            return true;
        }

        if (checkArrIfAllTrue(verticalPattern02)){
            return true;
        }

        if (checkArrIfAllTrue(verticalPattern03)){
            return true;
        }

        if (checkArrIfAllTrue(verticalPattern04)){
            return true;
        }

        if (checkArrIfAllTrue(verticalPattern05)){
            return true;
        }

        if (checkArrIfAllTrue(diagonalPattern01)){
            return true;
        }

        if (checkArrIfAllTrue(diagonalPattern02)){
            return true;
        }
        
        return false;
    }

    // method to check if all 1D array contains all true
    public static boolean checkArrIfAllTrue(boolean[] arr){
        for (boolean value : arr) {
            if (!value) {
                return false;
            }
        }
        return true;
    }

    // method to mark the card base in roullete result 
    public static boolean[][] markCard(int[][] playerCard, boolean[][] playerMark, int roulleteResult){
        // find index of mark

        int index[] = findArrIntElementIndex(playerCard, roulleteResult);
        // then mark that shit base in index
        playerMark[index[0]][index[1]] = true;

        return playerMark;
    }
    
    // method for finding specific element in 2D array of integers, returns index
    public static int[] findArrIntElementIndex(int[][] playerCard, int target){

        int[] index = new int[2]; 

        for (int i = 0; i < playerCard.length; i++) {
            for (int j = 0; j < playerCard[i].length; j++) {
                if (playerCard[i][j] == target) {
                    index[0] = i; // Row index
                    index[1] = j; // Column index
                    return index;
                }
            }
        }

        // If the number is not found, return {-1, -1}
        return new int[]{-1, -1};
    }

    // method to check if the player has that number in roullete
    public static boolean isPlayerCardNumberMatched(int[][] playerCard, int roulleteResult){
        // scan all playerCardNumbers then compare it to roullete result, if it matched, then return true
        for (int i = 0; i < playerCard.length; i++) {
            for (int j = 0; j < playerCard[i].length; j++) {
               if (playerCard[i][j] == roulleteResult){
                    return true;
               }
            }
        }

        return false;
    }

    // method to get the dimensions of 2D array, returns an array
    public static int[] getDimensions(int[][] arr){
        int[] dimensions = new int[2];

        int x = 0;
        int y = 0;

        // counts first row (assume that it has row)
        for (int i = 0; i < arr[0].length; i++){
            x = x + 1;
        }

        for (int i = 0; i < arr.length; i++) {
           y = y + 1;
        }

        dimensions[0] = x;
        dimensions[1] = y;

        return dimensions;
    }

    //method for shuffling 
    public static int[] shuffle(int[] arr)
    {
        //fisher-yates shuffle
        int index;
        int temp;
        
        for (int i = arr.length - 1; i > 0; i--)
        {
            index = rand.nextInt(i + 1);
            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    //method for removing element in array of string (1D array)
    public static int[] intRemoveOneElement(int[] arr, int removeTarget)
    {
        //find the index
        int targetIndex = findIntIndex(arr, removeTarget);

        int tempArr[] = new int[arr.length];

        //create another array without the target value
        for (int i = 0; i < tempArr.length; i++){
            if(arr[i] != arr[targetIndex]){
                tempArr[i] = arr[i];
            }else if(arr[i] == arr[targetIndex]){
                tempArr[i] = -1;
            }
        }

        //remove null value
        return removeNegativeOne(tempArr);
    }

    public static int findIntIndex(int[] arr, int target){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; 
            }
        }
        return -1; 
    }

    //method for removing null in array of string
    public static int[] removeNegativeOne(int[] arr){
        // Count non-null values
        int countNonNull = 0;
        for (int element : arr) {
            if (element != -1) {
                countNonNull++;
            }
        }

        // Create a new array without null values
        int[] newArr = new int[countNonNull];
        int newIndex = 0;

        for (int element : arr) {
            if (element != -1) {
                newArr[newIndex] = element;
                newIndex++;
            }
        }

        return newArr;
    }

    // -- DISPLAY UTILS -- //

    public static void display2DArrChar(char[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
               System.out.print(arr[i][j] + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
    } 

    public static void display2DArrBoolean(boolean[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
               System.out.print(arr[i][j] + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
    } 

    public static void display1DArrInt(int[] arr){
        int elementsPerRow = 7; // Define the maximum number of elements per row
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t"); 
            if ((i + 1) % elementsPerRow == 0 && i != arr.length - 1) {
                System.out.println(); // Move to the next line
            }
        }
        System.out.println(); 
    } 

    public static void displayMarkedCard(int[][] playerCard, boolean[][] playerMark){
        // Display the marked card
        for (int i = 0; i < playerCard.length; i++) {
            for (int j = 0; j < playerCard[i].length; j++) {
                if (playerMark[i][j]) {
                    System.out.print("X\t");
                } else {
                    System.out.print(playerCard[i][j] + "\t"); // Print the previous value for unmarked cells
                }
            }
            System.out.println(); // Move to the next line after each row
        }
    }
}


class Player{
    String playerName;
    int[][] playerCard;
    boolean[][] playerMarkArr;
    boolean isPlayerWin;


    Player(String playerName, int[][] playerCard, boolean playerMark[][], boolean isPlayerWin){
        this.playerName = playerName;
        this.playerCard = playerCard;
        this.isPlayerWin = isPlayerWin;
        this.playerMarkArr = playerMark;
    }

    // GETTER 
    public int[][] getPlayerCard(){
        return playerCard;
    }

    // SETTER
    public void setPlayerCard(int[][] newPlayerCard){
        this.playerCard = newPlayerCard;
    }
}

class Utils{
    // Clear Screen method
    public static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec(new String[]{"clear"});
        } catch (Exception ex) {
            System.out.println("Failed to clear the screen.");
        }
    }
    
    // Method for pausing in command prompt
    public static void sleep(long ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e){};
    }

    public static void cont(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Press Enter to Continue");
        scan.nextLine();
        Utils.clrscr();
    }
}


