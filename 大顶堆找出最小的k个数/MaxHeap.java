package dao;

import java.util.ArrayList;

/**
 * @author zhangyangyang
 * @version 1.0
 * @date 2020/10/27 16:48
 */
public class MaxHeap {
    /**
     * 维护这个堆，使其成为一个大顶堆
     * @param index 下标
     * @param arr   大顶堆数组
     */
    private static void initHeap(int index, int[] arr) {
        //把当前下标的元素赋值给temp
        int temp = arr[index];
        //关键代码部分：比较当前节点的左右子路的值，把最大值和当前元素，再比较，找出最大值，放在当前下标index的位置上
        for (int i = index * 2 + 1; i < arr.length; i = i * 2 + 1) {//比较左右节点的大小，控制数据不要下标越界
            if (i + 1 < arr.length && arr[i] < arr[i + 1]) {
                i++;
            }
            //把左右子路中最大的元素的值和此元素的值比较,最大的值和此元素互换位置
            if (arr[i] > temp) {
                arr[index] = arr[i];
                index = i;
            } else {
                break;
            }
        }
        arr[index] = temp;
    }

    /**
     * @param input user输入的数组
     * @param k     k个最小的数
     * @return
     */
    private static ArrayList<Integer> GetNumbers(int[] input, int k) {
        if (k > input.length || k == 0) {
            //输入错误，返回空数组
            return new ArrayList<>();
        }
        //大堆顶，是二叉树的数据结构，底层也是数据，heap堆就是我们需要维护的大顶堆
        int[] heap = new int[k];
        //把输入的input数组的前k个元素，复制到head数组中，（参数说明，把input数组从0下标开始复制k个元素给heap数组）
        System.arraycopy(input, 0, heap, 0, k);
        //i是非叶子节点的下标（n/2 -1 ）
        for (int i = k / 2 - 1; i >= 0; i--) {
            initHeap(i, heap);
        }
        for (int i = k; i < input.length; i++) {
            //维护大顶堆其实也就是维护head[0]这个元素
            if (heap[0] > input[i]) {
                heap[0] = input[i];
                initHeap(0, heap);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int value : heap) {
            list.add(value);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] i = new int[]{3, 4, 1, 66, 33, 22, 1, 2, 4, 6, 4};
        ArrayList<Integer> integers = GetNumbers(i, 6);
        System.out.println(integers);
    }
}
