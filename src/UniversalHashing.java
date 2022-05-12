import java.util.Random;

public class UniversalHashing {
    private int rows;
    private int cols;
    private boolean[][] matrix;

    public UniversalHashing(int r,int c){
        this.rows=r;
        this.cols=c;
        this.matrix=this.generateRandomMatrix(this.rows,this.cols);
    }

    public int getHashValue(int key){
        if(this.rows == 0){
            return 0;
        }
        boolean[] keyvector=this.getVectorFromInt(key,this.cols);
        return this.getIntFromVector(this.generateHashVector(keyvector),this.rows);
    }

    /**
     *
     * @param vector boolean vector of bits (note: vector[len-1] is the bit corresponding to 2^0
     * @param dimension number of bits
     * @return
     */
    private int getIntFromVector(boolean[] vector,int dimension){
        if(dimension<1) throw new RuntimeException("illegal number of bits");
        int result=0;
        int base=1;
        for(int i=dimension-1;i>=0;i--){
            if(vector[i]) result+= base;
            base*=2;
        }
        return result;
    }

    /**
     *
     * @param num number to be converted to binary
     * @param dimension number of bits of key (needed to have the array of the correct size and not ignore initial 0s
     * @return vector of bits
     */
    private boolean[] getVectorFromInt(int num,int dimension){
        boolean[] vector= new boolean[dimension];
        int k=0;
        while (num >0){
            if(num%2==1) vector[dimension-k-1]=true;
            else vector[dimension-1-k]=false;

            num/=2;
            k++;
        }
        while (k<dimension) {
            vector[dimension-k-1]=false;
            k++;
        }
        return vector;
    }

    /**
     *
     * @param keyV vector of the key bits
     * @return vector of the hash value
     */
    private boolean[] generateHashVector(boolean[] keyV){
        //creating and initializing hash vector
        boolean[] hashV=new boolean[this.matrix.length];
        for(int i=0;i< this.matrix.length;i++) hashV[i]=false;

        for(int i=0;i< keyV.length;i++){
            if(keyV[i]){
                for(int j=0;j< this.matrix.length;j++)
                    hashV[j]^=this.matrix[j][i];
            }
        }
        return hashV;
    }


    /**
     *
     * @param rows number of rows in matrix(bits in the hashed value (result))
     * @param cols number of cols in matrix(bits in the key value)
     * @return  randomly generated boolean matrix (dimensions rows*cols) of true or false entries
     */
    private boolean[][] generateRandomMatrix(int rows,int cols){
        if(rows == 0){
            return null;
        }
        boolean[][] matrix=new boolean[rows][cols];

        //random settings
        Random rand = new Random();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                float x= rand.nextFloat();
                if(x<0.5) matrix[i][j]=true;
                else matrix[i][j]=false;
            }
        }
        return matrix;
    }
}
