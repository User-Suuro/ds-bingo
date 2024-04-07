public class Bingo01 {
    // -- MAIN METHOD -- //
    public static void main(String[] args) {

        // FIXED FOR NOW, (GAWAN NYU TO METHOD UWU FOR AUTOMATION)
        int[][] playerCard = {
            {1,  2,  3,  4,  5},
            {6,  7,  8,  9,  17},
            {11, 12, -1, 14, 35},
            {16, 17, 18, 19, 47},
            {21, 22, 23, 24, 75},
        };


        // CREATE OF INSTANCE OF PLAYER
        Player player01 = new Player("Player", playerCard, false);
        Player computer01 =  new Player("Computer 1", playerCard, false);
        Player computer02 =  new Player("Computer 2", playerCard, false);
        Player computer03 =  new Player("Computer 3", playerCard, false);

        // STORE INSTANCES OF PLAYER TO ARRAY
        Player[] activePlayers = {player01, computer01, computer02, computer03}; 

        arrayIntToChar(player01.playerCard); 

        // NOTE SA 2
        // Bali mangyayari d2 need natin sya gawan ng pang sort kung sino ung mga nanalo and natalo
        // Dapat nasa unahan ung mga natalo, then nasa hulihan ung mga nanalo
        // Sa pag remove, need din natin pag mag create ng panibagong array, or reassign nlng
        // Bali kung sino ung natira sa array un ang idedeclare natin kung sino ung nanalo

        // THEN GAWA RIN KAYA METHOD PANG DISPLAY, TAKE NOTE SA PAG DISPLAY MAEEXEMPT UNG -1 , BTW MAY UTILS SA BABA PDE MO UN MAGAMIT DITO :3. EXAMPLE: Utils.clrsr()
        //  if (playerCard[i][j] != -1){
        //       System.out.print(playerCard[i][j] + " ");
        // }
        
        
    }

    public static char[][] arrayIntToChar(int[][] playerCard){
        int[] arrDimensions = getDimensions(playerCard);

        char[][] result = new char[arrDimensions[0]][arrDimensions[1]];

        

        for (int i = 0; i < playerCard.length; i++) {
            for (int j = 0; j < playerCard[i].length; j++) {
                
                int element = playerCard[i][j];

                if (element >= 1 && element <= 15){
                    result[i][j] = 'B';
                }

                else if (element >= 16 && element <= 30){
                    result[i][j] = 'I';
                }
                
                else if (element >= 31 && element <= 45){
                    result[i][j] = 'N';
                }

                else if (element >= 46 && element <= 60){
                    result[i][j] = 'G';
                }

                else if (element >= 61 && element <= 75){
                    result[i][j] = 'O';
                }

                // BONUS LOGIC: No matter what pattern -1 always represent 'N', so therefore we have to just make it 'N' by default
                else if(element == -1){
                    result[i][j] = 'N';
                }
            }
            
        }

        display2DArr(result);
    
        return result;
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
    boolean isPlayerWin;


    Player(String playerName, int[][] playerCard, boolean isPlayerWin){
        this.playerName = playerName;
        this.playerCard = playerCard;
        this.isPlayerWin = isPlayerWin;
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


