import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Solution {
    static final int keyBits=32;
    public static void main(String[] args){
        Integer dummyForAllElements=50;
        int inputSize=5000;
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        KeysGenerator gen = new KeysGenerator();
        int[] input = gen.generateKeys(inputSize);

        PerfectHashingN2Solution p1=new PerfectHashingN2Solution(inputSize,keyBits);
        p1.setInput(input);
        for(int i=0;i< input.length;i++){
            p1.insert(input[i],dummyForAllElements);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }

    private void testManually(){
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
//            p1.printStorageContents();

    }
}
