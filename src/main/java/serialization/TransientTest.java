package serialization;

import java.io.*;

/**
 * Created by yiban on 2017/4/1.
 */
public class TransientTest implements Serializable {

    private ASerialObject aserialobject;

    private transient ASerialObject aserialobject2;

    private SerialObject serialObject;

    public static void main(String[] args) {
        ASerialObject aSerialObject = new ASerialObjectImpl(1, "aaa", "aserialobject");
        ASerialObject aSerialObject2 = new ASerialObjectImpl(2, "bbb", "aserialobject2");
        SerialObject serialObject = new SerialObject(3, "ccc", "serialObject");
        TransientTest test = new TransientTest(aSerialObject, aSerialObject2, serialObject);
        writeFile(test, "H://obj.txt");
        readFile("H://obj.txt");
        
/*        Object[] objs = new Object[]{aserialobject, aserialobject2, serialObject};
        for (int i = 0; i < objs.length; i++) {
            writeFile(objs[i],"H://obj"+i+".txt");
        }*/
    }

    private static void writeFile(Object obj, String path) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path));
            os.writeObject(obj); // 将U对象写进文件
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile(String path) {
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(path));
            TransientTest test = (TransientTest)os.readObject(); // 将U对象写进文件
            os.close();
            System.out.println(test);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TransientTest(ASerialObject aserialobject, ASerialObject aserialobject2, SerialObject serialObject) {
        this.aserialobject = aserialobject;
        this.aserialobject2 = aserialobject2;
        this.serialObject = serialObject;
    }

    @Override
    public String toString() {
        return " aserialobject:" + aserialobject + "\n aserialobject2:" + aserialobject2 + " \n serialObject" + serialObject;
    }
}

class ASerialObject {
    private int no;

    private String name;

    private Object object;

    public ASerialObject() {
    }

    public ASerialObject(int no, String name, Object object) {
        this.no = no;
        this.name = name;
        this.object = object;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "no:" + no + " name:" + name + " object:" + object;
    }
}

class ASerialObjectImpl extends ASerialObject implements Serializable {
    public ASerialObjectImpl() {
        super();
    }

    public ASerialObjectImpl(int no, String name, Object object) {
        super(no, name, object);
    }
}


class SerialObject implements Serializable {
    private int no;

    private String name;

    private Object object;

    public SerialObject(int no, String name, Object object) {
        this.no = no;
        this.name = name;
        this.object = object;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "no:" + no + " name:" + name + " object:" + object;
    }
}