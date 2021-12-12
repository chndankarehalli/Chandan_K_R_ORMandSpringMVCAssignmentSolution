package com.great.learning.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.great.learning.model.Customer;
import com.great.learning.service.CustomerService;

@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		List<Customer> customer = session.createQuery("from Customer").list();
		return customer;
	}

	@Transactional
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(customer);
		transaction.commit();
	}

	@Transactional
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
		session.delete(customer);
		transaction.commit();
	}

}