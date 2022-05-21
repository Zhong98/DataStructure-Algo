package stack_linkedList;

public class Stack<T> {
    int length=0;
    Unit top; 
    public void insert(T t){
        Unit newUnit=new Unit<>(t);
        if (length==0){
            top=newUnit;
        }else {
            newUnit.next=top; 
            top=newUnit;
        }
        length++;
    }

    public Unit pop(){
        if(length>0){
            Unit unitPop=top;
            top=top.next;
            length--;
            return unitPop;
        }else {
            throw new IndexOutOfBoundsException();
        }
    }
}
