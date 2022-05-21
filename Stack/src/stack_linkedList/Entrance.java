package stack_linkedList;

import java.util.Scanner;

public class Entrance {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str=scanner.nextLine();
        CheckBrackets checkBrackets=new CheckBrackets();
        int check = checkBrackets.check(str);
        if (check!=0){
            System.out.println("Your brackets are not formatted correctly, please check and edit");
        }
    }
}
