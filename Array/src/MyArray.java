class MyArray{
    private int[] data;
    private int length=0;
    MyArray(){
        data=new int[5];
    }

    void expand(){
        if(length>=data.length/2){
            int[] temp=data;
            data=new int[data.length*2];
            for(int i=0;i<length;i++){
                data[i]=temp[i];
            }
        }
    }
    //2n
    //5n+3n*length

    public int size(){
        return length;
    }



    public int find(int x){
        return data[x];
    }
    /**
     *   initialAddress+(x-0)*32
     *   get(1)-->initialAddress+(1-0)*32
     *   get(2)-->initialAddress+(2-0)*32
     */

    public void insert(int x){
        expand();
        data[length++]=x;
    }

    public int findPosition(int x){
        for(int i=0;i<length;i++){
            if(data[i]==x){
                return i;
            }
        }
        return -1;
    }

    public void insert(int x,int position){
        expand();//2n
        length++;
        int posTemp=position;
        int temp=0;
        int result=0;
        result=data[position];
        for(int i=position+1;i<length;i++){
            temp=data[i];
            data[i]=result;
            result=temp;
            position++;
        }
        data[posTemp]=x;
    }

    public void remove(int position){
        for (int i = position; i < length-1; i++) {
            data[i]= data[i+1];
        }
        length--;
    }

    public void remove(){
        data[length-1]=0;
        length--;
    }

    /**
     *     1.position:10n+(length-position-1)*15n
     *     2.position=0:  10n+15n*(length-1)+(5n+3n*length)
     *     f1(x)=10n+15n*(length-1)=O(length)
     *     f2(x)=5n+3n*length=O(length)
     *     f1(x)+f2(x)=O(length+length)=O(length)
     */

    void show(){
        for(int i=0;i<length;i++){
            System.out.print(data[i]+"-");
        }
        System.out.println();
    }

    Iterator iterator(){
        return this.new Iterator();
    }

    public static void main(String[] args) throws Exception {
        MyArray a=new MyArray();
        a.insert(3);
        a.insert(10);
        a.insert(100);
        a.insert(101);
        a.insert(102);
        a.show();
        System.out.println("the length of data:"+ a.data.length);
        MyArray.Iterator iterator=a.iterator();
        while (iterator.hasNext()){
            if (iterator.next()==3){
                iterator.remove();
            }
            System.out.println(iterator.next());
        }
        /*System.out.println(iterator.next());
        System.out.println(iterator.next());
        iterator.remove();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());*/

        a.show();
    }

    public class Iterator {
        int pointer=0;

        int next() {
            if (pointer>length){
                throw new IndexOutOfBoundsException();
            }
            return MyArray.this.find(pointer++);
        }

        boolean hasNext(){
            return pointer!=MyArray.this.size();
        }

        void remove(){
            MyArray.this.remove(pointer-1);
        }
    }
}
