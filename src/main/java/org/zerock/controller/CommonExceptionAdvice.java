package org.zerock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*�� �ּ��� ���� Ŭ������ ��ü�� ��Ʈ�ѷ����� �߻��ϴ� exception�� ���������� ó���ϴ� Ŭ������ ����*/
@ControllerAdvice
public class CommonExceptionAdvice {
	private static final Logger logger=
			LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
/*	�� �޼ҵ带 ���� exception Ÿ������ ó���Ǵ� ��翹�ܸ� ó���ϵ��� ����
	@ExceptionHandler(Exception.class)
	public String common(Exception e){
		�� Ŭ������ �޼ҵ�� �߻��� exception ��ü�� Ÿ�Ը���
		 * �Ķ���ͷ� ��밡��, �Ϲ� ��Ʈ�ѷ��� ���� model�� �Ķ���ͷ� ����ϴ� ����
		 * �������� �ʱ� ����, ���� ModelAndViewŸ���� ����ϴ� ���·� �ۼ��ؾ��Ѵ�
		 * ModelAndView�� �ϳ��� ��ü�� MODEL �����Ϳ� VIEW�� ó���� ���ÿ� ������ ��ü
		 * �̰��� �ַ� ������ �Ķ���͸� ����ϴ� ��쿡 �ַλ��
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
