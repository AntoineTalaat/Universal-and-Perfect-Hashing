import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class KeysGenerator {
    public ArrayList<Map.Entry<Integer,Object>> generateInput(int amount){
        ArrayList<Map.Entry<Integer,Object>> generated = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < amount; i++) {
            int n;
            do{n =  rand.nextInt(Integer.MAX_VALUE);}
            while(contains(generated,n));
            generated.add(Map.entry(n,i));
        }
        return generated;
    }
    private boolean contains(ArrayList<Map.Entry<Integer,Object>> generated, int n) {
        for (Entry<Integer, Object> i : generated) {
            if(i.getKey() == n){
                return true;
            }
        }
        return false;
    }
}
