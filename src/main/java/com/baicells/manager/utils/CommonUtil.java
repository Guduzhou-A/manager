package com.baicells.manager.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.UUID;


public class CommonUtil {

	/**
	 * 生成盐
	 * 
	 * @return
	 */
	public static String getSalt() {

		return UUID.randomUUID().toString();
	}

	/**
	 * 生成密码 md5(salt+md5(md5(password+username)))
	 * 
	 * @param salt
	 * @param pw
	 * @param username
	 * @return
	 */
	public static String getPassword(String salt, String pw,
                                     String username) {
		String md5First = Md5Util.getMD5(StringUtils.trim(pw) + StringUtils.trim(username));
		String md5Second = Md5Util.getMD5(StringUtils.lowerCase(md5First));
		return Md5Util.getMD5(pw + StringUtils.lowerCase(md5Second));
	}

	

}
