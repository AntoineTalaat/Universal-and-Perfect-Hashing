public class PerfectHashingNSolution implements IPerfectHashing{
    PerfectHashingN2Solution[] Buckets;
    UniversalHashing FirstLevelHashing;
    int keybits = 32;
    int size = 0;
    int maxSize = 0;
    int numberRehashing = 0;
    int FirstLevelCollisionCount = 0;
    int SecondLevelRehashingCount = 0;

    public PerfectHashingNSolution(int size, int keybits){
        this.FirstLevelHashing = new UniversalHashing((int)Math.ceil(Math.log(size)/Math.log(2)),keybits);
        this.keybits = keybits;
        this.maxSize = size;
        this.Buckets = new PerfectHashingN2Solution[(int) Math.pow(2,(int)Math.ceil(Math.log(size)/Math.log(2)))];
    }
    public void insert(int key, Object value){
        int hashvalue=this.FirstLevelHashing.getHashValue(key);
        this.size++;
        assert this.size <= this.maxSize;
        if(Buckets[hashvalue]==null){ //no collision
            Buckets[hashvalue]=new PerfectHashingN2Solution(1,keybits);
            Buckets[hashvalue].insert(key,value);
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
//    public Object getValue(int key){
//        return Buckets[this.FirstLevelHashing.getHashValue(key)].getValue(key);
//    }
//    public void printStorageContents(){
//        int i=0;
//        for (PerfectHashingN2Solution bucket : Buckets) {
//            System.out.print("Bucket " + (i++)+" : ");
//            if(bucket!=null){
//                System.out.print("\n");
//                bucket.printStorageContents();
//            }
//            else{
//                System.out.print("empty\n");
//            }
//            System.out.println("----------------");
//        }
//        System.out.println("First Level Collisions: "+FirstLevelCollisionCount);
//        System.out.println("Second Level Rehashing count: "+SecondLevelRehashingCount);
//    }
}