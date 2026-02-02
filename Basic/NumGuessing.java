import java.util.Random;
import java.util.Scanner;
class NumGuessing{

    public static boolean Guessing(int randomNum,Scanner sc){
        int maxMoves=7;

        for(int i=1;i<=maxMoves;i++){
        
            System.out.println("Enter number between (1-100): "+i+"th");
            int usernumber=sc.nextInt();
            if(usernumber>randomNum){
                 System.out.println("The number is smaller than your guess");
                
            }else if(usernumber<randomNum){
                 System.out.println("The number is greater than your guess");
                
            }else{
                System.out.println("Congrates YOu won in "+i+ " moves");
                return true;
            }
            System.out.println("Remaining chances: " + (maxMoves - i));
            System.out.println();
        }

        return false;
    } 

    public static void main(String[] args) {
        Random rand = new Random();
        int randomNum = rand.nextInt(100) + 1;

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("You have only 7 moves.");

        boolean result = Guessing(randomNum, sc);

        if (!result) {
            System.out.println("You lost! The number was: " + randomNum);
        }

        sc.close();

    }
}