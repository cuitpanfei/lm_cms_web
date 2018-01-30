package com.lm.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lm.web.entity.po.UserToken;
import com.lm.web.repository.dao.UserTokenRepositoryDao;
import com.lm.web.service.UserTokenService;
import com.lm.web.tools.result.Ret;


@Service("userTokenService")
public class UserTokenServiceImpl implements UserTokenService{
	
	@Autowired
	private UserTokenRepositoryDao userTokenRepositoryDao;
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;
	
	
	@Override
	public UserToken queryByUserId(Long userId) {
		return userTokenRepositoryDao.findOne(userId);
	}

	
	@Override
	public Ret createToken(Long userId) {
		//生成token
		//String token = TokenGenerator.generateValue();
		String token = null;
		
		//过期时间
		Date expireTime = new Date(new Date().getTime() + EXPIRE * 1000);
		
		//判断是否生成token
		UserToken userToken = userTokenRepositoryDao.findOne(userId);
		if(userToken == null){
			userToken.setUserId(userId);
			userToken.setToken(token);
			userToken.setExpireTime(expireTime);
			userToken.setUpdateTime(new Date());
			userTokenRepositoryDao.save(userToken);
		}else{
			userToken.setToken(token);
			userToken.setUpdateTime(new Date());
			userToken.setExpireTime(expireTime);
			userTokenRepositoryDao.save(userToken);
		}
		Ret r = Ret.ok().put("token", token).put("expire", EXPIRE);
		return r;
	}

	
	
	
}
