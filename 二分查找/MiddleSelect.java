/**
 * @author zhangyangyang
 * @version 1.0
 * @date 2020/11/5 14:28
 */
public class MiddleSelect {
    public static int middleSearch(int[] arr,int target){
        int start=0;
        int end=arr.length-1;
        int middleNum;
        while (start<=end){
            middleNum=(start+end)/2;
            if(target==arr[middleNum]){
                return middleNum;
            }else if(target>arr[middleNum]){
                start = middleNum+1;
            }else {
                end = middleNum-1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 6;
        System.out.println(middleSearch(arr, target));
    }
}
