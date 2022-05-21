 class  MyArrayNew{
    Object[] data;
    int length=0;
     MyArrayNew(){
        data=new Object[5];
    }

    void expand(){
        if(length>=data.length/2){
            Object[] temp=data;
            data=  new Object[data.length*2];
            for(int i=0;i<length;i++){
                data[i]=temp[i];
            }
        }
    }
    //2n
    //5n+3n*length


     Object find(int x){
        return data[x];
    }

    void insert(Object x){
        expand();
        data[length++]=x;
    }

    int findPosition(Object x){
        for(int i=0;i<length;i++){
            if(data[i].equals(x)){
                return i;
            }
        }
        return -1;
    }

    void insert(Object x,int position){
        expand();//2n
        length++;
        int posTemp=position;
        Object temp=0;
        Object result=0;
        result=data[position];
        for(int i=position+1;i<length;i++){
            temp=data[i];
            data[i]=result;
            result=temp;
            position++;
        }
        data[posTemp]=x;
    }

    void show(){
        for(int i=0;i<length;i++){
            Person p=(Person) data[i];
            System.out.print(p.age+"-");
        }
        System.out.println();
    }

    public static void main(String[] args){
        MyArrayNew a=new MyArrayNew();
        Person p1=new Person(0);
        Person p2=new Person(1);

        Person p3=new Person(2);

        Person p4=new Person(3);
        Person p5=new Person(4);


        a.insert(p1);
        a.insert(p2);
        a.insert(p3);
        a.insert(p4);
        a.insert(p5,0);

        a.show();

    }
}
