package come.example.myone.ui;

public class Array {
    private int items[];
    private int count;
    public Array(int length){
        items = new int[length];
    }
    public void insert(int item){
    //if array is full, resize it
        if(items.length == count){
            int[] newItems = new  int[count*2];

            for (int i =0; i<count; i++){
                newItems[i] = items[i];
            }
            items = newItems;
        }
        //Add thew new item at the end
        items[count++] = item;

    }
    public void print(){
        for(int i=0; i<count;i++){
            System.out.println(items[i]);
        }
    }

    public void removeAt(int index){
        //Validate the index
        if(index < 0 || index>=count){
            throw new IllegalArgumentException();
        }
        //Shift the item to the left to fill the hole
        // [10,30,43,54,66]
        for(int i = index; i <count; i++){
            items[i] = items[i+1];
            count--;
        }
    }


}
