package org.zerock.controller;
/*������MVC���� ��Ʈ�ѷ� ������ �� ����ؾ� �ϴ� ����
 * 1.��������URI��ο� �� ��ɺ� URI
 * 2.��URI�� ���� ȣ����(GET,POST)
 * 3.���ó���� �����̷�Ʈ ����� ������ ����
 * 4.���� ������*/

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.BoardService;

/*������2.5���� �̻��� ��� ��Ʈ�ѷ� ����� Ư���� ��찡 �ƴϸ�,
 * (���:�����޴��� ����ǹ���)
 * ����� ����=��Ʈ�ѷ��Ǽ��ڸ� ����
 * ex)ȸ���� �Խ��� ��� �ʿ�=> 1,1=> �ѵΰ�*/

/*��Ʈ�ѷ��� ������ �� ���� �߿�����!!
 *"URI�� � ������� ����ϰ� �� ���ΰ�?"
 *1.�ܺγ� �ٸ�������� �޽��� ������ ���� �� �ְ� �Ϸ��� �ݵ�� GET���
 *��,��ȸ�� �����ϵ��� ������ �ϴ°��(�����ִ°��)=>GET���
 *2.POST���<=���� ����ڰ� ������ �۾��ϴ� ������ �ִ°��,
 *��,�ܺο� ����Ǵ� ���� �ƴ϶�,����� ������ �����ؼ� ��۾��� ����Ǵ� ���*/
@Controller
@RequestMapping("/board/*")
public class BoardController {
	//��Ʈ�ѷ�=�ϳ��Ǹ��,�װ��� ��Ÿ���� ��ǥ��θ� ����
	//ex)�Խ����� ��� /board or /board/
	private static final Logger logger=LoggerFactory.getLogger(BoardController.class);
	@Inject
	private BoardService service;
	/*���������� ������ ��û�� �ڵ����� �Ķ���ͷ� ������ Ŭ������ ��ü�Ӽ������� ó��(=���ε�)*/
	/*������MVC�� MODEL��ü�� �ش� �޼ҵ忡�� ��(jsp��)�� �ʿ��� �����͸� �����ϴ� �뵵
	 * =>�޼ҵ峻���� ��� ������ �����Ͱ� �ִٸ�,MODEL�� �Ķ���ͷ� ����*/
	/*GET���:�۾��� ���� �������� ���°��
	 *POST���:������ �����͸� ó���ϴ� ���*/
/*	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void registerGET(BoardVO vo,Model model)throws Exception{
		logger.info("register get....");
	}
	@RequestMapping(value="/listAll",method=RequestMethod.GET)
	public void listAll(Model model)throws Exception{
		logger.info("show all list......");
		model.addAttribute("list",service.listAll());
	}
	URI�� �ǹ��ϴ� value�Ӽ�, GET/POST�� ���۹���� �����ϴ� method�Ӽ�
	 *=>�� �� �Ӽ��� �迭�� �������� �Ӽ����� �������� �ʿ��� ���!!!
	 *@RequestMapping(value="/register",
	 *method={RequestMethod.GET,RequestMethod.POST})
	
	//GET����� �׻� ����ڰ� ���������� ���� ���ٰ����� �� ���...
	//�Ϲ������δ� 1)�Է������� 2)��ȸ���������� ���
	
	//�ݸ�POST����� �׻� �ܺο��� ���� ������ �Է��ϴ� ��� ���...
	//�������󿡼� �ּ�â�� �������� �ȵǴ� ������ ó��!
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPOST(BoardVO vo,RedirectAttributes rttr)throws Exception{
		logger.info("register post....");
		logger.info(vo.toString());
		
		service.regist(vo);//�ڵ����� ��� �����͸� VO�� �����ϴ� �κ�
		rttr.addFlashAttribute("result","success");//���� ��� ������ ���� ���ɼ��� �����Ͽ� ��´�
		//return "/board/success";//�ش��ϴ� ��� �������
		return "redirect:/board/listAll";
		//����ڰ� ���ΰ�ħ�� �̿��ϴ� ���� �����ϱ� ���� �ڵ����� �ٸ��������� �̵��ϴ� �۾�
		//���� ���� ���� ����� redirect �̿�
		//����� ������ �� �ٷ� �����̷�Ʈ ��� �������� �̵��ϸ� ����ڴ� post�� �ۼ��� ���
		//�������� �������� ���¿��� �ٷ� ����������� �̵� ���Եȴ�
	}
	MODELŬ������ ������ MVC���� �����ϴ� ������ ���޿� ��ü
	 *�̴� MAP�� �����ϰ� KEY��/VALUE������ �����͸� �����ϴ� ����
	 *���� Servlet������ RequestDispatcher�� �����͸� �����ߵ���...
	 *������������ MODEL�̿� ����������
	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno,Model model,@ModelAttribute("cri") Criteria cri)throws Exception{
		model.addAttribute(service.read(bno));
		�Ķ���ʹ� �ܺο��� ���޵� bno���� ���޹���
		 * �� �� ��Ȯ�ϰ� ǥ���ϱ� ���ؼ� @RequestParam�� �̿��ؼ� �����մϴ�
		 * ��ȸ�� �Խù��� jsp�� �����ϱ� ���� model��ü �̿�
	}
	//@RequestParam()�� servlet���� request.getParameter()�� ȿ���� �����մϴ�
	//model.addAttribute()�� �����Ҷ� �ƹ� �̸����� �����ʰ� �����͸� �����ϸ�
	//�ڵ����� Ŭ������ �̸��� �ҹ��ڷ� �����ؼ� ��� ex)BoardVO => boardVO
	
	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno,@ModelAttribute("cri") Criteria cri,Model model)throws Exception{
		model.addAttribute(service.read(bno));
		�Ķ���ʹ� �ܺο��� ���޵� bno���� ���޹���
		 * �� �� ��Ȯ�ϰ� ǥ���ϱ� ���ؼ� @RequestParam�� �̿��ؼ� �����մϴ�
		 * ��ȸ�� �Խù��� jsp�� �����ϱ� ���� model��ü �̿�
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
		//��ϵ����͸� model�� �����ϴ��۾���
		//pageMaker�� �����ؼ� model�� ��� �۾��� �̷����
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
