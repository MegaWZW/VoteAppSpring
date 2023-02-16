package com.course.app.dao.db;

import com.course.app.dao.api.IVotesDAO;
import com.course.app.dao.db.orm.entity.Vote;
import com.course.app.dto.VoteDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class VotesDataBaseDAO implements IVotesDAO {

	private final EntityManagerFactory factory;

	public VotesDataBaseDAO(EntityManagerFactory factory){
		this.factory = factory;
	}

	@Override
	public List<VoteDTO> getAll() {
		List<VoteDTO> list = new ArrayList<>();
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Vote> cq = cb.createQuery(Vote.class);
			Root<Vote> votesRoot = cq.from(Vote.class);
			CriteriaQuery<Vote> allVotes = cq.select(votesRoot);
			TypedQuery<Vote> allQuery = em.createQuery(allVotes);
			List<Vote> voteEntities = allQuery.getResultList();
			em.getTransaction().commit();
			for(Vote entity : voteEntities) {
				list.add(new VoteDTO(entity));
			}
		}catch(Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
		return list;
	}

	@Override
	public void save(VoteDTO voteDTO) {
		Vote entity = new Vote(voteDTO);
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			throw  new RuntimeException(e);
		} finally {
			em.close();
		}
	}
}
