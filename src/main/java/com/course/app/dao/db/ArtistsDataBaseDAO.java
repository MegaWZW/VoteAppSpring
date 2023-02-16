package com.course.app.dao.db;

import com.course.app.dao.api.IArtistsDAO;
import com.course.app.dao.db.orm.entity.Artist;
import com.course.app.dto.ArtistDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArtistsDataBaseDAO implements IArtistsDAO {

	private final EntityManagerFactory factory;

	public ArtistsDataBaseDAO(EntityManagerFactory factory){
		 this.factory = factory;
	}

	@Override
	public List<ArtistDTO> getAll() {
		List<ArtistDTO> list = new ArrayList<>();
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
			Root<Artist> artistRoot = cq.from(Artist.class);
			cq.select(artistRoot);
			List<Artist> artistEntities = em.createQuery(cq).getResultList();
			em.getTransaction().commit();
			for(Artist entity : artistEntities) {
				list.add(new ArtistDTO(entity));
			}
		}catch(Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
		return list;
	}

	@Override
	public ArtistDTO getOne(Long id) {
		EntityManager entityManager = factory.createEntityManager();
		try{
			if (!isExist(id)) {
				throw new NoSuchElementException("Артиста с таким id не существует");
			}
			entityManager.getTransaction().begin();
			Artist entity = entityManager.find(Artist.class, id);
			entityManager.getTransaction().commit();
			return new ArtistDTO(entity);
		}catch (Exception e) {
			if(entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void addPosition(ArtistDTO artist) {
		Artist entity = new Artist(artist);
		EntityManager em = factory.createEntityManager();
		try {
			if(isNameExist(artist.getName())){
				throw new IllegalArgumentException("Такой артист уже участвует в голосовании");
			}
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally {
			em.close();
		}
	}

	@Override
	public void deletePosition(Long id, Long version) {
		EntityManager em = factory.createEntityManager();
		try{
			Artist entity = em.find(Artist.class, id);
			if(!isExist(id)) {
				throw new NoSuchElementException("Артиста, которого Вы хотите удалить, нет в БД");
			}
			if(!entity.getVersion().equals(version)){
				throw new IllegalStateException("Запись об артисте, которую Вы хотите удалить, была изменена");
			}
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally {
			em.close();
		}
	}

	@Override
	public void updatePosition(ArtistDTO dto, Long id, Long version) {
		EntityManager em = factory.createEntityManager();
		try {
			if(!isExist(id)){
				throw new NoSuchElementException("Изменяемого артиста не существует");
			}
			Artist entity = em.find(Artist.class, id);
			if(!entity.getVersion().equals(version)){
				throw new IllegalStateException("Запись об артисте, которую Вы хотите изменить, уже была изменена");
			}
			Long newVersion = entity.getVersion() + 1L;
			em.getTransaction().begin();
			entity.setName(dto.getName());
			entity.setVersion(newVersion);
			em.getTransaction().commit();
		}catch (Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally {
			em.close();
		}
	}

	@Override
	public boolean isExist(Long id) {
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
			Root<Artist> artistRoot = cq.from(Artist.class);
			cq.select(artistRoot).where(cb.equal(artistRoot.get("id"), id));
			List<Artist> artistEntities = em.createQuery(cq).getResultList();
			em.getTransaction().commit();
			int size = artistEntities.size();
			return size != 0;

		}catch(Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
	}

	private boolean isNameExist(String name) {
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
			Root<Artist> artistRoot = cq.from(Artist.class);
			cq.select(artistRoot).where(cb.like(artistRoot.get("name"), name));
			List<Artist> artistEntities = em.createQuery(cq).getResultList();
			em.getTransaction().commit();
			int size = artistEntities.size();
			return size != 0;

		}catch(Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			throw new RuntimeException(e);
		}finally {
			em.close();
		}
	}
}
