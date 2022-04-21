Explore
Problems
Interview
Contest
Discuss
Store
🔈 LeetCode is hiring! Apply NOW.🔈

Premium
11
104
Description
Solution
Discuss (999+)
Submissions
Back
Simple Java implementation | Rehashing | load Factor | 80% faster
6
abhi9720's avatar
abhi9720

609
Last Edit: 3 hours ago

219 VIEWS

For loop on list is almost negligible as if load factor goes greater than 0.7, Set Will Rehash
class MyHashSet {

    ArrayList<ArrayList<Integer>> set;
    int bucketSize ;
    int size;
    
    public MyHashSet() {
        bucketSize =  10;
        size = 0;
        set  =  new ArrayList<>();
        for(int i=0;i<bucketSize;i++){
            set.add(null);
        }                
    }
    
    private int getBucketIndex(int key){
        return key % bucketSize;
    }
    
    private double loadFactor(){
        return 1.0*size/bucketSize;
    }
    
    private void rehash(){ // rehash is  expensive       
        
        ArrayList<ArrayList<Integer>> temp =  set;
        set  =  new ArrayList<>();
        bucketSize*=1.5;
        for(int i=0;i<bucketSize;i++){
            set.add(null);
        }
        
        size = 0;
        
        
        for(int i=0;i<temp.size();i++ ){
            ArrayList<Integer> li =  temp.get(i);
            if(li!=null){
                for(int ele :li){
                    add(ele);
                }
            }
        }
    }
    public void add(int key) {
        double load  =  loadFactor();
        if(load > 0.7){
            rehash();
        }
        
        int index =  getBucketIndex(key);
        ArrayList<Integer> li =  set.get(index);
        if(li==null){
            li =  new ArrayList<>();
            set.set( index, li  );                        
        }
        for(int i=0;i<li.size();i++ ){
            if(li.get(i)==key ){
                return;
           }
        }
        
        size++;
        li.add(key);    
    }
    
    public void remove(int key) {
        int index =  getBucketIndex(key);
        ArrayList<Integer> li =  set.get(index);
        if(li==null) return;
        
        int idx = -1;
        for(int i=0;i<li.size();i++ ){
            if(li.get(i)==key ){
                idx =  i;
                break;
            }
        }
        if(idx==-1) return;
        
        
        int lastIndex =  li.size()-1;
        
        li.set(idx , li.get(lastIndex) ); // O(1) removal
        li.remove(lastIndex); 
        size--;
    }
    
    public boolean contains(int key) {
        
        int index =  getBucketIndex(key);                
        ArrayList<Integer> li =  set.get(index);                        
        if(li==null){            
            return false;
        }
        
        for(int i=0;i<li.size();i++ ){            
            if(li.get(i)==key ){
                return true;
            }
        }
        
         return false;        
    }
}
