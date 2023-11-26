package ma.projet.service;

import java.util.List;

import ma.projet.beans.ServiceEntity;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

public class ServiceEntityService implements IDao<ServiceEntity> {

    @Override
    public boolean create(ServiceEntity service) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(service);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(ServiceEntity service) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(service);
        session.getTransaction().commit();
        return true;
    }

    @Override
public boolean delete(ServiceEntity service) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(service);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Vous pouvez gérer cette exception de manière appropriée
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    @Override
    public ServiceEntity getById(int id) {
        ServiceEntity service = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        service = (ServiceEntity) session.get(ServiceEntity.class, id);
        session.getTransaction().commit();
        return service;
    }

     @Override
    public List<ServiceEntity> getAll() {
        
         List <ServiceEntity> serviceEntitys = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        serviceEntitys  = session.createQuery("from ServiceEntity").list();
        session.getTransaction().commit();
        return serviceEntitys;
    }
    public ServiceEntity getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ServiceEntity service = (ServiceEntity) session.get(ServiceEntity.class, id);
            session.getTransaction().commit();
            return service;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public List<ServiceEntity> getAllServices() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<ServiceEntity> services = session.createQuery("from ServiceEntity").list();
        session.getTransaction().commit();
        return services;
    }
   
}
