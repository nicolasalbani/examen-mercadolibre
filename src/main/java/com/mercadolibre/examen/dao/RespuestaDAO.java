package com.mercadolibre.examen.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import com.mercadolibre.examen.domain.Respuesta;
import com.mercadolibre.examen.hibernate.SessionFactoryProvider;

public class RespuestaDAO {

	public static RespuestaDAO instance = null;
	public static boolean initialized = false;
	private SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

	private RespuestaDAO(){	}

	/**
	 * Retorna una instancia singleton del DAO
	 * @return
	 */
	public static RespuestaDAO getInstance(){
		if(instance == null){
			instance = new RespuestaDAO();
		}

		return instance;
	}

	/**
	 * Persiste un listado de elementos Respuesta en la base de datos. En caso de ya estar
	 * inicializada, actualiza la lista.
	 * @param respuestaList
	 */
	public void persistirListado(List<Respuesta> respuestaList) {
		StatelessSession session = sessionFactory.openStatelessSession();
		Transaction tx = session.beginTransaction();
		if(!initialized){
			for (Respuesta respuesta:respuestaList) {
				session.insert(respuesta);
			}
			initialized = true;
		} else {
			for (Respuesta respuesta:respuestaList) {
				session.update(respuesta);
			}
		}
		tx.commit();
		session.close();
	}

	/**
	 * Retorna el elemento Respuesta para el dia buscado
	 * @param dia
	 * @return
	 */
	public Respuesta buscarRespuesta(int dia) {
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("FROM respuesta R WHERE R.dia = :dia");
			query.setParameter("dia", dia);
			return (Respuesta) query.uniqueResult();
		} catch (RuntimeException re) {
			throw re;
		} finally {
			session.close();
		}
	}

	/**
	 * Obtiene las ocurrencias de los diferentes tipos de clima
	 * @param clima
	 * @return
	 */
	public List<Respuesta> obtenerOcurrencias(String clima){
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("FROM respuesta R WHERE R.clima = :clima");
			query.setParameter("clima", clima);
			return query.list();
		} catch (RuntimeException re) {
			throw re;
		} finally {
			session.close();
		}
	}
}
