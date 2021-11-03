package com.exam.admin.util;



import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;


/**
 * 随机数工具类
 *
 * @see ProbUtil
 * @since
 */
public final class ProbUtil {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        //输入满减的上限
        int discount=input.nextInt();
        //设置各个满减阶段的概率，这里显示将折扣设置为2等分
        Map<Integer,Double> map=new HashMap<>();
        map.put(discount/2,90.00);
        map.put(discount,10.00);
        Integer result=getRand(map);
        //随机获取折扣范围内的金额
        Random random=new Random();
        double money=0;
        if (result==discount/2){
             money=random.nextDouble()*(discount/2-0.01)+0.01;
        }else{
            money=random.nextDouble()*(discount/2)+discount/2;
        }
        //保留两位小数
        DecimalFormat df   = new DecimalFormat("######0.00");
        System.out.println(df.format(money));
//        测试概率是不是符合，通过a，b的次数可以查看得到
//        Integer a = 0, b = 0;
//        for (int j = 0; j < 10000; j++) {
//            Map<Integer, Double> m = new HashMap<Integer, Double>();
//            m.put(10, 90.0);
//            m.put(20, 10.0);
//            Integer result = getRand(m);
//            switch (result) {
//                case 10:
//                    a++;
//                    break;
//                case 20:
//                    b++;
//                    break;
//            }
//        }
//        System.out.println("1的次数：" + a + "     2的次数：" + b );
    }
    private final static Random random = new Random();

    /**
     * 获取概率事件
     */
    public static final <T> T getRand(Map<T, Double> map) {
        // 放大位数
        int multiple = 1000;
        // 求和
        int sum = 0;
        Iterator<Entry<T, Double>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<T, Double> entry = iter.next();
            Double v = entry.getValue();
            sum += v * multiple;
        }
        if (sum <= 0) {
            return null;
        }
        // 产生0-sum的整数随机
        int luckNum = random.nextInt(sum) + 1;
        int tmp = 0;
        iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<T, Double> entry = iter.next();
            Double v = entry.getValue();
            tmp += v * multiple;
            if (luckNum <= tmp) {
                return entry.getKey();
            }
        }
        return null;
    }
}