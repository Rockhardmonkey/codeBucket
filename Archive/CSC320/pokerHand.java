import java.util.Arrays;
import java.util.Random;

public class pokerHand {
                private static final int handSize =5;
                private static Integer [] playerHand = new Integer[handSize];
                private static Random random = new Random ();


                public static void main(String[] args) {
                                initializeHand();

                                System.out.println("Initial Hand Is: " + Arrays.toString(playerHand));
                }
                private static void initializeHand() {

                                for (int i =0; i < handSize; i++) {

                                                playerHand[i] = random.nextInt(52); // Cards From 0 To 51

                                }


    }
}