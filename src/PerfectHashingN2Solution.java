import java.util.LinkedList;
import java.util.List;

public class PerfectHashingN2Solution implements IPerfectHashing{
    Object[] storage;
    UniversalHashing u;
    int keybits;
    int hashbits;
    int N2;
    int numberRehashing=0;
    int maxSize;
//    List<Element> backup;
    int[] input;

    public PerfectHashingN2Solution(int sizeDesired,int keybits){
        this.keybits=keybits;
        maxSize = sizeDesired;
        if(sizeDesired == 1){
            this.storage = new Object[1];
            return;
        }
//        this.backup= new LinkedList<>();
        this.hashbits=(int)Math.ceil(Math.log(Math.pow(sizeDesired,2))/Math.log(2));
        this.N2=(int)Math.pow(2,hashbits);
        storage=new Object[N2];
        u=new UniversalHashing(this.hashbits,this.keybits);
    }

    public void setInput(int[] input){
        this.input=input;
    }

    public void insert(int key,Object value){
        if(maxSize == 1){
            this.storage[0] =value;
            return;
        }
        int hashvalue=u.getHashValue(key);
        Element temp = new Element(key,value);
//        backup.add(temp);
        if(storage[hashvalue]==null) //no collision
            storage[hashvalue]=temp.value;
        else{
            //collision
            //REHASHING
            this.storage=null;
            while(!rehashAndInsert(key,value)){
                this.storage=null;
                numberRehashing++;
                System.out.println("Rehashed in N^2, n rehash = " + numberRehashing);
            }
        }
    }
    //to insert beyond size
    public void InsertAdditional(int key, Object value){
        this.hashbits=(int)Math.ceil(Math.log(Math.pow(maxSize+1,2))/Math.log(2));
        this.N2=(int)Math.pow(2,hashbits);
        this.u=new UniversalHashing(this.hashbits,this.keybits);
        this.maxSize++;
        Element temp = new Element(key,value);
//        backup.add(temp);

        while(!rehashAndInsert(key,value)){
            numberRehashing++;
        }
    }
    public boolean rehashAndInsert(int key,Object value){
        Object[] newStorage=new Object[this.N2];
        u=new UniversalHashing(this.hashbits,this.keybits);
        for(int k:input){
            int hashvalue=u.getHashValue(k);
            if(newStorage[hashvalue]==null) //no collision
                newStorage[hashvalue]=50;
            else return false;
        }
        this.storage=newStorage;
        return true;
    }
//    public Object getValue(int key){
//        return this.storage[u.getHashValue(key)].value;
//    }
//    public void printStorageContents(){
//        for(int i=0 ;i<storage.length;i++){
//            System.out.print(i + "|");
//            if(storage[i]==null)             System.out.println("x");
//            else System.out.println( storage[i].key + " >> " +storage[i].value);
//        }
//    }
}
