package ma.projet.service;

import java.util.List;

import ma.projet.beans.Employe;
import ma.projet.beans.ServiceEntity;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Query;

public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe employe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(employe);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Employe employe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(employe);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Employe employe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(employe);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Employe getById(int id) {
        Employe employe = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employe = (Employe) session.get(Employe.class, id);
        session.getTransaction().commit();
        return employe;
    }

    @Override
    public List<Employe> getAll() {
        List<Employe> employes = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes = session.createQuery("from Employe").list();
        session.getTransaction().commit();
        return employes;
    }
    public List<Employe> getEmployesByChef(Employe chef) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Employe where chef = :chef");
            query.setParameter("chef", chef);
            List<Employe> employes = query.list();
            session.getTransaction().commit();
            return employes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public ServiceEntity getServiceByChef(Employe chef) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select e.serviceEntity from Employe e where e = :chef");
            query.setParameter("chef", chef);
            ServiceEntity service = (ServiceEntity) query.uniqueResult();
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
    public Employe getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Employe employe = (Employe) session.get(Employe.class, id);
            session.getTransaction().commit();
            return employe;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


}
