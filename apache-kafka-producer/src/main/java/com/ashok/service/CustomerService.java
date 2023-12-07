package com.ashok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ashok.model.Customer;
import com.ashok.util.KafkaConstants;

/**
 * This class is used to perform business operation
 * 
 * @author Ashok
 *
 */

@Service("customerService")
public class CustomerService {

	@Autowired
	private KafkaTemplate<String, Customer> kafkaTemplate;

	/**
	 * This method is used to publish customer records as msgs to kafka topic
	 * 
	 * @param customers
	 * @return
	 */
	public String add(List<Customer> customers) {

		if (!customers.isEmpty()) {
			for (Customer c : customers) {
				kafkaTemplate.send(KafkaConstants.TOPIC, c);
				System.out.println("************Msg published to Kafka topic***************");
			}
		}
		return "Customer Record Added To Kafka Queue Successfully";
	}
}
