package com.mercadolibre.examen.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mercadolibre.examen.domain.Respuesta;

public class SessionFactoryProvider {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	/**
	 * Retorna una instancia singleton de SessionFactory
	 * @return	SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		
		if(sessionFactory==null){
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			addAnnotatedClasses(configuration);
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}

		return sessionFactory;
	}

	private static void addAnnotatedClasses(Configuration configuration){
		configuration.addAnnotatedClass(Respuesta.class);
	}
}