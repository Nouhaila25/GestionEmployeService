/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ma.projet.beans.Employe;
import ma.projet.beans.ServiceEntity;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceEntityService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "employeBean")
@ViewScoped
public class EmployeBean implements Serializable{
    
    private TreeNode root;
    
    private Employe employe;
    private ServiceEntity service;
    private List<Employe> employes;
    private EmployeService employeService;
    private ServiceEntityService serviceEntityService;
    private TreeNode selectedNode;


    public EmployeBean() {
        employe = new Employe();
        employeService = new EmployeService();
        serviceEntityService = new ServiceEntityService();
        root = new DefaultTreeNode("root", null);
        loadTree();
        loadEmployes();
        
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public ServiceEntity getServiceEntity() {
        return service;
    }

    public void setServiceEntity(ServiceEntity service) {
        this.service = service;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }

    public ServiceEntityService getServiceEntityService() {
        return serviceEntityService;
    }

    public void setServiceEntityService(ServiceEntityService serviceEntityService) {
        this.serviceEntityService = serviceEntityService;
    }
    
     public String onCreateAction() {
        employeService.create(employe);
        employe = new Employe();
        return null;
    }
    
     public void onEdit(RowEditEvent event) {
        employe = (Employe) event.getObject();
        ServiceEntity service = serviceEntityService.getById(this.employe.getServiceEntity().getId());
        employe.setServiceEntity(service);
        employe.getServiceEntity().setNom(service.getNom());
        employeService.update(employe);
    }
     
    public String onDeleteAction() {
        employeService.delete(employeService.getById(employe.getId()));
        return null;
    }

    public void onCancel(RowEditEvent event) {
    }

    public void loadEmployes() {
        employes = employeService.getAll();
    }
    
   public void loadTree() {
    root.getChildren().clear(); // Clear old nodes
    List<ServiceEntity> services = serviceEntityService.getAll();

    for (ServiceEntity service : services) {
        TreeNode serviceNode = new DefaultTreeNode(service, root);

        // Employees nodes
        for (Employe employe : service.getEmployes()) {
            TreeNode employeNode = new DefaultTreeNode(employe, serviceNode);

            // Chief node
            Employe chief = employe.getChef();
            if (chief != null) {
                TreeNode chiefNode = new DefaultTreeNode(chief, employeNode);
            }
        }
    }
}


    
    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Employe Edited", ((Employe) event.getObject()).getNom());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteNode() {
    if (selectedNode != null) {
        Object data = selectedNode.getData();

        if (data instanceof Employe) {
            Employe employe = (Employe) data;
            employeService.delete(employe);
            loadEmployes();
        }

        TreeNode parent = selectedNode.getParent();
        if (parent != null) {
            parent.getChildren().remove(selectedNode);
        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employe deleted", null);
        FacesContext.getCurrentInstance().addMessage(null, message);

        selectedNode.setParent(null);
        selectedNode = null;  // Set selectedNode to null after deletion
    }
}


}

    

    
    
