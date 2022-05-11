import java.util.Random;

public class KeysGenerator {
    public int[] generateKeys(int amount){
        int[] generated = new int[amount];
        Random rand = new Random();
        for (int i = 0; i < generated.length; i++) {
            int n;
            do{n =  rand.nextInt(Integer.MAX_VALUE);}
            while(contains(generated,n));
            generated[i] = n;
        }
       return generated;
    }

    private boolean contains(int[] generated, int n) {
        for (int i : generated) {
            if(i == n){
                return true;
            }
        }
        return false;
    }
}
