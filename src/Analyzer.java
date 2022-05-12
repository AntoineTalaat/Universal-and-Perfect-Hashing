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
        File nf_rehashing= new File("n_rehashing.txt");
        File n2f_rehashing= new File("n2_rehashing.txt");
        File nf_space= new File("n_sp.txt");
        File n2f_space= new File("n2_sp.txt");
        File[] files = {nf_rehashing,n2f_rehashing,nf_space,n2f_space};
        try {
            for (File f : files) {
                f.createNewFile();
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
               appendToFile(nf_rehashing,N.rehashingCount());
               appendToFile(n2f_rehashing,N2.rehashingCount());
               appendToFile(nf_space,N.space());
               appendToFile(n2f_space,N2.space());
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



