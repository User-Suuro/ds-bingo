import java.util.Random;
import java.util.Scanner;

public class Bingo01 {
    static Random rand = new Random();
    
    // -- MAIN METHOD -- //
    public static void main(String[] args) {
       

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

        // STORE INSTANCES OF PLAYER TO ARRAY (GAWA NYU RIN METHOD PAG SORT NG isPlayerWin also pag remove then pag add)
        Player[] activePlayers = {player01, computer01, computer02, computer03}; 

        
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

            int roulette = rouletteArr[0]; // implemented FIFO
            rouletteArr = intRemoveOneElement(rouletteArr, roulette); // remove the element pag nabunot na ung number
            System.out.println("Rollete Result: " + roulette);
            Utils.cont();
            // -- MARKING SYSTEM -- //
            
            // PLAYER01
            if(isPlayerCardNumberMatched(player01.playerCard, roulette)){
                System.out.println("Player Card has the number: " + roulette);
                player01.playerMarkArr = markCard(player01.playerCard, player01.playerMarkArr, roulette);
                display2DArrBoolean(player01.playerMarkArr);
            }else{
                System.out.println("Player don't have that number");
            }

            Utils.cont();

            // COMPUTER01
            if(isPlayerCardNumberMatched(computer01.playerCard, roulette)){
                System.out.println("Computer 1 Card has the number: " + roulette);
                computer01.playerMarkArr = markCard(computer01.playerCard, computer01.playerMarkArr, roulette);
                display2DArrBoolean(computer01.playerMarkArr);
            }else{
                System.out.println("Computer 1 don't have that number"); 
            }

            Utils.cont();

            // COMPUTER02
            if(isPlayerCardNumberMatched(computer02.playerCard, roulette)){
                System.out.println("Computer 2 Card has the number:" + roulette);
                computer02.playerMarkArr = markCard(computer02.playerCard, computer02.playerMarkArr, roulette);
                display2DArrBoolean(computer02.playerMarkArr);
            }else{
                System.out.println("Computer 2 don't have that number");
            }

            Utils.cont();

            // COMPUTER03
            if(isPlayerCardNumberMatched(computer03.playerCard, roulette)){
                System.out.println("Computer 3 Card has the number: " + roulette);
                computer03.playerMarkArr = markCard(computer03.playerCard, computer03.playerMarkArr, roulette);
                display2DArrBoolean(computer03.playerMarkArr);
            }else{
                System.out.println("Computer 3 don't have that number");
            }

            Utils.cont();
        }
        
    }

    public static boolean[][] markCard(int[][] playerCard, boolean[][] playerMark, int roulleteResult){
        // find index of mark

        int index[] = findArrIntElementIndex(playerCard, roulleteResult);
        // then mark that shit base in index
        playerMark[index[0]][index[1]] = true;

        return playerMark;
    }
    
    
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
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
            if (index >= 6){
                System.out.println();
                index = 0;
            }
            index++;
        }
        System.out.println(); // Move to the next line after each row
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


