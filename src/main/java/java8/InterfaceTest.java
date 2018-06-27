package java8;

/**
 * troy
 * 2018/6/10
 **/
public interface InterfaceTest {
    default void testInterface(){
        System.out.println("Interface can have default method implements");
    }

    class InterfaceTestImpl implements InterfaceTest{

    }
    static void main(String[] args) {
        InterfaceTest implTest=new InterfaceTestImpl();
        implTest.testInterface();
    }
}
