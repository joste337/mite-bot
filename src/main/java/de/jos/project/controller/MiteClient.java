package de.jos.project.controller;


import de.jos.project.database.UserRepository;
import de.jos.project.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MiteClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiteClient.class);

    private static final String MITE_BASE_URL = "https://exozet.mite.yo.lk/";

    private String mtr3ID = "2351287";
    private String developmentID = "253445";

    @Autowired
    private BotMessages botMessages;
    @Autowired
    private UserRepository userRepository;

    private RestTemplate restTemplate;

    public MiteClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public String createNewEntry(String duration, String comment, User user) {
        String url = MITE_BASE_URL + "time_entries.json?api_key=" + user.getApiKey();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MiteEntry miteEntry = new MiteEntry(duration, comment, user.getProjectId(), user.getServiceId());
        HttpEntity<MiteEntry> entity = new HttpEntity<>(miteEntry, headers);

        try {
            return restTemplate.postForObject(url, entity, String.class);
        } catch (Exception e) {
            LOGGER.error("Failed to execute new-Command: {}", e.getMessage());
            return botMessages.getCommandFailedReply();
        }
    }

    public String getAvailableProjectsByName(User user, String name) {
        LOGGER.info("projects request for name: {}", name);
        String url = MITE_BASE_URL + "projects.json?api_key=" + user.getApiKey() + "&name=" + name;
        ProjectResponse[] projects;
        try {
            projects = restTemplate.getForObject(url, ProjectResponse[].class);
        } catch (Exception e) {
            System.out.println("failed to execute project command: " + e.getMessage());
            LOGGER.error("Failed to execute project-Command: {}", e.getMessage());
            return botMessages.getCommandFailedReply();
        }

        StringBuilder responseBuilder = new StringBuilder();
        user.setProjectId(projects[0].getProject().getId());
        user.setProjectName(projects[0].getProject().getName());
        responseBuilder.append(botMessages.getSuccessfullySetProjectIdByNameReply(projects[0].getProject().getName()));
        responseBuilder.append("\n Projects found:\n");

        for (ProjectResponse projectResponse : projects) {
            responseBuilder.append(projectResponse.getProject().getName()).append("; ").append(projectResponse.getProject().getId()).append("\n");
        }
        return responseBuilder.toString();
    }

    public String getAvailableServicesByName(User user, String name) {
        String url = MITE_BASE_URL + "services.json?api_key=" + user.getApiKey() + "&name=" + name;
        ServiceResponse[] services;
        try {
            services = restTemplate.getForObject(url, ServiceResponse[].class);
        } catch (Exception e) {
            LOGGER.error("Failed to execute service-Command: {}", e.getMessage());
            return botMessages.getCommandFailedReply();
        }

        StringBuilder responseBuilder = new StringBuilder();
        user.setServiceId(services[0].getService().getId());
        user.setServiceName(services[0].getService().getName());
        responseBuilder.append(botMessages.getSuccessfullySetServiceIdByNameReply(services[0].getService().getName()));
        responseBuilder.append("\n Services found:\n");


        for (ServiceResponse serviceResponse : services) {
            responseBuilder.append(serviceResponse.getService().getName()).append("; ").append(serviceResponse.getService().getId()).append("\n");
        }
        return responseBuilder.toString();
    }

    public String verifyApiKey(User user, String apiKey) {
        String url = MITE_BASE_URL + "myself.json?api_key=" + apiKey;
        try {
            restTemplate.getForObject(url, Object.class);
            user.setApiKey(apiKey);
            return botMessages.getSuccessfullyRegisteredReply();
        } catch (Exception e) {
            return botMessages.getInvalidApiKeyReply();
        }
    }
}
