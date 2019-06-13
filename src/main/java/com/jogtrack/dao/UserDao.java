package com.jogtrack.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.jogtrack.domain.UserDO;
import com.jogtrack.service.contract.User;

@Component
public class UserDao extends GenericDao<UserDO>{
	
	// Get a list of all users
	public List<UserDO> getAll(String filterString, int offset, int limit) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserDO> cq = cb.createQuery(UserDO.class);
		Root<UserDO> from = cq.from(UserDO.class);
		CriteriaQuery<UserDO> select = cq.select(from);
		
		// Create a Predicate object from the filter string
		Predicate pr = getPredicate(filterString, cb, from);
		select.where(pr);
	
		TypedQuery<UserDO> tq = em.createQuery(select);
		tq.setFirstResult(offset);
		tq.setMaxResults(limit);
		List<UserDO> userList = tq.getResultList();
		
		return userList;
	}
}
