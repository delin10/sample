package nil.ed.year19.month7;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/**
 * 去重或者查重
 */
public class DuplitcateProblem {
    /**
     * 两个数组求交集
     * 暴力穷举
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int len = Math.min(nums1.length, nums2.length);
        ArrayList<Integer> ls = new ArrayList<Integer>(len);
        Map<Integer, Integer> map = new HashMap<>(len);

        for (int i = 0; i < nums1.length; ++i) {
            for (int j = 0; j < nums2.length; ++j) {
                if (nums1[i] == nums2[j]) {
                    //自动装箱NPE问题
                    Integer value = map.get(nums1[i]);
                    if (value != null && j <= value) {
                        j = value;
                        continue;
                    }
                    ls.add(nums1[i]);
                    map.put(nums1[i], j);
                    break;
                }
            }
        }
        //无基本类型的toArray
        int[] arr = new int[ls.size()];
        for (int i = 0; i < ls.size(); ++i) {
            arr[i] = ls.get(i);
        }

        return arr;
    }

    /**
     * 修改数组复制
     * 更慢
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        int count = 0, len = Math.min(nums1.length, nums2.length);
        int[] arrr = new int[len];
        Map<Integer, Integer> map = new HashMap<>(len);

        for (int i = 0; i < nums1.length; ++i) {
            for (int j = 0; j < nums2.length; ++j) {
                if (nums1[i] == nums2[j]) {
                    //自动装箱NPE问题
                    Integer value = map.get(nums1[i]);
                    if (value != null && j <= value) {
                        j = value;
                        continue;
                    }
                    arrr[count++] = nums1[i];
                    map.put(nums1[i], j);
                    break;
                }
            }
        }
        //无基本类型的toArray
        int[] arr = new int[count];
        System.arraycopy(arrr, 0, arr, 0, count);
        return arr;
    }

    /**
     * 先排序
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int minLen = Math.min(nums1.length, nums2.length);
        int[] buf = new int[minLen];

        if (nums1[0] > nums2[nums2.length - 1] || nums2[0] > nums1[nums1.length - 1]) {
            return new int[0];
        }

        int i = 0, j = 0, index = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                buf[index++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] result = new int[index];
        for (int k = 0; k < index; ++k) {
            result[i] = buf[k];
        }
        return result;
    }

    /**
     * 数组加法
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int mod = 1, faction = 0;

        for (int i = digits.length - 1; i >= 0; --i) {
            faction = digits[i] + mod;
            mod = faction / 10;
            digits[i] = faction % 10;
        }
        //需要增加位数的情况
        if (mod != 0) {
            int[] result = new int[digits.length + 1];
            result[0] = mod;
            //加一绝对不会出现个位数为非零的情况
//            for (int i = 1; i < result.length; ++i) {
//                result[i] = digits[i - 1];
//            }
            return result;
        }
        return digits;
    }
}
