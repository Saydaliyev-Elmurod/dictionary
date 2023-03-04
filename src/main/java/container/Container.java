package container;

import repository.WordRepository;
import service.WordService;

import java.util.Scanner;

public class Container {
    public static int correct=0;
    public static int all= 0;
    public static WordRepository wordRepository ;
    public static WordService wordService ;
    static {
        wordRepository = new WordRepository();
        wordService = new WordService();
    }
    public static Scanner scannerInt = new Scanner(System.in);
    public static Scanner scannerString = new Scanner(System.in);

    public static int getAction() {
        System.out.print("Action>> ");
        return scannerInt.nextInt();
    }
    public static String getAnswer() {
        System.out.print("Answer(a,b,c,d,A,B,C,D)>> ");
        return scannerString.next();
    }
    public static String getTranslate() {
        System.out.print("Answer(Exit q or Q )>> ");
        return scannerString.next();
    }
}
