package de.jos.project.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private long discordChannelID;
    private String apiKey;
    private String projectID;
    private String serviceID;

    public User() {

    }

    public User(String id, String name, long discordChannelID) {
        this.id = id;
        this.name = name;
        this.discordChannelID = discordChannelID;
    }

    public User(String id, String apiToken, String projectID, String serviceID) {
        this.id = id;
        this.apiKey = apiToken;
        this.projectID = projectID;
        this.serviceID = serviceID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDiscordChannelID() {
        return discordChannelID;
    }

    public void setDiscordChannelID(long discordChannelID) {
        this.discordChannelID = discordChannelID;
    }

    public String toSQLString() {
        return "('" + this.id + "', '" + this.name + "', '" + this.discordChannelID + "', '" + this.apiKey + "', '" + this.projectID + "', '" + this.serviceID + "')";
    }
}
