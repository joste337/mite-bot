package de.jos.project.controller;


import de.jos.project.model.MiteEntry;
import de.jos.project.model.ProjectResponse;
import de.jos.project.model.ServiceResponse;
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



    private RestTemplate restTemplate;

    public MiteClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public void createNewEntry(String duration, String comment) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MiteEntry miteEntry = new MiteEntry(duration, comment, mtr3ID, developmentID);


        HttpEntity<MiteEntry> entity = new HttpEntity<>(miteEntry, headers);

        String response = restTemplate.postForObject(mitePostUrl, entity, String.class);

    }

    public void getAvailableProjects() {
        ProjectResponse[] projects = restTemplate.getForObject(miteUrl, ProjectResponse[].class);
        for (ProjectResponse projectResponse : projects) {
            System.out.println(projectResponse.getProject().getName() + "; " + projectResponse.getProject().getId());
        }

        ServiceResponse[] serviceResponses = restTemplate.getForObject(miteUrl2, ServiceResponse[].class);
        for (ServiceResponse serviceResponse : serviceResponses) {
            System.out.println(serviceResponse.getService().getName() + "; " + serviceResponse.getService().getId());
        }
    }

    private String buildFinalUrl(String endpoint) {
        return MITE_BASE_URL + endpoint;
    }
}
