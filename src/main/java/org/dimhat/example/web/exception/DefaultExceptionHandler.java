package org.dimhat.example.web.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 通用异常处理
 * 应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行
 * 
 * 配置在spring-mvc.xml中，没有跟controller一起扫描到
 * 
 * @author dimhat
 * @date 2015年12月4日 下午2:02:04
 * @version 1.0
 */
@ControllerAdvice
public class DefaultExceptionHandler {

	/**
	 * 没有权限 异常
	 * <p/>
	 * nested exception is org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [user:view]] with root cause
	 * 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)//返回指定的http状态码
	public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		System.out.println("未授权异常，没有权限访问该资源");
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("exception", e);
		mv.setViewName("unauthorized");
		return mv;
	}
	
}
