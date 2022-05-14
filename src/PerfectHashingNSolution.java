import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PerfectHashingNSolution<T> implements IPerfectHashing{
    PerfectHashingN2Solution<T>[] Buckets;
    UniversalHashing FirstLevelHashing;
    int keybits = 32;
    int size = 0;
    int maxSize = 0;
    int FirstLevelCollisionCount = 0;
    int SecondLevelRehashingCount = 0;
    List<Element<T>> input;

    public PerfectHashingNSolution (int keybits,List<Element<T>>input){
        this.FirstLevelHashing = new UniversalHashing((int)Math.ceil(Math.log(input.size())/Math.log(2)),keybits);
        this.keybits = keybits;
        this.maxSize = input.size();
        this.insertBatch(input);
    }
     private void insert(int key, T value){
         int hashvalue=this.FirstLevelHashing.getHashValue(key);
         this.size++;
         assert this.size <= this.maxSize;
         if(Buckets[hashvalue]==null){ //no collision
             Buckets[hashvalue]=new PerfectHashingN2Solution<T>(keybits,new LinkedList<>());
             Buckets[hashvalue].InsertAdditional(key,value);
         }
         else{
             //collision
             this.FirstLevelCollisionCount++;
             int prevcount = Buckets[hashvalue].numberRehashing;
             //REHASHING
             Buckets[hashvalue].InsertAdditional(key,value);
             this.SecondLevelRehashingCount+= Buckets[hashvalue].numberRehashing - prevcount;
         }
     }
    
    public void insertBatch(List<Element<T>> input){
        this.input = input;
        this.Buckets = new PerfectHashingN2Solution[(int) Math.pow(2,(int)Math.ceil(Math.log(input.size())/Math.log(2)))];
        for(Element<T> element:input){
            int hashvalue=FirstLevelHashing.getHashValue(element.key);
            if(Buckets[hashvalue]==null){
                Buckets[hashvalue]=new PerfectHashingN2Solution<T>(keybits,new LinkedList<Element<T>>());
                Buckets[hashvalue].InsertAdditional(element.key,element.value);
            }
            else{
                //collision
                this.FirstLevelCollisionCount++;
                int prevcount = Buckets[hashvalue].numberRehashing;
                //REHASHING
                Buckets[hashvalue].InsertAdditional(element.key,element.value);
                this.SecondLevelRehashingCount+= Buckets[hashvalue].numberRehashing - prevcount;
            }
        }
    }
   public Object getValue(int key){
       return Buckets[this.FirstLevelHashing.getHashValue(key)].getValue(key);
   }
   public void printStorageContents(){
       int i=0;
       for (PerfectHashingN2Solution<T> bucket : Buckets) {
           System.out.print("Bucket " + (i++)+" : ");
           if(bucket!=null){
               System.out.print("\n");
               bucket.printStorageContents();
           }
           else{
               System.out.print("empty\n");
           }
           System.out.println("----------------");
       }
       System.out.println("First Level Collisions: "+FirstLevelCollisionCount);
       System.out.println("Second Level Rehashing count: "+SecondLevelRehashingCount);
   }

@Override
public int rehashingCount() {
    return this.SecondLevelRehashingCount;
}
public int space(){
    int space = 0;
    for (int i = 0; i < Buckets.length; i++) {
        if(Buckets[i]!=null){
            space += Buckets[i].space();
        }
    }
    return space;
}
}