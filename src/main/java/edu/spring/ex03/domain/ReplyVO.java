package edu.spring.ex03.domain;

import java.util.Date;

public class ReplyVO {
	private int replyNo; //��� ��ȣ
	private int replyBno;// �Խñ� ��ȣ
	private String replyContent;// ��� ����
	private String replyId;// ��� �ۼ��� ���̵�
	private Date replyDate;// ��� �ۼ�/���� �ð�
	
	public ReplyVO() {
		
	}

	public ReplyVO(int replyNo, int replyBno, String replyContent, String replyId, Date replyDate) {
		super();
		this.replyNo = replyNo;
		this.replyBno = replyBno;
		this.replyContent = replyContent;
		this.replyId = replyId;
		this.replyDate = replyDate;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getReplyBno() {
		return replyBno;
	}

	public void setReplyBno(int replyBno) {
		this.replyBno = replyBno;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", replyBno=" + replyBno + ", replyContent=" + replyContent
				+ ", replyId=" + replyId + ", replyDate=" + replyDate + "]";
	}

}