package edu.spring.ex03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.ex03.domain.ReplyVO;
import edu.spring.ex03.persistence.BoardDAO;
import edu.spring.ex03.persistence.ReplyDAO;

@Service
public class ReplyServiceImple implements ReplyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReplyServiceImple.class);

	@Autowired
	public ReplyDAO replyDao;
	@Autowired
	private BoardDAO boardDao;

	// @Transactional
	// - �� ���� �����ͺ��̽� ������ ���� ��
	// ���� ������ ����Ǿ���, �Ʒ� ������ ������ �߻��ϸ�
	// ���� ������ rollback �Ǿ�� �Ѵ�.
	// �̷��� ����� ���ִ� ������̼�
	// root-context.xml���� ����

	@Transactional

	@Override
	public int create(ReplyVO vo) throws Exception {
		// ����� �����ϸ�
		// �Խ��ǿ� ��� ������ ����Ǿ�� ��
		// test_reply ���̺� insert�� �����ϸ�
		// test_board ���̺� update�� �����Ѵ�.

		LOGGER.info("create() ȣ��");
		replyDao.insert(vo);
		LOGGER.info("��� �Է� ����");

		boardDao.updateReplyCount(1, vo.getReplyBno());
		LOGGER.info("�Խ��� ��� ���� ������Ʈ ����");

		return 1;
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

	@Transactional
	@Override
	public int delete(int replyNo, int replyBno) throws Exception {
		LOGGER.info("delete() ȣ��");
		replyDao.delete(replyNo);
		LOGGER.info("��� ���� ����");

		boardDao.updateReplyCount(-1, replyBno);
		LOGGER.info("�Խ��� ��� ���� ������Ʈ ����");
		return 1;
	}

}
