
import java.util.Date;
import ma.projet.beans.Employe;
import ma.projet.beans.ServiceEntity;
import ma.projet.domaine.ServiceBean;
import ma.projet.service.EmployeService;
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
public class Test3 {
    public static void main(String[] args){
        EmployeService ms = new EmployeService();
        ServiceEntityService ss = new ServiceEntityService();
        ServiceBean serviceBean = new ServiceBean();
        
        //ms.create(new Employe("khabbachi", "nouhaila", new Date(), "sdfghjk", ss.getById(1L), null));
        //ms.create(new Employe("hansal", "nada", new Date(), "ertyuio", ss.getById(1L), ms.getById(8L)));
        //ms.create(new Employe("merjane", "wiam", new Date(), "cvbnhjkl", ss.getById(1L), ms.getById(8L)));

        //ms.create(new Employe("jalaoui", "salma", new Date(), "xcvbn,", ss.getById(2L), null));
        //ms.create(new Employe("lokj", "fatima", new Date(), "ertyuio", ss.getById(2L), ms.getById(9L)));
        //ms.create(new Employe("lakiml", "nazha", new Date(), "cvbnhjkl", ss.getById(2L), ms.getById(9L)));

        //Afficher les machines par salle
        for (ServiceEntity s : ss.getAll()) {
            System.out.println("Service  : " + s.getNom() + " : ");

            for (Employe m : s.getEmployes()) {
                Employe chef = m.getChef();
                String chefName = (chef != null) ? chef.getNom() + " " + chef.getPrenom() : "Chef non défini";
                System.out.println("\t" + m.getNom() + " - Chef: " + chefName);
            }
        }

    }
    
    
}
