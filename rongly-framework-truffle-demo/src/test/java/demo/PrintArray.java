package demo;

import com.vip.vjtools.vjkit.base.ObjectUtil;
import com.vip.vjtools.vjkit.collection.ArrayUtil;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/12/27 18:19
 * @Version: 1.0
 * modified by:
 */
public class PrintArray {
    public static void main(String[] args) {
        System.out.println( ObjectUtil.toPrettyString(double.class.getDeclaredFields()));
    }
}
