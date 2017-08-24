package org.zerock.controller;
/*스프링MVC에서 컨트롤러 설계할 때 고민해야 하는 사항
 * 1.공통적인URI경로와 각 기능별 URI
 * 2.각URI에 대한 호출방식(GET,POST)
 * 3.결과처리와 리다이렉트 방식의 페이지 결정
 * 4.예외 페이지*/

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.BoardService;

/*스프링2.5버젼 이상의 경우 컨트롤러 설계는 특별한 경우가 아니면,
 * (모듈:흔히메뉴나 기능의묶음)
 * 모듈의 갯수=컨트롤러의숫자를 제작
 * ex)회원과 게시판 모듈 필요=> 1,1=> 총두개*/

/*컨트롤러를 설계할 때 가장 중요한점!!
 *"URI를 어떤 방식으로 사용하게 할 것인가?"
 *1.외부나 다른사람에게 메신저 등으로 보낼 수 있게 하려면 반드시 GET방식
 *즉,조회가 가능하도록 만들어야 하는경우(보여주는경우)=>GET방식
 *2.POST방식<=현재 사용자가 스스로 작업하는 내용이 있는경우,
 *즉,외부에 노출되는 것이 아니라,사용자 본인이 결정해서 어떤작업이 진행되는 경우*/
@Controller
@RequestMapping("/board/*")
public class BoardController {
	//컨트롤러=하나의모듈,그것을 나타내는 대표경로를 주자
	//ex)게시판의 경우 /board or /board/
	private static final Logger logger=LoggerFactory.getLogger(BoardController.class);
	@Inject
	private BoardService service;
	/*브라우저에서 들어오는 요청이 자동으로 파라미터로 지정한 클래스의 객체속성값으로 처리(=바인딩)*/
	/*스프링MVC의 MODEL객체는 해당 메소드에서 뷰(jsp등)에 필요한 데이터를 전달하는 용도
	 * =>메소드내에서 뷰로 전달한 데이터가 있다면,MODEL을 파라미터로 선언*/
	/*GET방식:작업을 위한 페이지를 보는경우
	 *POST방식:실제로 데이터를 처리하는 경우*/
/*	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void registerGET(BoardVO vo,Model model)throws Exception{
		logger.info("register get....");
	}
	@RequestMapping(value="/listAll",method=RequestMethod.GET)
	public void listAll(Model model)throws Exception{
		logger.info("show all list......");
		model.addAttribute("list",service.listAll());
	}
	URI를 의미하는 value속성, GET/POST의 전송방식을 결정하는 method속성
	 *=>이 두 속성은 배열로 여러가지 속성값을 지정가능 필요한 경우!!!
	 *@RequestMapping(value="/register",
	 *method={RequestMethod.GET,RequestMethod.POST})
	
	//GET방식은 항상 사용자가 브라우저에서 직접 접근가능할 때 사용...
	//일반적으로는 1)입력페이지 2)조회페이지에서 사용
	
	//반면POST방식은 항상 외부에서 많은 정보를 입력하는 경우 사용...
	//브라우저상에서 주소창에 보여지만 안되는 정보를 처리!
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPOST(BoardVO vo,RedirectAttributes rttr)throws Exception{
		logger.info("register post....");
		logger.info(vo.toString());
		
		service.regist(vo);//자동으로 모든 데이터를 VO에 수집하는 부분
		rttr.addFlashAttribute("result","success");//향후 뷰로 데이터 전달 가능성을 염두하여 담는다
		//return "/board/success";//해당하는 뷰로 결과전송
		return "redirect:/board/listAll";
		//사용자가 새로고침을 이용하는 것을 방지하기 위해 자동으로 다른페이지로 이동하는 작업
		//가장 많이 쓰는 방식인 redirect 이용
		//등록이 성공한 후 바로 리다이렉트 결과 페이지로 이동하면 사용자는 post로 작성된 결과
		//페이지를 보지못한 상태에서 바로 목록페이지로 이동 보게된다
	}
	MODEL클래스는 스프링 MVC에서 제공하는 데이터 전달용 객체
	 *이는 MAP과 유사하게 KEY값/VALUE값으로 데이터를 저장하는 역할
	 *과거 Servlet에서는 RequestDispatcher에 데이터를 저장했듯이...
	 *스프링에서는 MODEL이용 데이터저장
	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno,Model model,@ModelAttribute("cri") Criteria cri)throws Exception{
		model.addAttribute(service.read(bno));
		파라미터는 외부에서 전달될 bno값을 전달받음
		 * 좀 더 명확하게 표현하기 위해서 @RequestParam을 이용해서 구성합니다
		 * 조회된 게시물을 jsp로 전달하기 위해 model객체 이용
	}
	//@RequestParam()은 servlet에서 request.getParameter()의 효과와 유사합니다
	//model.addAttribute()은 전달할때 아무 이름값을 주지않고 데이터를 전송하면
	//자동으로 클래스의 이름을 소문자로 시작해서 사용 ex)BoardVO => boardVO
	
	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno,@ModelAttribute("cri") Criteria cri,Model model)throws Exception{
		model.addAttribute(service.read(bno));
		파라미터는 외부에서 전달될 bno값을 전달받음
		 * 좀 더 명확하게 표현하기 위해서 @RequestParam을 이용해서 구성합니다
		 * 조회된 게시물을 jsp로 전달하기 위해 model객체 이용
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,
							RedirectAttributes rttr) throws Exception{
		service.remove(bno);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void modifyGET(int bno,Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPOST(BoardVO vo,RedirectAttributes rttr)throws Exception{
		logger.info("mod post......");
		service.modify(vo);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/listCri",method=RequestMethod.GET)
	public void listAll(Criteria cri,Model model)throws Exception{
		logger.info("show list Page with Criteria......");
		model.addAttribute("list",service.listCriteria(cri));
	}
	
	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri")Criteria cri,Model model)throws Exception{
		logger.info(cri.toString());
		//목록데이터를 model에 저장하는작업과
		//pageMaker를 구성해서 model에 담는 작업이 이루어짐
		model.addAttribute("list",service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		//pageMaker.setTotalCount(131);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker",pageMaker);
	}
	
	@RequestMapping(value="/removePage",method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,Criteria cri,RedirectAttributes rttr) throws Exception{
		service.remove(bno);
		rttr.addFlashAttribute("page",cri.getPage());
		rttr.addFlashAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/modifyPage",method=RequestMethod.GET)
	public void modifyGET(@RequestParam int bno,@ModelAttribute Criteria cri,Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
	public String modifyPOST(BoardVO board,Criteria cri,RedirectAttributes rttr)throws Exception{
		service.modify(board);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("msg","success");
		
		return "redirect:/board/listPage";
	}*/
	
}
