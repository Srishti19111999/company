package com.company.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {

	static SessionFactory factory;
	static
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		factory = configuration.buildSessionFactory();
	}

	public static Session getSession()
	{
		Session session = factory.openSession();
		return session;
	}
}
