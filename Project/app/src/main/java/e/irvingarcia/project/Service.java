package e.irvingarcia.project;

public class Service {
    private String service;
    private double hour;
    public Service(){}
    public Service(String service,double hourly){
        this.service=service;
        hour=hourly;
    }
    public double getHour() {
        return hour;
    }

    public String getService() {
        return service;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    public void setService(String service) {
        this.service = service;
    }
}
