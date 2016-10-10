package com.lanxi.elegift.bean.in;

import java.io.Serializable;
/**
 * 枚举类,用于 从reqbean中获取参数时是否需要获取签名
 * @author 1
 *
 */
public enum MapType implements Serializable {
	 STANDARD, // 标准类型
     SIGN// 需要签名的类型
}
