public class Bingo01 {
    // -- MAIN METHOD -- //
    public static void main(String[] args) {

        // FIXED FOR NOW, (GAWAN NYU TO METHOD UWU FOR AUTOMATION)
        int[][] playerCard = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25},
        };


        // CREATE OF INSTANCE OF PLAYER
        Player player01 = new Player("Player", playerCard, false);
        Player computer01 =  new Player("Computer 1", playerCard, false);
        Player computer02 =  new Player("Computer 1", playerCard, false);
        Player computer03 =  new Player("Computer 1", playerCard, false);

        // STORE INSTANCES OF PLAYER TO ARRAY
        Player[] activePlayers = {player01, computer01, computer02, computer03}; 

        // NOTE SA 2
        // Bali mangyayari d2 need natin sya gawan ng pang sort kung sino ung mga nanalo and natalo
        // Dapat nasa unahan ung mga natalo, then nasa hulihan ung mga nanalo
        // Sa pag remove, need din natin pag mag create ng panibagong array, or reassign nlng
        // Bali kung sino ung natira sa array un ang idedeclare natin kung sino ung nanalo

        // THEN GAWA RIN KAYA METHOD PANG DISPLAY, BTW MAY UTILS SA BABA PDE MO UN MAGAMIT DITO :3. EXAMPLE: Utils.clrsr()

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


