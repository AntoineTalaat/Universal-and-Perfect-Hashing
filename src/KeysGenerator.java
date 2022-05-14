import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

public class KeysGenerator {
    public LinkedList<Element<Integer>> generateInput(int amount){
        LinkedList<Element<Integer>> generated = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i < amount; i++) {
            int n;
            do{n =  rand.nextInt(Integer.MAX_VALUE);}
            while(contains(generated,n));
            generated.add(new Element<Integer>(n,i));
        }
        return generated;
    }
    private boolean contains(List<Element<Integer>> generated, int n) {
        for (Element<Integer> i : generated) {
            if(i.key == n){
                return true;
            }
        }
        return false;
    }
}
