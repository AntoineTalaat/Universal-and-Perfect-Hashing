
import java.util.List;

public class PerfectHashingN2Solution<T> implements IPerfectHashing{
    Element<T>[] storage;
    UniversalHashing u;
    int keybits;
    int hashbits;
    int N2;
    int numberRehashing=0;
    int maxSize;
    List<Element<T>> input;

    public PerfectHashingN2Solution(int keybits,List<Element<T>> input){
        this.keybits=keybits;
        maxSize = input.size();
        this.hashbits=(int)Math.ceil(Math.log(Math.pow(maxSize,2))/Math.log(2));
        this.N2=(int)Math.pow(2,hashbits);
        storage=new Element[N2];
        insertBatch(input);
    }

    public void insertBatch(List<Element<T>> input){
        this.input=input;
        if(input.isEmpty()){
            return;
        }
        while(!rehash()){
            this.numberRehashing++;
        }
    }

     private void insert(int key,T value){
         Element<T> temp = new Element<T>(key,value);
         if(maxSize == 1){
             this.storage[0] =new Element<T>(key,value);
             return;
         }
         int hashvalue=u.getHashValue(key);
         input.add(temp);
         if(storage[hashvalue]==null) //no collision
             storage[hashvalue]=temp;
         else{
             //collision
             //REHASHING
             this.storage=null;
             while(!rehash()){
                 numberRehashing++;
                 System.out.println("Rehashed in N^2, n rehash = " + numberRehashing);
             }
         }
     }

    //to insert beyond size (useful for O(N) buckets)
    public void InsertAdditional(int key, T value){
        this.maxSize++;
        this.hashbits=(int)Math.ceil(Math.log(Math.pow(maxSize,2))/Math.log(2));
        this.N2=(int)Math.pow(2,hashbits);
        this.input.add(new Element<T>(key,value));
        while(!rehash()){
            numberRehashing++;
        }
    }
    public boolean rehash(){
        this.storage= new Element[N2];
        this.u=new UniversalHashing(this.hashbits,this.keybits);
        for(Element<T> inputElement:input){
            int hashvalue=u.getHashValue(inputElement.key);
                Element<T> newElement = new Element<T>(inputElement.key, inputElement.value);
                if(storage[hashvalue]==null||storage[hashvalue].key==inputElement.key) {//no collision
                    storage[hashvalue]=newElement;
                }
                else {return false;}
        }
        return true;
    }
    /** 
    * @Param the key 
    * @return - null if the key doesn't exist
    *         - the value of that key otherwise
    */
   public T getValue(int key){
        Element<T> requestedElement = this.storage[u.getHashValue(key)];
        //if key not found or if the requested key doesn't match the original key of the fetched element
        if(requestedElement == null || requestedElement.key != key){
            return null;
        }
        return requestedElement.value;
   }
   public void printStorageContents(){
       for(int i=0 ;i<storage.length;i++){
           System.out.print(i + "|");
           if(storage[i]==null)             System.out.println("x");
           else System.out.println( storage[i].key + " >> " +storage[i].value);
       }
   }

    public int rehashingCount() {
        return this.numberRehashing;
    }
    public int space(){
        return this.storage.length;
    }
    public boolean contains(int key){
     Element<T> requestedElement = this.storage[u.getHashValue(key)];
     if (requestedElement==null||requestedElement.key!=key){
         return false;
}
     return true;
}

}
