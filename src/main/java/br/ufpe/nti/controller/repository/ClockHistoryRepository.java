package br.ufpe.nti.controller.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufpe.nti.model.Clock;

@Repository
@Transactional(readOnly = true)
public class ClockHistoryRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Clock> listAll() {
		return em.createQuery("SELECT c FROM Clock c", Clock.class).getResultList();
	}
	
	public Clock getLastEntry() {
		return em.createQuery("SELECT c "
				+ "FROM Clock c "
				+ "WHERE c.id = (SELECT MAX(c2.id) FROM Clock c2)", Clock.class).getSingleResult();
	}

	@Transactional
	public Clock save(Clock c) {
		em.persist(c);
		return c;
	}

}
