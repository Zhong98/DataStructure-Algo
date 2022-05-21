public class MyIterator {
    MyArray myArray;
    int pointer=0;
    MyIterator(MyArray myArray){
        this.myArray=myArray;
    }
    int next(){
        return myArray.find(pointer++);
    }

    boolean hasNext(){
        return pointer!=myArray.size();
    }
}
