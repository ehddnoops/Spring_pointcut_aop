package edu.spring.ex03.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.service.ReplyService;
import oracle.jdbc.proxy.annotation.Post;

// RequestMapping
// /replies (POST) : ��� �߰�(insert)
// /replies/all/���� (GET) : �ش� �۹�ȣ(replyBno)�� ��� ��� �˻�(select)
// /replies/���� (PUT) : �ش� ��� ��ȣ(replyNo)�� ������ ����(update)
// /replies/���� (DELETE) : �ش� ��� ��ȣ(replyNo)�� ��� ����(delete)
@RestController
@RequestMapping(value="/replies")
public class ReplyRESTController {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(ReplyRESTController.class);
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping // POST : ��� �Է�
	public ResponseEntity<Integer> createReply(@RequestBody ReplyVO vo){
		// @RequestBody
		// - Ŭ���̾�Ʈ���� ���۹��� json �����͸� �ڹ� ��ü�� ��ȯ���ִ� annotation
		LOGGER.info(vo.toString());
		int result;
		try {
			result = replyService.create(vo);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all/{replyBno}") // GET : ��� ����(all)
	public ResponseEntity<List<ReplyVO>> readReplies(
			@PathVariable("replyBno") int replyBno){
		// @PathVariable("replyBno") : /all/{replyBno}���� ������ ������ ����
		List<ReplyVO> list = replyService.read(replyBno);
		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
	}
	
	
	@PutMapping("/{replyNo}") // PUT : ��� ����
	public ResponseEntity<String> updateReply(
			@PathVariable("replyNo") int replyNo,
			@RequestBody ReplyVO vo
			){
			vo.setReplyNo(replyNo);
			LOGGER.info("replyBno = " + vo.getReplyBno());
			int result = replyService.update(vo);
			ResponseEntity<String> entity = null;
			if(result == 1) {
				entity = new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
			return entity;
	}
	
	@DeleteMapping("/{replyNo}")
	public ResponseEntity<String> deleteReply(
			@PathVariable("replyNo") int replyNo,
			@RequestBody ReplyVO vo){
		LOGGER.info("replyBno = " + vo.getReplyBno());
		
		try {
			replyService.delete(replyNo, vo.getReplyBno());
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}
	}
}
