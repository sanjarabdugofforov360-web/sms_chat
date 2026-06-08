package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utill {
    public static Scanner scannerStr = new Scanner(System.in);
    public static Scanner scannerNum = new Scanner(System.in);

    public  static String currentUserId;


    public static String getStr(String text) {
        System.out.print(text + ": ");
        return scannerStr.nextLine();
    }

    public static int getNum(String text) {
        while (true) {
            try {
                System.out.print(text + ": ");
                return scannerNum.nextInt();
            } catch (   InputMismatchException e) {
                System.out.println("Xato! Faqat raqam kiriting.");
                scannerNum.next();
            }
        }
    }

}
