package repeat.task1.Dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repeat.task1.Dao.CarDoorDao;
import repeat.task1.lib.Dao;
import repeat.task1.model.CarDoor;
import repeat.task1.util.HibernateUtil;

@Dao
public class CarDoorDaoImpl implements CarDoorDao {
    @Override
    public CarDoor create(CarDoor door) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(door);
            transaction.commit();
            return door;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert car door entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public CarDoor getById(CarDoor door) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CarDoor> criteriaQuery = criteriaBuilder
                    .createQuery(CarDoor.class);
            Root<CarDoor> root = criteriaQuery.from(CarDoor.class);
            Predicate predicateId = criteriaBuilder.equal(root.get("id"), door.getId());
            criteriaQuery.where(predicateId);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Can't get door with id " + door.getId(), e);
        }
    }

    @Override
    public List<CarDoor> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<CarDoor> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(CarDoor.class);
            criteriaQuery.from(CarDoor.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't give all car door ", e);
        }
    }
}
