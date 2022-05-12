import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class Solution {
    static final int keyBits=32;
    public static void main(String[] args){
        int inputSize=10;
        // try {
        //     TimeUnit.SECONDS.sleep(10);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        KeysGenerator gen = new KeysGenerator();
        ArrayList<Entry<Integer,Object>> g = gen.generateInput(inputSize);
        PerfectHashingNSolution p1=new PerfectHashingNSolution(keyBits,g);
        p1.printStorageContents();
    }

    private void testManually(){
        Scanner sc = new Scanner(System.in);
            System.out.println("Enter number of elements:");
            int num=sc.nextInt();

            PerfectHashingN2Solution p1=new PerfectHashingN2Solution(keyBits,new ArrayList<Map.Entry<Integer,Object>>());

            for(int i=0;i<num;i++){
                sc.nextLine();
                System.out.println("insert key and value :");
                int key=sc.nextInt();
                int value=sc.nextInt();
                p1.InsertAdditional(key, value);
            }
            sc.close();
            System.out.println("RESULTS");
//            p1.printStorageContents();

    }
}
