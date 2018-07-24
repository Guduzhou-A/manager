/**
 * 
 */
package com.baicells.manager.interceptor;

import com.baicells.manager.base.properties.ProjectProperties;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestAnalyzerInterceptor extends HandlerInterceptorAdapter {

	public RequestAnalyzerInterceptor() {
	}

	public RequestAnalyzerInterceptor(ProjectProperties projectProperties) {
		this.projectProperties = projectProperties;
	}

	private ProjectProperties projectProperties;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		String[] params = request.getRequestURI().split("/");
		String base = request.getContextPath();
		if (params.length >= 2) {
			if(params.length != 2)
				request.setAttribute("requestPath", params[2]);
			request.setAttribute("base", base);

		}
		request.setAttribute("project", projectProperties);
		return true;
	}

}
