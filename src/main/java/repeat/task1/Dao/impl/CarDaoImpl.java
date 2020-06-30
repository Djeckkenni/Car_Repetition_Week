package repeat.task1.Dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repeat.task1.Dao.CarDao;
import repeat.task1.lib.Dao;
import repeat.task1.model.Car;
import repeat.task1.util.HibernateUtil;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();
            return car;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert Car entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Car getById(Car car) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder
                    .createQuery(Car.class);
            Root<Car> root = criteriaQuery.from(Car.class);
            Predicate predicateId = criteriaBuilder.equal(root.get("id"), car.getId());
            criteriaQuery.where(predicateId);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Can't get car with id " + car.getId(), e);
        }
    }

    @Override
    public boolean delete(Car car) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't delete car with id "
                    + car.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    @Override
    public Car update(Car car) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't update car with id "
                    + car.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return car;
    }
}
