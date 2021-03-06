package sort.java;

import java.util.Random;

//快速排序
public class QuickSort {

    public static void quickSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        _quickSort(nums, 0, nums.length - 1);
    }

    public static void _quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int index = partition1(nums, start, end);
//        int index = partition2(nums, start, end);
//        int index = partition3(nums, start, end);
        if (index == -1) return;
        _quickSort(nums, start, index - 1);
        _quickSort(nums, index + 1, end);
    }

    //前后指针法(滑动窗口)
    //思路是：选定中枢点(假设是末尾),前指针指向头，后指针=前指针-1
    //前指针向后扫描比中枢值小的值，找到了就让后指针+1，并交换前后指针的值
    //当前指针扫描结束后，后指针+1，并交换后指针和中枢点，此时后指针的值就是划分点的位置。
    public static int partition1(int[] nums, int start, int end) {
        if (start < 0 || end >= nums.length || start >= end) return -1;
//        int index = randomInRange(start, end);
//        swap(nums, index, end);
        int pivot = nums[end];
        int previous = start - 1;
        for (int current = start; current < end; current++) {
            if (nums[current] < pivot) {
                previous++;
                if (previous != current) swap(nums, current, previous);
            }
        }
        //previous一开始是-1，所以最后要+1.如果pivot是开头元素，那么就不需要
        previous++;
        swap(nums, previous, end);
        return previous;
    }

    //对撞指针的思路(左右指针)
    //思路是：选定中枢点(假设是末尾)，左右指针分别从头，尾开始扫描，
    //左指针扫描到比中枢大的值，就交换(左<->右)，右指针扫描到比中枢小的值，就交换(左<->右)
    //最终相遇于划分点,交换划分点和中枢点
    public static int partition2(int[] nums, int start, int end) {
        if (start < 0 || end >= nums.length || start >= end) return -1;
        int left = start, right = end, pivot = nums[end];
        while (left < right) {
            while (left < right && nums[left] <= pivot) left++;
            while (left < right && nums[right] >= pivot) right--;
            if (left < right) swap(nums, left, right);
        }
        swap(nums, left, end);
        return left;
    }

    //挖坑法
    //思路是：选择中枢点(假设是末尾)，作为坑点，随着左右指针的交换而变动位置，最后坑点的位置就是划分点
    public static int partition3(int[] nums, int start, int end) {
        if (start < 0 || end >= nums.length || start >= end) return -1;
        int pivot = nums[end];
        while (start < end) {
            while (start < end && nums[start] <= pivot) start++;
            if (start < end) swap(nums, start, end);
            while (start < end && nums[end] >= pivot) end--;
            if (start < end) swap(nums, start, end);
        }
        return start;
    }

    private static int randomInRange(int start, int end) {
        Random random = new Random();
        return random.nextInt((end) % (end - start + 1) + start);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
