package org.zerock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*이 주석을 통해 클래스의 객체가 컨트롤러에서 발생하는 exception을 전문적으로 처리하는 클래스로 설정*/
@ControllerAdvice
public class CommonExceptionAdvice {
	private static final Logger logger=
			LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
/*	이 메소드를 통해 exception 타입으로 처리되는 모든예외를 처리하도록 설정
	@ExceptionHandler(Exception.class)
	public String common(Exception e){
		이 클래스의 메소드는 발생한 exception 객체의 타입만을
		 * 파라미터로 사용가능, 일반 컨트롤러와 같이 model을 파라미터로 사용하는 것은
		 * 지원하지 않기 때문, 직접 ModelAndView타입을 사용하는 형태로 작성해야한다
		 * ModelAndView는 하나의 객체에 MODEL 데이터와 VIEW의 처리를 동시에 가능한 객체
		 * 이것은 주로 지정된 파라미터를 사용하는 경우에 주로사용
		logger.info(e.toString());
		return "error_common";
	}*/
	
	@ExceptionHandler(Exception.class)
	private ModelAndView errorModeAndView(Exception ex){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("error_common");
		modelAndView.addObject("exception",ex);
		
		return modelAndView;
	}
}
