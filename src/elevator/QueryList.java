package elevator;

import com.sun.tools.classfile.ConstantPool;

import java.util.ArrayList;

class UnsortedException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnsortedException(String message) {
        super(message);
    }
}
public class QueryList {
    /**
     * OVERVIEW:请求队列类，管理乘客请求（Query）
     * 请求队列需要在添加请求时对请求的合法性进行二次判断，所以需要记录楼层的取值范围，队列中最后一个请求的时间
     * 对于不满足时间非降序的添加请求，需要抛出一个可辨识的异常便于处理逻辑，所以额外定义了一个UnsortedException类
     * 请求队列提供添加、遍历、清空的方法
     */
    //请求队列
    private ArrayList<Query> queue;
    //最高和最低楼层
    private int highLevel, lowLevel;
    //队列中最近一次请求的时间
    private double lastTime;

    //构造方法1
    public QueryList(int high, int low, double time) throws Exception {
        queue = new ArrayList<Query>();
        if(high<=low){
            throw new Exception("High is no more than low.");
        }
        highLevel = high;
        lowLevel = low;
        lastTime = time;
    }
    //构造方法2
    public QueryList(int high, int low) throws Exception{
        this(high, low, 0);
    }

    //插入
    public boolean append(Query req) throws NullPointerException, UnsortedException {
        /**@ REQUIRES: req != null ;
         @ MODIFIES: this;
         @ EFFECTS:
         (this.lastTime>req.queryTime)==>\result=false;
         (req.targetFloor=low && req.queryDirection==Direction.DOWN)==>\result=false;
         (req.targetFloor=high && req.queryDirection==Direction.UP)==>\result=false;
         (this.queue.size == \old(this.queue).size+1) && (this.queue.contains(req)==true)&&(this.queue.lastTime==req.queryTime) && (\result==true);
         */
        //空指针
        if(req==null) {
            throw new NullPointerException("Req Is Null");
            //return false;
        }
        //时间乱序
        if( (this.lastTime>req.getTime())) {
            throw new UnsortedException("Unsorted req");
            //return false;
        }
        //底层无下
        if( (req.getTarget()==this.lowLevel && req.getDirection()== Query.Direction.DOWN)) {
            System.out.println("First floor has no down");
            return false;
        }
        //顶层无上
        if((req.getTarget()==this.highLevel && req.getDirection()== Query.Direction.UP)) {
            System.out.println("Top floor has no up");
            return false;
        }
        this.queue.add(req);
        this.lastTime =req.getTime();
        return true;
    }

    //删除
    public boolean remove(int index) throws Throwable{
        /**
        @MODIFIES:this
        @EFFECTS:
           normal_behavior
           (\old(this).get(index) !=null) ==> (this.size == \old(this).size-1) && (this.contains(\old(this).get(index))==false) && (\result==true) ;
           (\old(this).size ==0)==>exceptional_behavior(EmptyQueueException)
           (index >=\old(this).size) ==> exceptional_behavior (InvalidIndexException);
           (index < 0) ==> exceptional_behavior (InvalidIndexException);
        */
        if(this.getSize()==0){
            throw new Exception("Empty Queue.");
        }
        if(index>=this.getSize()||index<0){
            throw new Exception("Invalid Index.");
        }
        if(this.getQuery(index)!=null){
            queue.remove(index);
            return true;
        }else {
            return false;
        }
//        try {
//            queue.remove(index);}
//        catch(Exception e) {
//            return false;
//        }
    }

    public int getSize() {
        return queue.size();
    }

    public Query getQuery(int index) {
        return queue.get(index);
    }

    public void clear() {
        queue.clear();
    }

    public void setQueue(ArrayList<Query> queue){
        this.queue = queue;
    }

}

