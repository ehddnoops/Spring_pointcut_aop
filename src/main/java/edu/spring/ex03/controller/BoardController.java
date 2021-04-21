package edu.spring.ex03.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.ex03.domain.BoardVO;
import edu.spring.ex03.pageutil.PageCriteria;
import edu.spring.ex03.pageutil.PageMaker;
import edu.spring.ex03.service.BoardService;

@Controller
@RequestMapping(value="/board") // ex02/board/
public class BoardController {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer perPage) {
		LOGGER.info("list() ȣ��");
		LOGGER.info("page = " + page + ", perPage = " + perPage);
		
		// Paging ó��
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
			
		} 
		if(perPage != null) {
			criteria.setNumsPerPage(perPage);
		}
		
		List<BoardVO> list = boardService.read(criteria);
		model.addAttribute("list", list);
		
		
		PageMaker maker = new PageMaker();
		maker.setCriteria(criteria);
		maker.setTotalCount(boardService.getTotalNumsOfRecords());
		maker.setPageData();
		model.addAttribute("pageMaker", maker);
		
	}//end list()

	@GetMapping("/register")
	public void registerGET() {
		LOGGER.info("registerGET() ȣ��");
		
	}//end registerGET()
	
	@PostMapping("/register")
	public String registerPOST(BoardVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - ���� ��ġ�� �Ӽ����� �����ϴ� ��ü
		
		LOGGER.info("registerPOST() ȣ��");
		LOGGER.info(vo.toString());
		int result = boardService.create(vo);
		LOGGER.info(result + "�����");
		if(result == 1) {
			// "insert_result"�� Ű�̸��� ���� ������ ����
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/board/list"; // /board/list ��η� �̵�. get���
		} else {
			reAttr.addFlashAttribute("insert_result", "fail");
			return "redirect:/board/list"; // /board/list ��η� �̵�. get���
		}
	}
	
	@GetMapping("/detail")
	public void detail(Model model, Integer bno ,Integer page) {
		LOGGER.info("detail() ȣ�� bno = " + bno +"dddd"+ page);
		BoardVO vo = boardService.read(bno);
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
	}
	
	@GetMapping("/update")
	public void updateGET(Model model, Integer bno, Integer page) {
		LOGGER.info("update() ȣ�� : bno = " + bno);
		BoardVO vo = boardService.read(bno);
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
	}
	
	@PostMapping("/update")
	public String updatePOST(BoardVO vo, int page) {
		LOGGER.info("updatePOST() ȣ�� : bno = " + vo.getBno());
		int result = boardService.update(vo);
		if(result == 1) {
			return "redirect:/board/list?page=" + page;
		} else {
			return "redirect:/board/update?bno=" + vo.getBno();
		}
	}
	
	@GetMapping("/delete")
	public String delete(Integer bno) {
		LOGGER.info("delete() ȣ�� : bno = " + bno);
		int result = boardService.delete(bno);
		if(result == 1) {
			return "redirect:/board/list";
		} else {
			return "redirect:/board/list";
		}
	}
}


















