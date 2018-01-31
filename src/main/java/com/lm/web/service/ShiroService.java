package com.lm.web.service;


import java.util.Set;

import com.lm.web.entity.po.User;
import com.lm.web.entity.po.UserToken;

/**
 * 
 *【shiro相关接口】  
 * @author ShenZiYang 
 * @date 2018年1月31日 上午9:19:18
 */
public interface ShiroService {
	
	/**
	 * 
	 *【根据用户ID获取用户权限列表】 
	 * @param userId
	 * @return Set<String>返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月31日上午9:19:42
	 * @throws 异常
	 */
    Set<String> getUserPermissions(Long userId);
    
    /**
     * 
     *【根据token获取用户token】 
     * @param token
     * @return UserToken返回类型   
     * @author ShenZiYang
     * @date 2018年1月31日上午9:21:45
     * @throws 异常
     */
    UserToken queryByToken(String token);

    /**
     * 
     *【根据用户ID，查询用户】 
     * @param userId
     * @return User返回类型   
     * @author ShenZiYang
     * @date 2018年1月31日上午9:22:15
     * @throws 异常
     */
    User queryUser(Long userId);
}
