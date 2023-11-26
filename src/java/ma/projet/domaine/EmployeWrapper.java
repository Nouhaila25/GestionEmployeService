/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import ma.projet.beans.Employe;

/**
 *
 * @author khabb
 */
public class EmployeWrapper {

    private Employe employe;

    public EmployeWrapper(Employe employe) {
        this.employe = employe;
    }

    public Employe getEmploye() {
        return employe;
    }

    // Autres getters et setters si nécessaire
}
