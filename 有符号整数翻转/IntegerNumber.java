
/**
 * @author zhangyangyang
 * @version 1.0
 * @date 2020/10/27 9:41
 */

public class IntegerNumber {
    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * 示例 1:
     *
     * 输入: 123
     * 输出: 321
     *  示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     * @param x
     * @return
     */
    public int reverse(int x) {
       long flag=0;
       while (x!=0){
           flag=flag*10+x%10;
           x=x/10;
       }
       return flag<Integer.MIN_VALUE||flag>Integer.MAX_VALUE?0: (int) flag;
    }
}
