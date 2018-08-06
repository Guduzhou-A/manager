/**
 * 
 */
package com.baicells.manager.interceptor;


import com.baicells.manager.model.entity.User;
import com.baicells.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor extends HandlerInterceptorAdapter {

	public LoginInterceptor() {
	}

	@Autowired
	private UserService userServiceImpl;

	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		Object object = request.getSession().getAttribute("currentUser");
		String loginPath = request.getContextPath() +"/login";return true;
//		if (object != null){
//			try {
//				User user =  (User) object;
//				if (userServiceImpl.getById(user.getId())!=null){
//					return true;
//				}else{
//					response.sendRedirect(loginPath);
//					return false;
//				}
//			}catch (Exception e){
//				response.sendRedirect(loginPath);
//				return false;
//			}
//
//		}else{
//			response.sendRedirect(loginPath);
//			return false;
//		}
	}

}
