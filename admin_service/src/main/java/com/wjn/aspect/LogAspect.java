package com.wjn.aspect;

import com.wjn.bean.validator.LoginUser;
import com.wjn.constant.NaturalNumber;
import com.wjn.mapper.UserLoginLogMapper;
import com.wjn.model.admin.User;
import com.wjn.model.admin.UserLoginLog;
import com.wjn.utils.HttpContextUtils;
import com.wjn.utils.IPUtils;
import com.wjn.utils.ShiroUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Aspect
@Component
public class LogAspect {

	@Autowired
	private UserLoginLogMapper userLoginLogMapper;

	@Pointcut("@annotation(com.wjn.annotation.LoginLog)")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint point){
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = null;
		long time;
		try {
			result = point.proceed();
			time = System.currentTimeMillis() - beginTime;

		} catch (Throwable throwable) {
			time = System.currentTimeMillis() - beginTime;
			saveLog(point, time,false);
		}
		saveLog(point, time,true);
		return result;
	}

	private void saveLog(ProceedingJoinPoint joinPoint, long time,boolean flag) {
		UserLoginLog userLoginLog = new UserLoginLog();
		if(flag){
			User user = ShiroUtil.getUser();
			userLoginLog.setUsername(user.getUsername());
			userLoginLog.setName(user.getName());
			userLoginLog.setDescription("登录成功");
		}else {
			Object[] paramValues = joinPoint.getArgs();
			LoginUser loginUser = (LoginUser)paramValues[NaturalNumber.zero];
			userLoginLog.setUsername(loginUser.getUsername());
			userLoginLog.setDescription("登录失败");
		}

		// 获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		// 设置IP地址
		userLoginLog.setIp(IPUtils.getIpAddr(request));
		userLoginLog.setTime((int) time);
		Date date = new Date();
		userLoginLog.setCreateTime(date);
		// 保存系统日志
		userLoginLogMapper.insertSelective(userLoginLog);
	}
}
