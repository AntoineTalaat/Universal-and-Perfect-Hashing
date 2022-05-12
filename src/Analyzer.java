import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class Analyzer {
    static final int K = 32;
    public static void main(String[] args){
        KeysGenerator generator= new KeysGenerator();
        File nf= new File("n.txt");
        File n2f= new File("n2.txt");

        try {
            if (nf.createNewFile() && n2f.createNewFile()) {
                System.out.println("Files created: ");
            } else {
                System.out.println("Files already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PerfectHashingN2Solution N2 ;
        PerfectHashingNSolution N;
        int nCount ,n2Count;
        for(int i = 100 ; i <= Math.pow(2, 13); i+=10){
               ArrayList<Map.Entry<Integer,Object>> input = generator.generateInput(i);
               N = new PerfectHashingNSolution(K,input);
               N2 = new PerfectHashingN2Solution(K,input);
               appendToFile(nf,N.rehashingCount());
               appendToFile(n2f,N2.rehashingCount());
        }
    }




    private static void appendToFile(File file, int num){
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(String.valueOf(num));
            br.write("\n");
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



