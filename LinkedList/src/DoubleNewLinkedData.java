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
     * 数据量是N
     * find方法最坏的情况是position为N-1
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
     * 数据量是N
     * findPosition最坏情况下，是在最后一个位置找到对应元素
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
             * 避免使用了getUnit方法，next()时间复杂度为O(1)，所以循环N次，对比之前，findPosition的时间复杂度变为O(N)
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
     * 最坏情况是在倒数第二个结点后面插入
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
            lastUnit = lastUnit.last; //倒数第二个变为尾节点
            lastUnit.next = null; //切断新lastUnit与之前的lastUnit的连接
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
        Unit unitNow; //记录当前节点

        Unit next() {
            if (pointer == 0) {
                unitNow = firstUnit;
            } else {
                unitNow = unitNow.next; //0之后，每次只用调用一次next，从而避免使用getUnit方法
            }
            pointer++;
            return unitNow;
        }

        boolean hasNext() {
            return pointer != size - 1;
        }
    }
}
