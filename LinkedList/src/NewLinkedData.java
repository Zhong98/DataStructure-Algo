class NewLinkedData {
    Unit firstUnit;
    Unit lastUnit;
    int size;

    public static void main(String[] args) {
        NewLinkedData newLinkedData = new NewLinkedData();
        newLinkedData.insert(1);
        newLinkedData.insert(4);
        newLinkedData.insert(2);
        newLinkedData.insert(5);
        newLinkedData.insert(10);
        newLinkedData.insert(12);
        newLinkedData.show();
        System.out.println("----------------------");
        newLinkedData.reverse();
        Unit unitNow=newLinkedData.firstUnit;
        while (unitNow!=null){
            System.out.println(unitNow.data);
            unitNow=unitNow.next;
        }
    }

    public int find(int position) {
        Unit unit = getUnit(position);
        if (unit == null)
            throw new RuntimeException();
        else
            return unit.data;
    }

    private Unit getUnit(int position) {
        if (firstUnit == null || position < 0)
            return null;
        if (position == 0)
            return firstUnit;
        if (position == size - 1)
            return lastUnit;
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
     * The amount of data is N
     * The worst case of findPosition is to find the corresponding element in the last position
     * i->0-size-1
     * 4n+5n*(0)+2n
     * 4n+5n*1+2n
     * 4n+5n*2+2n
     * ...4n+5n*（N-1）+2n
     * O(N^2)
     */

    int findPosition(int x) {
        for (int i = 0; i < size; i++) {
            if (x == find(i)) {
                return i;
            }
        }
        return -1;
    }

    void show() {
        for (int i = 0; i < size; i++) {
            System.out.println(find(i));
        }
    }

    /**
     * O(N)
     * The worst case is to insert after the penultimate node
     */
    boolean insert(int x, int position) {
        if (position < 0 || position >= size) {
            return false;
        }
        Unit data = new Unit(x);
        Unit unit = getUnit(position);
        Unit temp = unit.next;
        unit.next = data;
        data.next = temp;
        if (position == size - 1) {
            lastUnit = data;
        }
        size++;
        return true;
    }

    /**
     * O(1)
     */
    boolean insert(int x) {
        if (size == 0) {
            firstUnit = new Unit(x);
            //lastUnit=firstUnit;
            size++;
            return true;
        } else {
            return insert(x, size - 1);
        }
    }

    public void reverse() {
        Unit prev=null;
        Unit temp=firstUnit;
        Unit next;
        while(temp!=null){
            next=temp.next;
            temp.next=prev;
            prev=temp;
            temp=next;
        }
        Unit unitTemp;
        unitTemp=firstUnit;
        firstUnit=lastUnit;
        lastUnit=unitTemp; //reverse the head and tail
    }
}
