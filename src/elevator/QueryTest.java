package elevator;
import static org.junit.Assert.*;

import oracle.jrockit.jfr.jdkevents.ThrowableTracer;
import org.junit.Test;

public class QueryTest{
    //测试构造方法1

    //测试构造方法2
    @Test(expected = Throwable.class)
    public void testString()throws Throwable{
        //3.发起人
            Query q1 = new Query("(QR,1,UP,1)");
            assertFalse(q1.repOk());
            System.out.println("here");
    }

    @Test
    public void testString1(){
        //3.发起人
        try {
            Query b4 =new Query("(ER,1,NONE,1)");
            //assertTrue(b4.repOk());
           //assertFalse(b4.repOk());
           assertFalse(true);
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

    @Test(expected = Throwable.class)
    public void testQuery() throws Throwable{
        String buf = "(FR,3,UP,1)";
        String[] str = buf.split("[(,)]");
        for(int i=1;i<str.length;i++) {
            System.out.println(str[i]);
        }
      //  try{
            //测试构造方法3字符串构造
        System.out.println("1");
            //1.楼层
            //正确边界
            Query a1 =new Query("(FR,1,UP,1)");
            assertTrue(a1.repOk());
            Query a2 =new Query("(FR,10,UP,1)");
            assertTrue(a2.repOk());
            //错误边界
            Query a3 =new Query("(FR,0,UP,1)");
            Query a4 =new Query("(FR,11,UP,1)");
            assertFalse(a3.repOk());
            assertFalse(a4.repOk());
        System.out.println("2");
            //2.方向
            Query b4 =new Query("(ER,1,NONE,1)");
            assertTrue(b4.repOk());
            assertFalse(b4.repOk());
//            Query b1 =new Query("(FR,1,NONE,1)");
//            assertFalse(b1.repOk());
//            Query b2 =new Query("(ER,1,UP,1)");
//            assertFalse(b2.repOk());
//
//        System.out.println("here");
//
//        Query b3 =new Query("(FR,1,Hello,1)");
//            assertFalse(b3.repOk());


     //   }catch (Throwable e){
     //       e.printStackTrace();
      //  }
    }

//    @Test
//    public void testQuery(){
//        Query a = new Query(1,1, Query.Direction.UP);
//        assertTrue(a.repOk());
//    }
}


