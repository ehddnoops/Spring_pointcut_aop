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
// /replies (POST) : 댓글 추가(insert)
// /replies/all/숫자 (GET) : 해당 글번호(replyBno)의 모든 댓글 검색(select)
// /replies/숫자 (PUT) : 해당 댓글 번호(replyNo)의 내용을 수정(update)
// /replies/숫자 (DELETE) : 해당 댓글 번호(replyNo)의 댓글 삭제(delete)
@RestController
@RequestMapping(value="/replies")
public class ReplyRESTController {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(ReplyRESTController.class);
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping // POST : 댓글 입력
	public ResponseEntity<Integer> createReply(@RequestBody ReplyVO vo){
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를 자바 객체로 변환해주는 annotation
		LOGGER.info(vo.toString());
		int result;
		try {
			result = replyService.create(vo);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all/{replyBno}") // GET : 댓글 선택(all)
	public ResponseEntity<List<ReplyVO>> readReplies(
			@PathVariable("replyBno") int replyBno){
		// @PathVariable("replyBno") : /all/{replyBno}값을 설정된 변수에 저장
		List<ReplyVO> list = replyService.read(replyBno);
		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
	}
	
	
	@PutMapping("/{replyNo}") // PUT : 댓글 수정
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
