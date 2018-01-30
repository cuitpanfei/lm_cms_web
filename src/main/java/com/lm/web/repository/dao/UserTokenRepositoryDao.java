package com.lm.web.repository.dao;

import com.lm.web.configuration.query.core.BaseRepository;
import com.lm.web.entity.po.UserToken;

public interface UserTokenRepositoryDao extends BaseRepository<UserToken, Long>{
	

	/**
	 * 
	 *【通过token查询UserToken】 
	 * @param token
	 * @return UserToken返回类型   
	 * @author ShenZiYang
	 * @date 2018年1月30日上午11:54:56
	 * @throws 异常
	 */
	UserToken queryByToken(String token);
	
	
}
