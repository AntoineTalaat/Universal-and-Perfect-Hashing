import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class Solution {
    static final int keyBits=32;
    public static void main(String[] args){
        testBatch();
    }

    private static void testManually(){
        Scanner sc = new Scanner(System.in);
            System.out.println("Enter number of elements:");
            int num=sc.nextInt();

            PerfectHashingN2Solution<Integer> p1=new PerfectHashingN2Solution<Integer>(keyBits,new LinkedList<Element<Integer>>());

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

    private static void testBatch(){
        int inputSize=15000;
        try {
            TimeUnit.SECONDS.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        KeysGenerator gen = new KeysGenerator();
        List<Element<Integer>> g = gen.generateInput(inputSize);
        IPerfectHashing p=new PerfectHashingNSolution<Integer>(keyBits,g);

        System.out.println("rehashing count : " + p.rehashingCount());

    }
}
