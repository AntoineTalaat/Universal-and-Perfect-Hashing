import java.util.Scanner;

public class PerfectHashingN2Solution {
    Element[] storage;
    UniversalHashing u;
    int keybits;
    int hashbits;
    int N;
    int numberRehashing=0;
    public PerfectHashingN2Solution(int sizeDesired,int k){
        this.hashbits=(int)Math.ceil(Math.log(sizeDesired)/Math.log(2));
        this.N=(int)Math.pow(2,hashbits);
        this.keybits=k;
        this.hashbits=this.hashbits*2;
        storage=new Element[(int)Math.pow(N,2)];
        u=new UniversalHashing(this.hashbits,this.keybits);
    }

    public void insert(int key,int value){
        int hashvalue=u.getHashValue(key);
        if(storage[hashvalue]==null) //no collision
            storage[hashvalue]=new Element(key,value);
        else{
            //collision
            //REHASHING
            while(!rehashAndInsert(key,value)){
                numberRehashing++;
                System.out.println("Rehashed in N^2, n rehash = " + numberRehashing);
            }
        }

    }

    public boolean rehashAndInsert(int key,int value){
        Element[] newStorage=new Element[(int)Math.pow(this.N,2)];
        u=new UniversalHashing(this.hashbits,this.keybits);
        for(int i=0;i<storage.length;i++){
            if(storage[i]!=null){
                int hashvalue=u.getHashValue(storage[i].key);
                if(newStorage[hashvalue]==null) //no collision
                    newStorage[hashvalue]=new Element(storage[i].key,storage[i].value);
                else return false;
            }
        }
        int hashvaluelast=u.getHashValue(key);
        if(newStorage[hashvaluelast]==null) //no collision
        {
            newStorage[hashvaluelast] = new Element(key, value);
            this.storage=newStorage;
            return true;
        }else
            return false;
    }

    public void printStorageContents(){
        for(int i=0 ;i<storage.length;i++){
            System.out.print(i + " ");
            if(storage[i]==null)             System.out.println("x");
            else System.out.println( storage[i].key + " >> " +storage[i].value);
        }
    }



}
