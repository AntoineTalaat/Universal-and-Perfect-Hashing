import java.util.Scanner;

public class Solution {
    static final int keyBits=32;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of elements:");
        int num=sc.nextInt();

        PerfectHashingN2Solution p1=new PerfectHashingN2Solution(num,keyBits);

        for(int i=0;i<num;i++){
            sc.nextLine();
            System.out.println("insert key and value :");
            int key=sc.nextInt();
            int value=sc.nextInt();
            p1.insert(key,value);
        }
        sc.close();
        System.out.println("RESULTS");
        p1.printStorageContents();

    }
}
