package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findUserByCar(String model, int series) {

        System.out.println("starting method");

        Query query = sessionFactory.getCurrentSession().createQuery("from Car car where car.model=:mode and car.series=:ser");
        query.setParameter("mode", model);
        query.setParameter("ser", series);

        Car car = (Car) query.getSingleResult();

        //System.out.println("need for me");
//
        Query query1 = sessionFactory.getCurrentSession().createQuery("from User user where user.car=:carid");
        query1.setParameter("carid", car);
//
        User user = (User) query1.getSingleResult();

        System.out.println(user.getLastName());
        return user;
    }
}
