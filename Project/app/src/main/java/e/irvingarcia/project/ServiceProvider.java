package e.irvingarcia.project;

import java.util.ArrayList;

public class ServiceProvider {
    private Users user;
    private String company;
    private String phone;
    private String description;
    private boolean liscence;
    private ArrayList<Service> services;
    private ArrayList<String> schedule;
    private int rate;

    private boolean completed=false;

    public  ServiceProvider(Users user){
        this.user=user;
    }
    public ServiceProvider(){}

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setCompleted(boolean b){
        completed=b;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLiscence(boolean liscence) {
        this.liscence = liscence;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLiscence() {
        return liscence;
    }

    public void setServices(ArrayList<Service> services){
        this.services=services;
    }
    public ArrayList<Service> getServices() {
        return services;
    }

    public void setSchedule(ArrayList<String> schedule) {
        this.schedule = schedule;
    }

    public ArrayList<String> getSchedule() {
        return schedule;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
