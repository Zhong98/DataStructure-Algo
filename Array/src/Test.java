import java.util.ArrayList;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) throws Exception {
//        ArrayList a=new ArrayList();
//        a.add(1);
//        a.add(2);
//        a.add(3);
//        a.add(4);
//        for(int i=0;i<a.size();i++){
//            System.out.println(a.get(i));
//        }
//        System.out.println("-----");
//        Iterator myIterator=a.iterator();
//        while(myIterator.hasNext()){
//            System.out.println(myIterator.next());
//        }
        MyArray a=new MyArray();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        a.insert(4);
        for(int i=0;i<a.size();i++){
            System.out.println(a.find(i));
        }
        System.out.println("-----");
        MyArray.Iterator myIterator=a.iterator();
        while(myIterator.hasNext()){
            System.out.println(myIterator.next());
        }
    }
}
