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
		LOGGER.info("�� ���� ����");
		throw new Exception("�� ���� ���� ����");// ���� �߻�
	}

	@Override
	public int deleteCustomer() {
		LOGGER.info("�� ���� ����");
		return 0;
	}

	@Override
	public int updateCustomer() throws Exception {
		LOGGER.info("�� ���� ����");
		return 0;
	}
	
}
