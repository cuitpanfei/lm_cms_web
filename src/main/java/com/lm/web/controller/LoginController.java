package com.lm.web.controller;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lm.web.configuration.log.GwsLogger;
import com.lm.web.entity.po.User;
import com.lm.web.entity.vo.LoginVO;
import com.lm.web.service.UserService;
import com.lm.web.service.UserTokenService;
import com.lm.web.tools.constant.CommConstant;
import com.lm.web.tools.result.Ret;

/**
 * 
 * (登录控制器)
 * 
 * @ClassName LoginController
 * @author ShenZiYang
 * @date 2017年12月19日 上午9:35:29
 */

@RestController
public class LoginController extends AbstractController {
	@Autowired
	UserService userService;
	@Autowired
	UserTokenService userTokenService;
	

	/**
	 * 
	 * 【用户登录】
	 * 
	 * @Title login
	 * @param userName
	 * @param password
	 * @return Ret返回类型
	 * @author ShenZiYang
	 * @date 2018年1月21日下午3:48:25
	 * @throws 异常
	 */
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public Ret login(LoginVO login) {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		Long startTime = System.currentTimeMillis();
		GwsLogger.info("用户登录开始:code={},message={},startTime={}", code, message, startTime);
		
		User user = userService.queryByUserName(login.getUsername());
		// 账号不存在、密码错误
		Sha256Hash sha256 = new Sha256Hash(login.getPassword(), user.getSalt());
		String pwd = sha256.toHex();
//		if (user == null || !user.getPassword().equals(new Sha256Hash(login.getPassword(), user.getSalt()).toHex())) {
//			return Ret.error("账号或密码不正确");
//		}
		
		if (user == null || !user.getPassword().equals(pwd)) {
			return Ret.error("账号或密码不正确");
		}
		
		// 账号锁定
		if (user.getStatus() == 0) {
			return Ret.error("账号已被锁定,请联系管理员");
		}
		
		Ret r = null;
		try{
			//生成token，并保存到数据库
			r = userTokenService.createToken(user.getUserId());	
		}catch(Exception e){
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("用户登录异常:code={},message={},e={}", code, message, e);
		}

		Long endTime = System.currentTimeMillis() - startTime;
		GwsLogger.info("用户登录结束:code={},message={},endTime={}", code, message, endTime);
		return r;
	}

	/**
	 * 
	 * 【退出登录】
	 * 
	 * @return String返回类型
	 * @author ShenZiYang
	 * @date 2018年1月19日上午10:18:26
	 * @throws 异常
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("退出系统开始:code={},message={}", code, message);

		try {
			//ShiroUtils.logout();
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("退出系统异常:code={},message={},e={}", code, message, e);
		}

		GwsLogger.info("退出系统结束:code={},message={}", code, message);
		return null;
	}

}
