
import ma.projet.beans.ServiceEntity;
import ma.projet.domaine.ServiceBean;
import ma.projet.service.ServiceEntityService;
import ma.projet.util.HibernateUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author khabb
 */
public class Test2 {
    // Test2.java


    public static void main(String[] args) {
       
        ServiceEntityService ss = new ServiceEntityService();
        //création des Salles
        ss.create(new ServiceEntity("Informatique"));
        ss.create(new ServiceEntity("Marketing"));
        ss.create(new ServiceEntity("Comptailité"));
        //La liste des salles
        for (ServiceEntity s : ss.getAll()) {
            System.out.println("" + s.getNom());
        }
    }
    }


    

