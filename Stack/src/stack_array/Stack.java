package stack_array;

public class Stack<T> {
    T[] datas;
    int length=0;
    public Stack(int n){
        datas=(T[])new Object[n];
    }

    private void expand(){
        if(length>=datas.length/2){
            T[] temp=datas;
            datas=(T[])new Object[datas.length*2];
            for(int i=0;i<length;i++){
                datas[i]=temp[i];
            }
        }
    }
    public void insert(T t){
        expand();
        datas[length++]=t;
    }

    public T pop(){
        T t=datas[length-1];
        datas[length-1]=null;
        length--;
        return t;
    }
}
