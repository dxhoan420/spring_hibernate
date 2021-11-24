package hiber.dao;

import hiber.model.User;
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
   public User getUserWithTheCar(int series, String model) {
      //Почему не работает?
//      String hql = "from User where car.series=" + series + " and car.model=" + model;
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("from User where car.series = :series and car.model = :model", User.class)
              .setParameter("series", series)
              .setParameter("model", model);
      List<User> resultList = query.getResultList();
      if (!resultList.isEmpty())
         return resultList.get(0);
      else
         return null;
   }

}
