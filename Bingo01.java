import java.util.Random;
import java.util.Scanner;

public class Bingo01 {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
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

        // STORE INSTANCES OF PLAYER TO ARRAY
        Player[] activePlayers = {player01, computer01, computer02, computer03}; 

        
        
        
        // -- MAIN LOOP -- // 
        while (true){
            // create roulette 
            int roulette = rand.nextInt(75) + 1;
            
            // marking system

            // PLAYER01
            if(isPlayerCardNumberMatched(player01.playerCard, roulette)){
                System.out.println("Player Card has the number:" + roulette);
                System.out.println("Number Marked");
                player01.playerMarkArr = markCard(player01.playerCard, player01.playerMarkArr, roulette);
            }

            // COMPUTER01
            if(isPlayerCardNumberMatched(computer01.playerCard, roulette)){
                System.out.println("Computer 1 Card has the number:" + roulette);
                System.out.println("Number Marked");
                computer01.playerMarkArr = markCard(computer01.playerCard, computer01.playerMarkArr, roulette);
            }

            // COMPUTER02
            if(isPlayerCardNumberMatched(computer02.playerCard, roulette)){
                System.out.println("Computer 2 Card has the number:" + roulette);
                System.out.println("Number Marked");
                computer02.playerMarkArr = markCard(computer02.playerCard, computer02.playerMarkArr, roulette);
            }

            // COMPUTER03
            if(isPlayerCardNumberMatched(computer03.playerCard, roulette)){
                System.out.println("Computer 3 Card has the number:" + roulette);
                System.out.println("Number Marked");
                computer03.playerMarkArr = markCard(computer03.playerCard, computer03.playerMarkArr, roulette);
            }
        }
        
    }

    public static boolean[][] markCard(int[][] playerCard, boolean[][] playerMark, int roulleteResult){

        // find index of mark
        int index[] = findArrIntElementIndex(playerCard, roulleteResult);

        // then mark that shit base in index


        

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

    public static void display2DArr(char[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
               System.out.print(arr[i][j] + " ");
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
}


