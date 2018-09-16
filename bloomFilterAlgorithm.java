public class StandardBloomFilter {
    class HashFunc {
        int cap, seed;
        
        HashFunc(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }
        
        int hash(String s) {
            int ret = 0;
            int n = s.length();
            for (int i = 0; i < n; ++i) {
                ret += seed * ret + s.charAt(i);
                ret %= cap;
            }
            return ret;
        }
    }
    
    List<HashFunc> hashFunc;
    BitSet bs;
    int k;
    
    /*
    * @param k: An integer
    */public StandardBloomFilter(int k) {
        // do intialization if necessary
        this.k = k;
        this.hashFunc = new ArrayList<>();
        this.bs = new BitSet(20000);
        
        for (int i = 0; i < k; ++i) {
            this.hashFunc.add(new HashFunc(10000 + i, 2 * i + 3));
        }
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        // write your code here
        for (int i = 0; i < k; ++i) {
            int pos = hashFunc.get(i).hash(word);
            bs.set(pos);
        }
    }

    /*
     * @param word: A string
     * @return: True if contains word
     */
    public boolean contains(String word) {
        // write your code here
        for (int i = 0; i < k; ++i) {
            int pos = hashFunc.get(i).hash(word);
            if (!bs.get(pos)) {
                return false;
            }
        }
        return true;
    }
}

For more explanations about bloom filter algorithm: 
http://buttercola.blogspot.com/2015/11/big-data-bloom-filter.html