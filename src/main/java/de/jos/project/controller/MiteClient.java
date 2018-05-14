package de.jos.project.controller;


import de.jos.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MiteClient {
    private String miteUrl = "https://exozet.mite.yo.lk/projects.json?api_key=502e33dc65c3c4d2";
    private String miteUrl2 = "https://exozet.mite.yo.lk/services.json?api_key=502e33dc65c3c4d2";
    private final String MITE_BASE_URL = "https://exozet.mite.yo.lk/";
    private String mitePostUrl = "https://exozet.mite.yo.lk/time_entries.json?api_key=502e33dc65c3c4d2";

    private String mtr3ID = "2351287";
    private String developmentID = "253445";

    @Autowired
    BotMessages botMessages;



    private RestTemplate restTemplate;

    public MiteClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public String createNewEntry(String duration, String comment, User user) {
        String url = MITE_BASE_URL + "time_entries.json?api_key=" + user.getApiKey();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MiteEntry miteEntry = new MiteEntry(duration, comment, user.getProjectID(), user.getServiceID());
        HttpEntity<MiteEntry> entity = new HttpEntity<>(miteEntry, headers);

        String response = restTemplate.postForObject(url, entity, String.class);

        return response;
    }

    public String getAvailableProjects(User user) {
        String url = MITE_BASE_URL + "projects.json?api_key=" + user.getApiKey();
        ProjectResponse[] projects = restTemplate.getForObject(url, ProjectResponse[].class);

        String response = "";
        for (ProjectResponse projectResponse : projects) {
            response += projectResponse.getProject().getName() + "; " + projectResponse.getProject().getId() + "\n";
        }
        return response;
    }

    public String getAvailableProjectsByName(User user, String name) {
        String url = MITE_BASE_URL + "projects.json?api_key=" + user.getApiKey() + "&name=" + name;
        ProjectResponse[] projects = restTemplate.getForObject(url, ProjectResponse[].class);

        if (projects.length == 1) {
            user.setProjectID(projects[0].getProject().getId());
            return botMessages.getSuccessfullySetProjectIdByNameReply(projects[0].getProject().getName());
        }

        String response = "";
        for (ProjectResponse projectResponse : projects) {
            response += projectResponse.getProject().getName() + "; " + projectResponse.getProject().getId() + "\n";
        }
        return response;
    }

    public String getAvailableServices(User user) {
        String url = MITE_BASE_URL + "projects.json?api_key=" + user.getApiKey();
        ServiceResponse[] services = restTemplate.getForObject(url, ServiceResponse[].class);

        String response = "";
        for (ServiceResponse serviceResponse : services) {
            response += serviceResponse.getService().getName() + "; " + serviceResponse.getService().getId() + "\n";
        }
        return response;
    }

    public String getAvailableServicesByName(User user, String name) {
        String url = MITE_BASE_URL + "projects.json?api_key=" + user.getApiKey() + "&name=" + name;
        ServiceResponse[] services = restTemplate.getForObject(url, ServiceResponse[].class);

        if (services.length == 1) {
            user.setServiceID(services[0].getService().getId());
            return botMessages.getSuccessfullySetProjectIdByNameReply(services[0].getService().getName());
        }

        String response = "";
        for (ServiceResponse serviceResponse : services) {
            response += serviceResponse.getService().getName() + "; " + serviceResponse.getService().getId() + "\n";
        }
        return response;
    }

}
