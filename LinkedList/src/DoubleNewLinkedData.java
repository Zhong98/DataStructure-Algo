class DoubleNewLinkedData {
    Unit firstUnit;
    Unit lastUnit;
    int size;


    public static void main(String[] args) {
        DoubleNewLinkedData doubleNewLinkedData = new DoubleNewLinkedData();
        doubleNewLinkedData.insert(1);
        doubleNewLinkedData.insert(4);
        doubleNewLinkedData.insert(2);
        doubleNewLinkedData.insert(5);
        doubleNewLinkedData.insert(10);
        doubleNewLinkedData.insert(12);
        doubleNewLinkedData.show();
        System.out.println("-----------------------------------------");
        doubleNewLinkedData.remove(2);
        doubleNewLinkedData.remove();
        doubleNewLinkedData.insert(15);
        doubleNewLinkedData.show();
        System.out.println("-----------------------------------------");
        System.out.println(doubleNewLinkedData.lastUnit.last.data);
        System.out.println(doubleNewLinkedData.findPosition(10));
    }

    private Unit getUnit(int position) {
        if (position > size - 1)
            return null;
        if (position == 0)
            return firstUnit;
        if (position == size - 1)
            return lastUnit;
        int index = 0;
        if (position <= size / 2) {
            Unit nextNode = firstUnit;
            while (nextNode.next != null) {
                nextNode = nextNode.next;
                index++;
                if (index == position)
                    return nextNode;
            }
        } else {
            int backIndex = size - 1;
            Unit lastNode = lastUnit;
            while (lastNode.last != null) {
                lastNode = lastNode.last;
                backIndex--;
                if (backIndex == position)
                    return lastNode;
            }
        }
        return null;
    }

    /**
     * The amount of data is N
     * The worst sistuation of method find is position=N-1
     * 6n+5n*(N-1)--->O(N)
     */

    public int find(int position) {
        Unit unit = getUnit(position);
        if (unit == null)
            throw new RuntimeException();
        else
            return unit.data;
    }

    /**
     * The amount of data is N
     * The worst sistuation of method findPosition is to find an element in the last position
     * i->0-size-1
     * 4n+5n*(0)+2n
     * 4n+5n*1+2n
     * 4n+5n*2+2n
     * ...4n+5n*（N-1）+2n
     * O(N^2)
     */

    /*public int findPosition(int x){
        for (int i = 0; i < size; i++) {
            if (x==find(i)){
                return i;
            }
        }
        return -1;
    }*/
    public int findPosition(int x) {
        DoubleNewLinkedData.Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            /**
             * avoid using method getUnit，next() time complexity is O(1)，So loop N times, before the comparison, the time complexity of findPosition becomes O(N)
             */
            if (x == iterator.next().data) {
                return iterator.pointer;
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
    public boolean insert(int x, int position) {
        if (position < 0 || position >= size) {
            return false;
        }
        Unit data = new Unit(x);
        Unit unit = getUnit(position);
        Unit temp = unit.next;
        unit.next = data;
        data.last = unit;
        if (temp != null) {
            data.next = temp;
            temp.last = data;
        }
        if (position == size - 1) {
            lastUnit = data;
        }
        size++;
        return true;
    }

    /**
     * O(1)
     */
    public boolean insert(int x) {
        if (size == 0) {
            firstUnit = new Unit(x);
            lastUnit = firstUnit;
            size++;
            return true;
        } else {
            return insert(x, size - 1);
        }
    }

    public void remove(int position) {
        if (position < 0 || position >= size) {
            throw new RuntimeException();
        }
        if (position == size - 1) {
            lastUnit = lastUnit.last; //The second to last becomes the last node
            lastUnit.next = null; //Disconnect the new lastUnit from the previous lastUnit
        } else {
            Unit dataBefore = getUnit(position - 1);
            Unit dataAfter = getUnit(position + 1);
            dataBefore.next = dataAfter;
            dataAfter.last = dataBefore;
        }
        size--;
    }

    public void remove() {
        remove(size - 1);
    }

    Iterator iterator() {
        return this.new Iterator();
    }

    class Iterator {
        int pointer = 0;
        Unit unitNow; 

        Unit next() {
            if (pointer == 0) {
                unitNow = firstUnit;
            } else {
                unitNow = unitNow.next; 
            }
            pointer++;
            return unitNow;
        }

        boolean hasNext() {
            return pointer != size - 1;
        }
    }
}
