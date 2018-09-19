package client;

import general.iStringProcessor;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        while (true) {
            iStringProcessor processor = new StringProcessorProxy();
            System.out.println("Enter String");
            Scanner in = new Scanner(System.in);
            String string = in.nextLine();
            System.out.println("select method: toLowerCase, trim, parseDouble");
            System.out.println("               1,           2,    3");
            try {
               int choice = in.nextInt();
               if(choice == 1){
                   System.out.println(processor.toLowerCase(string));
               } else if (choice == 2) {
                   System.out.println(processor.trim(string));
               } else if (choice == 3) {
                   System.out.println(processor.parseDouble(string));
               }

            } catch (IOException e) {
                System.out.println("an input error occurred");
            } catch (NumberFormatException e) {
                System.out.println("a number format error occurred");
            }catch (Exception e) {
                System.out.println("a connection error occurred");
            }
            System.out.println("\n");
        }
    }

}
