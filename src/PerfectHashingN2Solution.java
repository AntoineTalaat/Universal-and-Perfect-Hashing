
public class PerfectHashingN2Solution implements IPerfectHashing{
    Element[] storage;
    UniversalHashing u;
    int keybits;
    int hashbits;
    int N2;
    int numberRehashing=0;
    int maxSize;
    public PerfectHashingN2Solution(int sizeDesired,int keybits){
        maxSize = sizeDesired;
        this.hashbits=(int)Math.ceil(Math.log(Math.pow(sizeDesired,2))/Math.log(2));
        if(sizeDesired > 0 && hashbits<1){
            hashbits = 1;
        }
        this.N2=(int)Math.pow(2,hashbits);
        this.keybits=keybits;
        storage=new Element[N2];
        u=new UniversalHashing(this.hashbits,this.keybits);
    }

    public void insert(int key,Object value){
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
    //to insert beyond size
    public void InsertAdditional(int key, Object value){
        this.hashbits=(int)Math.ceil(Math.log(Math.pow(maxSize+1,2))/Math.log(2));
        this.N2=(int)Math.pow(2,hashbits);
        this.u=new UniversalHashing(this.hashbits,this.keybits);
        this.maxSize++;
        while(!rehashAndInsert(key,value)){
            numberRehashing++;
        }
    }
    public boolean rehashAndInsert(int key,Object value){
        Element[] newStorage=new Element[this.N2];
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
    public Object getValue(int key){
        return this.storage[u.getHashValue(key)].value;
    }
    public void printStorageContents(){
        for(int i=0 ;i<storage.length;i++){
            System.out.print(i + " ");
            if(storage[i]==null)             System.out.println("x");
            else System.out.println( storage[i].key + " >> " +storage[i].value);
        }
    }
}
