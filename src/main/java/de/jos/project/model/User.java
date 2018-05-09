package de.jos.project.model;


import javax.persistence.Entity;
import java.util.UUID;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String id;
    private String apiToken;
    private String projectID;
    private String serviceID;

    public User(String apiToken) {
        this.apiToken = apiToken;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }
}
