package ma.projet.domaine;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ma.projet.beans.Employe;
import ma.projet.beans.ServiceEntity;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceEntityService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "serviceBean")
@ViewScoped
public class ServiceBean implements Serializable {

    private ServiceEntity serviceEntity;
    private Employe employe;
    private EmployeService employeService;
    private ServiceEntityService serviceEntityService;
    private List<ServiceEntity> services;

    public ServiceBean() {
        serviceEntity = new ServiceEntity();
        employeService = new EmployeService();
        serviceEntityService = new ServiceEntityService();
        loadServices(); // Load services when the bean is initialized
    }

    public ServiceEntity getServiceEntity() {
        return serviceEntity;
    }

    public void setServiceEntity(ServiceEntity serviceEntity) {
        this.serviceEntity = serviceEntity;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
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

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

    public String createService() {
        serviceEntityService.create(serviceEntity);
        serviceEntity = new ServiceEntity(); // Clear the input fields after creation
        loadServices();
        return null;
    }

    public void updateService(RowEditEvent event) {
        serviceEntity = (ServiceEntity) event.getObject();
        ServiceEntity serviceEntity = serviceEntityService.getById(this.serviceEntity.getId());
        serviceEntity.setNom(serviceEntity.getNom());
        serviceEntity.setId(serviceEntity.getId());
        serviceEntityService.update(serviceEntity);
    }

    public String deleteService() {
        serviceEntityService.delete(serviceEntityService.getById(serviceEntity.getId()));
        return null;
    }
     public void onEdit(RowEditEvent event) {
        serviceEntity = (ServiceEntity) event.getObject();
        ServiceEntity service = serviceEntityService.getById(this.serviceEntity.getId());
      
        service.setNom(service.getNom());
        serviceEntityService.update(service);
       
    }

    public void onCancel(RowEditEvent event) {
    }


    public void loadServices() {
        services = serviceEntityService.getAll();
    }
    public BarChartModel getEmployeeChartData() {
        BarChartModel model = new BarChartModel();
        List<ServiceEntity> services = serviceEntityService.getAllServices();

        for (ServiceEntity service : services) {
            ChartSeries series = new ChartSeries();
            series.setLabel(service.getNom());

            int employeeCount = service.getEmployes().size();
            series.set(service.getNom(), employeeCount);

            model.addSeries(series);
        }

        // Configuration du modèle
        model.setTitle("Nombre d'employés par service");
        model.setLegendPosition("ne");

        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Service");
        xAxis.setTickAngle(-90);

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Nombre d'employés");

        return model;
    }
}
