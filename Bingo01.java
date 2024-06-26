import java.util.Random;
import java.util.Scanner;

public class Bingo01 {
    private static Random rand = new Random();
    private static Scanner scanner = new Scanner(System.in);

    // -- MAIN METHOD -- //
    public static void main(String[] args) {
        Utils.clrscr();

        System.out.println();
        System.out.println("░█░█░█▀▀░█░░░█▀▀░█▀█░█▄█░█▀▀░░░▀█▀░█▀█░░░█▀▄░▀█▀░█▀█░█▀▀░█▀█░░░█▀▀░█▀█░█▄█░█▀▀░█" + "\n" +//
                           "░█▄█░█▀▀░█░░░█░░░█░█░█░█░█▀▀░░░░█░░█░█░░░█▀▄░░█░░█░█░█░█░█░█░░░█░█░█▀█░█░█░█▀▀░▀" + "\n" +//
                           "░▀░▀░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░░░░▀░░▀▀▀░░░▀▀░░▀▀▀░▀░▀░▀▀▀░▀▀▀░░░▀▀▀░▀░▀░▀░▀░▀▀▀░▀" + "\n" //                    
        );
        
        System.out.println("+---------------------------------+");
        System.out.println("| Enter 1 for Traditional Bingo   |");
        System.out.println("| Enter 2 for Modern Bingo        |");
        System.out.println("+---------------------------------+");
        System.out.print("   Please select a game mode: ");
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

    // -- MODERN BINGO MODE VIEW -- //

    public static void modernBingoMode(){

        int[][] playerCard1 = generatePlayerCard();
        int[][] playerCard2 = generatePlayerCard();
        int[][] playerCard3 = generatePlayerCard();
        int[][] playerCard4 = generatePlayerCard();
        int[][] playerCard5 = generatePlayerCard();

        // CREATE OF INSTANCE OF PLAYER
        Player player01 = new Player("Player", playerCard1,  false);
        Player computer01 =  new Player("Computer 1", playerCard2, false);
        Player computer02 =  new Player("Computer 2", playerCard3,  false);
        Player computer03 =  new Player("Computer 3", playerCard4,  false);
        Player computer04 =  new Player("Computer 4", playerCard5, false);
        

        //----------------------------DISPLAY CARDS----------------------------//
        displayWithBorder(player01.playerName);
        displayPlayerCard(player01.playerCard);
        Utils.cont();

        displayWithBorder(computer01.playerName);
        displayPlayerCard(computer01.playerCard);
        Utils.cont();

        displayWithBorder(computer02.playerName);
        displayPlayerCard(computer02.playerCard);
        Utils.cont();

        displayWithBorder(computer03.playerName);
        displayPlayerCard(computer03.playerCard);
        Utils.cont();

        displayWithBorder(computer04.playerName);
        displayPlayerCard(computer04.playerCard);
        Utils.cont();

        int rouletteArr[] = new int[75];

        while (true){
            for (int i = 0; i < rouletteArr.length; i++) {
                rouletteArr[i] = i + 1;
            }
    
            displayWithBorder("Roullete: ");
            display1DArrInt(rouletteArr);
            Utils.cont();
    
            // shuffle roullete
            rouletteArr = shuffle(rouletteArr);
            displayWithBorder("Shuffeled Roullete: ");
            display1DArrInt(rouletteArr);
            Utils.cont();
            
            if (findDuplicate(rouletteArr)){
                displayWithBorder("Duplicates found!. Retrying Card Generation");
                Utils.cont();
            }else{
                displayWithBorder("No duplicates found");
                Utils.cont();
                break;
            }
        }
        

        // get winning pattern, using random
        displayWithBorder("Winning Pattern");
        int randomPatternPicker = rand.nextInt(14);
        displayTargetPattern(randomPatternPicker);
        Utils.cont();

        // ALSO PRINT ALL DISTRIBUTED CARDS BEFORE GOING TO MAIN LOOP
        System.out.println("╔═════════════════════════════════════════════════════════════════════╗");
        System.out.println("║    MAIN GAME WILL NOW BEGIN (MODERN MODE WILL SKIP THE PROCESS)     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════╝");

        Utils.cont();


        // -- MAIN LOOP -- //
        while (true){
            // create roulette -> should be: once the number called it must be not to be called againq

            int roulette = rouletteArr[0]; // get the first element -> implemented FIFO
            rouletteArr = intRemoveOneElement(rouletteArr, roulette); // remove the first element pag nabunot na ung number

            // -- MARKING SYSTEM -- //

            // PLAYER01
            player01.playerMarkArr = markCard(player01.playerCard, player01.playerMarkArr, roulette);


            if(checkIfPlayerWin(player01.playerMarkArr, randomPatternPicker)){
                displayGameResultInterface();
                player01.isPlayerWin = true;
                displayWithBorder(player01.playerName + " BINGO!");
                displayMarkedCard(player01.playerCard, player01.playerMarkArr);
                Utils.cont();
            }

            // COMPUTER01
            computer01.playerMarkArr = markCard(computer01.playerCard, computer01.playerMarkArr, roulette);


            if(checkIfPlayerWin(computer01.playerMarkArr, randomPatternPicker)){
                displayGameResultInterface();
                computer01.isPlayerWin = true;
                displayWithBorder(computer01.playerName + " BINGO!");
                displayMarkedCard(computer01.playerCard, computer01.playerMarkArr);
                Utils.cont();
            }


            // COMPUTER02
            computer02.playerMarkArr = markCard(computer02.playerCard, computer02.playerMarkArr, roulette);

            if(checkIfPlayerWin(computer02.playerMarkArr, randomPatternPicker)){
                displayGameResultInterface();
                computer02.isPlayerWin = true;
                displayWithBorder(computer02.playerName + " BINGO!");
                displayMarkedCard(computer02.playerCard, computer02.playerMarkArr);
                Utils.cont();
            }

            // COMPUTER03
            computer03.playerMarkArr = markCard(computer03.playerCard, computer03.playerMarkArr, roulette);

            if(checkIfPlayerWin(computer03.playerMarkArr, randomPatternPicker)){
                displayGameResultInterface();
                computer03.isPlayerWin = true;
                displayWithBorder(computer03.playerName + " BINGO!");
                displayMarkedCard(computer03.playerCard, computer03.playerMarkArr);
                Utils.cont();
            }

            // COMPUTER04
            computer04.playerMarkArr = markCard(computer04.playerCard, computer04.playerMarkArr, roulette);

            if(checkIfPlayerWin(computer04.playerMarkArr, randomPatternPicker)){
                displayGameResultInterface();
                computer04.isPlayerWin = true;
                displayWithBorder(computer04.playerName + " BINGO!");
                displayMarkedCard(computer04.playerCard, computer04.playerMarkArr);
                Utils.cont();
            }


            // break the main loop if there is a winner
            if (player01.isPlayerWin || computer01.isPlayerWin || computer02.isPlayerWin || computer03.isPlayerWin || computer04.isPlayerWin){
    
                if(!player01.isPlayerWin){
                    displayGameResultInterface();
                    displayLostInterface(); 
                    displayWithBorder(player01.playerName);
                    displayMarkedCard(player01.playerCard, player01.playerMarkArr);
                    Utils.cont();
                }
                
                if(!computer01.isPlayerWin){
                    displayGameResultInterface();
                    displayLostInterface(); 
                    displayWithBorder(computer01.playerName);
                    displayMarkedCard(computer01.playerCard, computer01.playerMarkArr);
                    Utils.cont();
                }
          
                if(!computer02.isPlayerWin){
                    displayGameResultInterface();
                    displayLostInterface(); 
                    displayWithBorder(computer02.playerName);
                    displayMarkedCard(computer02.playerCard, computer02.playerMarkArr);
                    Utils.cont();
                }
          
                if(!computer03.isPlayerWin){
                    displayGameResultInterface();
                    displayLostInterface(); 
                    displayWithBorder(computer03.playerName);
                    displayMarkedCard(computer03.playerCard, computer03.playerMarkArr);
                    Utils.cont();
                }
              
                if(!computer04.isPlayerWin){
                    displayGameResultInterface();
                    displayLostInterface(); 
                    displayWithBorder(computer04.playerName);
                    displayMarkedCard(computer04.playerCard, computer04.playerMarkArr);
                    Utils.cont();
                }
                break;
            }
        }
        String yellowColor = "\u001B[33m"; String resetColor = "\u001B[0m";
        System.out.println("╔════════════════════════╗");
        System.out.println("║   "+ yellowColor +"END OF BINGO GAME"+ resetColor +"    ║");
        System.out.println("╚════════════════════════╝");



    }

    // -- TRADITIONAL BINGO MODE VIEW -- //

    public static void traditionalBingoMode(){
        int[][] playerCard1 = generatePlayerCard();
        int[][] playerCard2 = generatePlayerCard();
        int[][] playerCard3 = generatePlayerCard();
        int[][] playerCard4 = generatePlayerCard();
        int[][] playerCard5 = generatePlayerCard();


        // CREATE OF INSTANCE OF PLAYER
        Player player01 = new Player("Player", playerCard1,  false);
        Player computer01 =  new Player("Computer 1", playerCard2, false);
        Player computer02 =  new Player("Computer 2", playerCard3, false);
        Player computer03 =  new Player("Computer 3", playerCard4,  false);
        Player computer04 =  new Player("Computer 4", playerCard5, false);

        //----------------------------DISPLAY CARDS----------------------------//
        displayWithBorder(player01.playerName);
        displayPlayerCard(player01.playerCard);
        Utils.cont();

        displayWithBorder(computer01.playerName);
        displayPlayerCard(computer01.playerCard);
        Utils.cont();

        displayWithBorder(computer02.playerName);
        displayPlayerCard(computer02.playerCard);
        Utils.cont();

        displayWithBorder(computer03.playerName);
        displayPlayerCard(computer03.playerCard);
        Utils.cont();

        displayWithBorder(computer04.playerName);
        displayPlayerCard(computer04.playerCard);
        Utils.cont();

        // -- ROULLETE -- //

        int rouletteArr[] = new int[75];

        while (true){
            for (int i = 0; i < rouletteArr.length; i++) {
                rouletteArr[i] = i + 1;
            }
    
           
            displayWithBorder("Roullete: ");
            display1DArrInt(rouletteArr);
            Utils.cont();
    
            // shuffle roullete
            rouletteArr = shuffle(rouletteArr);
            displayWithBorder("Shuffled Roullete: ");
            display1DArrInt(rouletteArr);
            Utils.cont();
    
            findDuplicate(rouletteArr);
    
            displayWithBorder("Checking for duplicates...");
           if (findDuplicate(rouletteArr)){
                displayWithBorder("Duplicates Found! Retrying card generation");
                Utils.cont();
           }else{
                displayWithBorder("No Duplicates Found");
                Utils.cont();
                break;
           }
        }
        
        int randomPatternPicker = rand.nextInt(14);
        displayWithBorder("Winning Pattern: ");
        displayTargetPattern(randomPatternPicker);
        Utils.cont();

        System.out.println("╔════════════════════════════════╗");
        System.out.println("║    MAIN GAME WILL NOW BEGIN    ║");
        System.out.println("╚════════════════════════════════╝");
        Utils.cont();

        // -- MAIN LOOP -- //
        while (true){
            // create roulette -> should be: once the number called it must be not to be called againq
            displayWithBorder("Current Roulloutte");
            display1DArrInt(rouletteArr);
            Utils.cont();

            int roulette = rouletteArr[0]; // get the first element -> implemented FIFO
            rouletteArr = intRemoveOneElement(rouletteArr, roulette); // remove the first element pag nabunot na ung number
            displayWithBorder("Roullete Result: " + roulette);
            Utils.cont();

            // -- MARKING SYSTEM -- //

            // PLAYER01
            player01.playerMarkArr = markCard(player01.playerCard, player01.playerMarkArr, roulette);

            if(isPlayerCardNumberMatched(player01.playerCard, roulette)){
                displayHasNumber(player01.playerName, roulette);    
            }else{
                displayHasNotNumber(player01.playerName, roulette);
            }

            if(checkIfPlayerWin(player01.playerMarkArr, randomPatternPicker)){
                player01.isPlayerWin = true;
                displayWithBorder(player01.playerName + " BINGO!");
                displayMarkedCard(player01.playerCard, player01.playerMarkArr);
            }

            if (!player01.isPlayerWin){
                displayMarkedCard(player01.playerCard, player01.playerMarkArr);
            }

            Utils.cont();

            // COMPUTER01
            computer01.playerMarkArr = markCard(computer01.playerCard, computer01.playerMarkArr, roulette);

            if(isPlayerCardNumberMatched(computer01.playerCard, roulette)){
                displayHasNumber(computer01.playerName, roulette); 
            }else{
                displayHasNotNumber(computer01.playerName, roulette); 
            }

            if(checkIfPlayerWin(computer01.playerMarkArr, randomPatternPicker)){
                computer01.isPlayerWin = true;
                displayWithBorder(computer01.playerName + " BINGO!");
                displayMarkedCard(computer01.playerCard, computer01.playerMarkArr);
            }

            if (!computer01.isPlayerWin){
                displayMarkedCard(computer01.playerCard, computer01.playerMarkArr);
            }

            Utils.cont();

            // COMPUTER02
            computer02.playerMarkArr = markCard(computer02.playerCard, computer02.playerMarkArr, roulette);

            if(isPlayerCardNumberMatched(computer02.playerCard, roulette)){
                displayHasNumber(computer02.playerName, roulette); 
            }else{
                displayHasNotNumber(computer02.playerName, roulette); 
            }

            if(checkIfPlayerWin(computer02.playerMarkArr, randomPatternPicker)){
                computer02.isPlayerWin = true;
                displayWithBorder(computer02.playerName + " BINGO!");
                displayMarkedCard(computer02.playerCard, computer02.playerMarkArr);
            }

            if (!computer02.isPlayerWin){
                displayMarkedCard(computer02.playerCard, computer02.playerMarkArr);
            }

            Utils.cont();

            // COMPUTER03
            computer03.playerMarkArr = markCard(computer03.playerCard, computer03.playerMarkArr, roulette);

            if(isPlayerCardNumberMatched(computer03.playerCard, roulette)){
                displayHasNumber(computer03.playerName, roulette); 

            }else{
                displayHasNotNumber(computer03.playerName, roulette); 
            }

            if(checkIfPlayerWin(computer03.playerMarkArr, randomPatternPicker)){
                computer03.isPlayerWin = true;
                displayWithBorder(computer03.playerName + " BINGO!");
                displayMarkedCard(computer03.playerCard, computer03.playerMarkArr);
            }

            if (!computer03.isPlayerWin){
                displayMarkedCard(computer03.playerCard, computer03.playerMarkArr);
            }

            Utils.cont();


            // COMPUTER04
            computer04.playerMarkArr = markCard(computer04.playerCard, computer04.playerMarkArr, roulette);
            if(isPlayerCardNumberMatched(computer04.playerCard, roulette)){
                displayHasNumber(computer04.playerName, roulette);

            }else{
                displayHasNotNumber(computer04.playerName, roulette); 
            }

            if(checkIfPlayerWin(computer04.playerMarkArr, randomPatternPicker)){
                computer04.isPlayerWin = true;
                displayWithBorder(computer04.playerName + " BINGO!");
                displayMarkedCard(computer04.playerCard, computer04.playerMarkArr);
            }

            if (!computer04.isPlayerWin){
                displayMarkedCard(computer04.playerCard, computer04.playerMarkArr);
            }
            
            Utils.cont();

            // break the main loop if there is a winner
            if (player01.isPlayerWin || computer01.isPlayerWin || computer02.isPlayerWin || computer03.isPlayerWin || computer04.isPlayerWin){
                
                if(!player01.isPlayerWin){
                    displayGameResultInterface();
                    displayLostInterface(); 
                    displayWithBorder(player01.playerName);
                    displayMarkedCard(player01.playerCard, player01.playerMarkArr);
                    Utils.cont();
                }
                
                if(!computer01.isPlayerWin){
                    displayGameResultInterface();
                    displayLostInterface(); 
                    displayWithBorder(computer01.playerName);
                    displayMarkedCard(computer01.playerCard, computer01.playerMarkArr);
                    Utils.cont();
                }
          
                if(!computer02.isPlayerWin){
                    displayGameResultInterface();
                    displayLostInterface(); 
                    displayWithBorder(computer02.playerName);
                    displayMarkedCard(computer02.playerCard, computer02.playerMarkArr);
                    Utils.cont();
                }
          
                if(!computer03.isPlayerWin){
                    displayGameResultInterface();
                    displayLostInterface(); 
                    displayWithBorder(computer03.playerName);
                    displayMarkedCard(computer03.playerCard, computer03.playerMarkArr);
                    Utils.cont();
                }
              
                if(!computer04.isPlayerWin){
                    displayGameResultInterface();
                    displayLostInterface(); 
                    displayWithBorder(computer04.playerName);
                    displayMarkedCard(computer04.playerCard, computer04.playerMarkArr);
                    Utils.cont();
                }
                break;
            }
        }

        String yellowColor = "\u001B[33m"; String resetColor = "\u001B[0m";
        System.out.println("╔════════════════════════╗");
        System.out.println("║   "+ yellowColor +"END OF BINGO GAME"+ resetColor +"    ║");
        System.out.println("╚════════════════════════╝");
    }

    // -- UTILS -- //

    // -- CHECK IF PLAYER WON -- //

    public static boolean checkIfPlayerWin(boolean[][] playerMark, int patternPicked){

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

        boolean[] crossPattern = {
                playerMark[0][0], playerMark[1][1], playerMark[2][2], playerMark[3][3], playerMark[4][4],
                playerMark[0][4], playerMark[1][3], playerMark[2][2], playerMark[3][1], playerMark[4][0]
        };

        boolean[] fullHousePattern = {
                playerMark[0][0], playerMark[0][1], playerMark[0][2], playerMark[0][3], playerMark[0][4],
                playerMark[1][0], playerMark[1][1], playerMark[1][2], playerMark[1][3], playerMark[1][4],
                playerMark[2][0], playerMark[2][1], playerMark[2][2], playerMark[2][3], playerMark[2][4],
                playerMark[3][0], playerMark[3][1], playerMark[3][2], playerMark[3][3], playerMark[3][4],
                playerMark[4][0], playerMark[4][1], playerMark[4][2], playerMark[4][3], playerMark[4][4],
        };


        // check if array are all true
        if (checkArrIfAllTrue(horiztontalPattern01) && patternPicked == 0){
            return true;
        }

        if (checkArrIfAllTrue(horiztontalPattern02) && patternPicked == 1){
            return true;
        }

        if (checkArrIfAllTrue(horiztontalPattern03) && patternPicked == 2){
            return true;
        }

        if (checkArrIfAllTrue(horiztontalPattern04) && patternPicked == 3){
            return true;
        }

        if (checkArrIfAllTrue(horiztontalPattern05) && patternPicked == 4){
            return true;
        }

        if (checkArrIfAllTrue(verticalPattern01)  && patternPicked == 5){
            return true;
        }

        if (checkArrIfAllTrue(verticalPattern02)  && patternPicked == 6){
            return true;
        }

        if (checkArrIfAllTrue(verticalPattern03)  && patternPicked == 7){
            return true;
        }

        if (checkArrIfAllTrue(verticalPattern04)  && patternPicked == 8){
            return true;
        }

        if (checkArrIfAllTrue(verticalPattern05)  && patternPicked == 9){
            return true;
        }

        if (checkArrIfAllTrue(diagonalPattern01)  && patternPicked == 10){
            return true;
        }

        if (checkArrIfAllTrue(diagonalPattern02)  && patternPicked == 11){
            return true;
        }

        if (checkArrIfAllTrue(crossPattern) && patternPicked == 12){
            return true;
        }

        if (checkArrIfAllTrue(fullHousePattern)  && patternPicked == 13){
            return true;
        }

        return false;
    }

    // -- CHECKER IF 1D ARRAY CONTAINS ALL TRUE -- //

    public static boolean checkArrIfAllTrue(boolean[] arr){
        for (boolean value : arr) {
            if (!value) {
                return false;
            }
        }
        return true;
    }

    // -- MARK THE CARD BASE IN ROULLETE RESULT -- //

    public static boolean[][] markCard(int[][] playerCard, boolean[][] playerMark, int rouletteResult){
        // find index of mark
        int index[] = findArrIntElementIndex(playerCard, rouletteResult);

        // then mark that spot on the card
        if (index != null && index.length == 2) {
            int rowIndex = index[0];
            int colIndex = index[1];
            playerMark[rowIndex][colIndex] = true;
        }

        return playerMark;
    }

    // -- FIND SPECIFIC ELEMENT IN 2D ARRAY, RETURNS ARRAY OF INDEX -- //

    public static int[] findArrIntElementIndex(int[][] arr, int target){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == target) {
                    return new int[]{i, j}; 
                }
            }
        }
        return null; 

    }

    // -- CHECK IF THE PLAYER CARD HAS THAT NUMBER BASE IN ROULLETE -- //

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

    // -- SHUFFLE METHOD -- //
    
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

    // -- REMOVING ELEMENT IN 1D ARRAY BASE IN TARGET VALUE -- //

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

    // -- REMOVING NULL IN ARRAY OF INT -- //

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

    // -- GENERATE CARDS -- //

    public static int[][] generatePlayerCard() {
        int[][] playerCard = new int[5][5];

        int[][] columns = new int[5][15];
        for (int i = 0; i < columns.length; i++) {
            int lowerBound = 1 + 15 * i;
            int upperBound = 15 * (i + 1);
            for (int j = lowerBound; j <= upperBound; j++) {
                columns[i][j - lowerBound] = j;
            }
            //------------------SHUFFLE------------------//
            shuffle2DArray(columns[i]);
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 2) {
                    playerCard[i][j] = -1;
                } else {
                    playerCard[i][j] = columns[j][i];
                }
            }
        }

        return playerCard;
    }

    // Shuffle the elements in an array
    private static void shuffle2DArray(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }


    public static boolean findDuplicate(int[] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = i + 1; j < arr.length; j++){
                if (arr[i] == arr[j]){
                    return true;
                }
            }
        }
        // return false when there is no pair
        return false;
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
        System.out.println("B\tI\tN\tG\tO");
        System.out.println("__________________________________");
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

    public static void displayPlayerCard(int[][] playerCard) {
        System.out.println("B\tI\tN\tG\tO");
        System.out.println("__________________________________");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (i == 2 && j == 2) {
                    System.out.print("F\t");
                } else {
                    System.out.print(playerCard[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void displayHasNumber(String playerName, int number){
         // CALCULATE BORDER TOP

         // COLOR
        String greenColor = "\u001B[32m"; 
        String resetColor = "\u001B[0m";
        System.out.print("+");
        for(int i = 0; i < playerName.length() + String.valueOf(number).length() + 18; i++){
            System.out.print("-");
        }
        System.out.println("+");  
        
        System.out.println("| "+ greenColor + playerName  + " has the number " + number + resetColor + " |" );

        // CALCULATE BORDER BOTTOM 
        System.out.print("+");

        for(int i = 0; i < playerName.length() + String.valueOf(number).length() + 18; i++){
            System.out.print("-");
        }

        System.out.println("+"); 
    }

    public static void displayHasNotNumber(String playerName, int number){
        // COLOR
        String redColor = "\u001B[31m";
        String resetColor = "\u001B[0m";
        System.out.print("+");
        for(int i = 0; i < playerName.length() + String.valueOf(number).length() + 25; i++){
            System.out.print("-");
        }
        System.out.println("+"); 

        System.out.println("| "+ redColor + playerName  + " don't have the number " + number + resetColor + " |" );

        System.out.print("+");

        for(int i = 0; i < playerName.length() + String.valueOf(number).length() + 25; i++){
            System.out.print("-");
        }

        System.out.println("+"); 
    }

    public static void displayWithBorder(String string){
        System.out.print("+");
        for(int i = 0; i < string.length() + 28; i++) {
            System.out.print("-");
        }
        
        System.out.println("+"); 

        System.out.println("|              "+  string + "              |" );

        System.out.print("+");

        for(int i = 0; i < string.length() + 28; i++){
            System.out.print("-");
        }

        System.out.println("+"); 
        System.out.println();
    }   
    
    public static void displayGameResultInterface(){
        System.out.println("╔═════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                            GAME RESULT                              ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════╝");
    }

    public static void displayLostInterface(){
        String redColor = "\u001B[31m";
        String resetColor = "\u001B[0m";
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║  "+ redColor +" PLAYERS WHO LOST THE GAME: "+ resetColor+"  ║");
        System.out.println("╚════════════════════════════════╝");
    }

    public static void displayTargetPattern(int patternPicked){
        switch (patternPicked) {
            case 0:
                System.out.println("First Row Straight Horizontal");
                System.out.println("X X X X X");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                break;
            case 1:
                System.out.println("Second Row Straight Horizontal");
                System.out.println("- - - - -");
                System.out.println("X X X X X");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                break;
            case 2:
                System.out.println("Third Row Straight Horizontal");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                System.out.println("X X X X X");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                break;
            case 3:
                System.out.println("Fourth Row Straight Horizontal");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                System.out.println("X X X X X");
                System.out.println("- - - - -");
                break;
            case 4:
                System.out.println("Fifth Row Straight Horizontal");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                System.out.println("- - - - -");
                System.out.println("X X X X X");
                break;
            case 5:
                System.out.println("First Column Straight Vertical");
                System.out.println("X - - - -");
                System.out.println("X - - - -");
                System.out.println("X - - - -");
                System.out.println("X - - - -");
                System.out.println("X - - - -");
                break;
            case 6:
                System.out.println("Second Column Straight Vertical");
                System.out.println("- X - - -");
                System.out.println("- X - - -");
                System.out.println("- X - - -");
                System.out.println("- X - - -");
                System.out.println("- X - - -");
                break;
            case 7:
                System.out.println("Third Column Straight Vertical");
                System.out.println("- - X - -");
                System.out.println("- - X - -");
                System.out.println("- - X - -");
                System.out.println("- - X - -");
                System.out.println("- - X - -");
                break;
            case 8:
                System.out.println("Fourth Column Straight Vertical");
                System.out.println("- - - X -");
                System.out.println("- - - X -");
                System.out.println("- - - X -");
                System.out.println("- - - X -");
                System.out.println("- - - X -");
                break;
            case 9:
                System.out.println("Fifth Column Straight Vertical");
                System.out.println("- - - - X");
                System.out.println("- - - - X");
                System.out.println("- - - - X");
                System.out.println("- - - - X");
                System.out.println("- - - - X");
                break;
            case 10:
                System.out.println("Top-Left Diagonal");
                System.out.println("X - - - -");
                System.out.println("- X - - -");
                System.out.println("- - X - -");
                System.out.println("- - - X -");
                System.out.println("- - - - X");
                break;
            case 11:
                System.out.println("Top-Right Diagonal");
                System.out.println("- - - - X");
                System.out.println("- - - X -");
                System.out.println("- - X - -");
                System.out.println("- X - - -");
                System.out.println("X - - - -");
                break;
            case 12:
                System.out.println("Cross");
                System.out.println("X - - - X");
                System.out.println("- X - X -");
                System.out.println("- - X - -");
                System.out.println("- X - X -");
                System.out.println("X - - - X");
                break;
            case 13:
                System.out.println("Full House");
                System.out.println("X X X X X");
                System.out.println("X X X X X");
                System.out.println("X X X X X");
                System.out.println("X X X X X");
                System.out.println("X X X X X");
                break;

            default:
                break;
        }
    }
}


class Player{
    String playerName;
    int[][] playerCard;
    boolean[][] playerMarkArr = {
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, true , false, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
    };
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

    public static void cont(){
        Scanner scan = new Scanner(System.in);
        System.out.print("\nPress Enter to Continue: ");
        scan.nextLine();
        Utils.clrscr();
    }
}

