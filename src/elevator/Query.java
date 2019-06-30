package elevator;

public class Query {
    /*	不变式
    不管来自电梯内外，最终都要由电梯运行，因此可以都转换为请求
    请求的通用表达方式
    请求：{请求者，请求目标楼层，请求时间，请求方向}
    实际需要使用的属性
    {请求目标楼层，请求时间，请求方向}

    输入时，请求者来自电梯内，方向为NONE
    反之，
    打印时，如果方向为NONE则请求来自电梯内

	AF(c)={Query, targetFloor, queryTime, queryDirection}
	Where 1=<targetFloor<=10 , 0=<queryTime < 1e12, queryDirection={UP, DOWN, NONE}
    */

    //时间的要求
    private static double inftyTime = 1e12;

    //请求的属性
    private int targetFloor;
    private double queryTime;
    private Direction queryDirection;

    enum Direction {UP, DOWN, NONE}

    ;

    //请求的构造
    //1.楼层请求
    public Query(int target, double time, Direction direction) {
        targetFloor = target;
        queryTime = time;
        queryDirection = direction;
    }

    //2.电梯内请求
    public Query(int target, double time) {
        this(target, time, Direction.NONE);
    }

    //3.通过字符串的构造方法
    //  并在创建请求时检查合法性
    public Query(String buf) throws Throwable {
        //字符串构造：输入的通用格式
        // buf Format : (FR/ER,num,UP/DOWN/NONE,time)
        int target;
        double time;
        Direction direction;
        String[] str = buf.split("[(,)]");

        if(!(str[1].equals("FR")||str[1].equals("ER"))){
            throw new Exception("Invalid Query applicant.");
        }
        if ("ER".equals(str[1]) != "NONE".equals(str[3])) {
            // only when str[1] is "ER", str[3] is "NONE"
            //两者逻辑相同时，不报错
            throw new Exception("Invalid Character Or Format.");
        }

        //尝试将字符串 楼层 转为int型
        try {
            target = Integer.parseInt(str[2]);
        } catch (NumberFormatException except) {
            throw new Exception("Floor Number Out Of Range.");
        }

        //设定方向
        if (str[3].equals("UP")) {
            direction = Direction.UP;
        } else if (str[3].equals("DOWN")) {
            direction = Direction.DOWN;
        } else if (str[3].equals("NONE")) {
            direction = Direction.NONE;
        } else {
            throw new Exception("Invalid Character Or Format.");
        }

        //尝试将字符串 时间 转为long型
        try {
            time = Long.parseLong(str[4]);
        } catch (NumberFormatException except) {
            throw new Exception("Time Number Out Of Range.");
        }

        //实际完成初始化
        targetFloor = target;
        queryTime = time;
        queryDirection = direction;
    }

    //重写toString打印方向
    @Override
    public String toString() {
        if (queryDirection == Direction.NONE) {
            return "(ER, " + targetFloor + ", " + queryTime + ")";
        } else {
            return "(FR, " + targetFloor + ", " + queryDirection + ", " + queryTime + ")";
        }
    }

    public int getTarget() {
        return targetFloor;
    }

    public double getTime() {
        return queryTime;
    }

    public Direction getDirection() {
        return queryDirection;
    }

    public boolean repOk() {
        if (!(targetFloor <= 10 && targetFloor >= 1)) {
            return false;
        }
        if (!(queryTime >= 0 && queryTime < inftyTime)) {
            return false;
        }
        if (queryDirection != Direction.NONE && queryDirection != Direction.UP && queryDirection != Direction.DOWN) {
            return false;
        }
        return true;
    }
}
