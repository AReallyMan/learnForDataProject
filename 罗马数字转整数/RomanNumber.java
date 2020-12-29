package dao;

import java.util.*;

/**
 * @author zhangyangyang
 * @version 1.0
 * @date 2020/10/27 11:40
 */
public class RomanNumber {
    /**
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<String,Integer> map=new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        map.put("A",4);
        map.put("B",9);
        map.put("Q",40);
        map.put("P",90);
        map.put("E",400);
        map.put("F",900);
        s=s.replace("IV","A").
                replace("IX","B").
                replace("XL","Q").
                replace("XC","P").
                replace("CD","E").
                replace("CM","F");
        Integer result=0;
        for(int i=0;i<s.length();i++){
            //String temp=s.substring(i,i+1);
            String temp=String.valueOf(s.charAt(i));
            result+=map.get(temp);
        }
        return result;
    }
}
