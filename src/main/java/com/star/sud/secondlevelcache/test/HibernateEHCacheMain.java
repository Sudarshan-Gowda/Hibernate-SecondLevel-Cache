package com.star.sud.secondlevelcache.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import com.star.sud.secondlevelcache.model.Employee;
import com.star.sud.secondlevelcache.util.HibernateUtil;

public class HibernateEHCacheMain {

	public static void main(String[] args) {

		System.out.println("Temp Dir:" + System.getProperty("java.io.tmpdir"));

		// Initialize Sessions
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Statistics stats = sessionFactory.getStatistics();
		System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		System.out.println("Stats enabled=" + stats.isStatisticsEnabled());

		Session session = sessionFactory.openSession();
		Session otherSession = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Transaction otherTransaction = otherSession.beginTransaction();

		printStatistics(stats, 0);

		Employee emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 1);

		emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 2);

		// clear first level cache, so that second level cache is used
		session.evict(emp);
		emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 3);

		emp = (Employee) session.load(Employee.class, 2L);
		printData(emp, stats, 4);

		emp = (Employee) otherSession.load(Employee.class, 1L);
		printData(emp, stats, 5);

		// Release resources
		transaction.commit();
		otherTransaction.commit();
		sessionFactory.close();
	}

	private static void printStatistics(Statistics stats, int i) {
		System.out.println("***** " + i + " *****");
		System.out.println("Fetch Count=" + stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count=" + stats.getSecondLevelCachePutCount());
	}

	private static void printData(Employee emp, Statistics stats, int count) {
		System.out.println(count + ":: Name=" + emp.getName() + ", Zipcode=" + emp.getAddress().getZipcode());
		printStatistics(stats, count);
	}

}
