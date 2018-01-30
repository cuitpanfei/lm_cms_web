package com.lm.web.service;

import com.lm.web.entity.po.UserToken;
import com.lm.web.tools.result.Ret;

public interface UserTokenService {
	
	/**
	 * 
	 *【通过用户ID查询UserToken】 
	 * @param userId
	 * @return UserToken返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月30日上午11:54:35
	 * @throws 异常
	 */
	UserToken queryByUserId(Long userId);
	
	/**
	 * 
	 *【创建token】 
	 * @param userId
	 * @return Ret返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月30日下午1:28:55
	 * @throws 异常
	 */
	Ret createToken(Long userId);
	
	
}
