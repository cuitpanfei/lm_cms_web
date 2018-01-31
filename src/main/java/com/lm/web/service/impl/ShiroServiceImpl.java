package com.lm.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lm.web.entity.po.Menu;
import com.lm.web.entity.po.User;
import com.lm.web.entity.po.UserToken;
import com.lm.web.repository.dao.MenuRepositoryDao;
import com.lm.web.repository.dao.UserRepositoryDao;
import com.lm.web.repository.dao.UserTokenRepositoryDao;
import com.lm.web.service.ShiroService;
import com.lm.web.tools.constant.Constant;

@Service
public class ShiroServiceImpl implements ShiroService {
	
	@Autowired
	private MenuRepositoryDao menuRepositoryDao;
	@Autowired
	private UserTokenRepositoryDao userTokenRepositoryDao;
	@Autowired
	private UserRepositoryDao userRepositoryDao;
	
	@Override
	public Set<String> getUserPermissions(Long userId) {
		List<String> permsList = null;
		
		if (userId == Constant.SUPER_ADMIN) {
			List<Menu> menuList = menuRepositoryDao.findAll(); //查询所有的菜单
			permsList = new ArrayList<>();
			for (Menu menu : menuList) {
				//遍历所有的菜单,存入到集合
				permsList.add(menu.getPerms());
			}
			
		}else{
			//如果不是系统管理员,根据用户ID查询所拥有的菜单
			permsList = userRepositoryDao.getPermsByUser(userId);
		}
		
		// 用户权限列表  （HashSet存value不能重复的对象)
		Set<String> permsSet = new HashSet<String>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			
			//将集合中的数据按,号分割，并转成数组存入HashSet中
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		
		return permsSet;
	}

	@Override
	public UserToken queryByToken(String token) {
		return userTokenRepositoryDao.queryByToken(token);
	}

	@Override
	public User queryUser(Long userId) {
		return userRepositoryDao.findOne(userId);
	}
	
	
}
