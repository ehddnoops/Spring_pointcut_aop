package edu.spring.ex03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImple implements CustomerService{
		private static final Logger LOGGER =
				LoggerFactory.getLogger(CustomerServiceImple.class);
	@Override
	public void createCustomer() throws Exception {
		LOGGER.info("고객 정보 생성");
		throw new Exception("고객 정보 생성 실패");// 예외 발생
	}

	@Override
	public int deleteCustomer() {
		LOGGER.info("고객 정보 삭제");
		return 0;
	}

	@Override
	public int updateCustomer() throws Exception {
		LOGGER.info("고객 정보 수정");
		return 0;
	}
	
}
