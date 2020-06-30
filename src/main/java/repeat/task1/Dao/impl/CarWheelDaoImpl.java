package repeat.task1.Dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repeat.task1.Dao.CarWheelDao;
import repeat.task1.lib.Dao;
import repeat.task1.model.CarWheel;
import repeat.task1.util.HibernateUtil;

@Dao
public class CarWheelDaoImpl implements CarWheelDao {
    @Override
    public CarWheel create(CarWheel carWheel) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(carWheel);
            transaction.commit();
            return carWheel;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert car wheel entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CarWheel> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<CarWheel> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(CarWheel.class);
            criteriaQuery.from(CarWheel.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't give all car wheels ", e);
        }
    }
}

