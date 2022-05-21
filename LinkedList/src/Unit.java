public class Unit {
    int data;
    Unit next;
    Unit last;
    Unit(int data){
        this.data=data;

    }
}

/**
 * Unit unit1=new Unit(2);//unit1,linkedData.firstUnit
 * Unit unit2=new Unit(1);//unit2,unit1.next
 * Unit unit3=new Unit(4);//unit3,unit2.next
 * unit1.next=unit2;
 * unit2.next=unit3;
 * unit1-->unit1.next-->unit1.next.next
 * LinkedData linkedData=new LinkedData();
 * linkedData.firstUnit=unit1;
 *
 */
class LinkedData {
    Unit firstUnit;
    Unit lastUnit;
    int size;


    /**
     *   数据量是N
     *   find方法最坏的情况是position为N-1
     *   6n+5n*(N-1)--->O(N)
     */
    public int find(int position) {
        Unit unit=getUnit(position);
        if(unit==null)
            throw new RuntimeException();
        else
            return unit.data;
    }

    private Unit getUnit(int position){
        if (firstUnit == null||position<0)
            return null;
        if (position == 0)
            return firstUnit;
        int index = 0;
        Unit nextNode = firstUnit;
        while (nextNode.next != null) {
            nextNode = nextNode.next;
            index++;
            if (index == position)
                return nextNode;
        }
        return null;
    }



    /**
     *   数据量是N
     *   findPosition最坏情况下，是在最后一个位置找到对应元素
     *   i->0-size-1
     *   4n+5n*(0)+2n
     *   4n+5n*1+2n
     *   4n+5n*2+2n
     *   ...4n+5n*（N-1）+2n
     *   O(N^2)
     */

    public int findPosition(int x){
        for(int i=0;i<size;i++){
            if(x==find(i)) {
                return i;
            }
        }
        return -1;
    }

    void show(){
        for(int i=0;i<size;i++){
           System.out.println(find(i));
        }

    }

    /**
     * O(N)
     */
    public boolean insert(int x, int position){
        if(position<0||position>=size){
            return false;
        }
        Unit data=new Unit(x);
        Unit unit=getUnit(position);
        Unit temp=unit.next;
        unit.next=data;
        data.next=temp;
        size++;
        return true;

    }
    /**
     * O(N)
     */
    public boolean insert(int x){
        if(size==0){
            firstUnit=new Unit(x);
            size++;
            return true;
        }else{
            return insert(x,size-1);

        }

    }


}



