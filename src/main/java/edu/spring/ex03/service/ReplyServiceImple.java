package edu.spring.ex03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.persistence.ReplyDAO;

@Service
public class ReplyServiceImple implements ReplyService{
	private static final Logger LOGGER=
			LoggerFactory.getLogger(ReplyServiceImple.class);
	
	@Autowired
	public ReplyDAO replyDao;
	
	@Override
	public int create(ReplyVO vo) {
		LOGGER.info("create() ȣ��");
		return replyDao.insert(vo);
	}

	@Override
	public List<ReplyVO> read(int replyBno) {
		LOGGER.info("read() ȣ��");
		return replyDao.select(replyBno);
	}

	@Override
	public int update(ReplyVO vo) {
		LOGGER.info("update() ȣ��");
		return replyDao.update(vo);
	}

	@Override
	public int delete(int replyNo, int replyBno) {
		LOGGER.info("delete() ȣ��");
		return replyDao.delete(replyNo);
	}

}
