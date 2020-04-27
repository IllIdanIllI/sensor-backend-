package com.sensor.dao.impl;

import com.sensor.dao.GenericDao;
import com.sensor.dao.UserDao;
import com.sensor.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static com.sensor.constant.ApplicationConstant.LOGIN;

@Repository
public class UserDaoImpl extends GenericDao<User, Long> implements UserDao {

    @Override
    @Transactional
    public User findByLogin(String login) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get(LOGIN), login));
        return session.createQuery(query).getSingleResult();
    }
}
