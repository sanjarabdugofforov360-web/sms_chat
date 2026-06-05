package utils;

import java.util.Scanner;

public class Utill {
    public static Scanner scNum = new Scanner(System.in);
    public static Scanner scStr = new Scanner(System.in);

    public static String currentUserId;


    public static String getStr(String str){
        System.out.println(str+":");
        return scStr.nextLine();
    }

    public static int getNum(String str){
        System.out.println(str+":");
        return scNum.nextInt();
    }

}
