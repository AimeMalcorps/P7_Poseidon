package com.nnk.springboot.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CustomRequestInterceptor implements HandlerInterceptor {
	
//	private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);
//
//	 @Override
//	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//
//	  long startTime = Instant.now().toEpochMilli();
//	  logger.info("Request URL::" + request.getRequestURL().toString() +
//	   ":: Start Time=" + Instant.now());
//	  request.setAttribute("startTime", startTime);
//	  return true;
//	 }
//
//	 @Override
//	 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//
//	  long startTime = (Long) request.getAttribute("startTime");
//
//	  logger.info("Request URL::" + request.getRequestURL().toString() +
//	   ":: Time Taken=" + (Instant.now().toEpochMilli() - startTime));
//	 }

}