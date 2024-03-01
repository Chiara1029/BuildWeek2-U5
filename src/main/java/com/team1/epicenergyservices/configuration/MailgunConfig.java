package com.team1.epicenergyservices.configuration;

import com.team1.epicenergyservices.entities.Client;
import com.team1.epicenergyservices.entities.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailgunConfig {
    private String mailgunAPIKey;
    private String domainName;
    public MailgunConfig(@Value("${mailgun.api.key}") String mailgunAPIKey, @Value("${mailgun.domain.name}") String domainName) {
        this.mailgunAPIKey = mailgunAPIKey;
        this.domainName = domainName;
    }

    public void sendRegistrationEmail(User user) {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", mailgunAPIKey)
                .queryString("from", "Chiara <chiarapuleio@yahoo.it>")
                .queryString("to", user.getEmail())
                .queryString("subject", "Registration completed!")
                .queryString("text", "Welcome aboard " + user.getName() + ", you've been successfully registered!").asJson();
        System.out.println(request.getBody());
    }

    public void sendInvoiceEmail(Client client) {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", mailgunAPIKey)
                .queryString("from", "Chiara <chiarapuleio@yahoo.it>")
                .queryString("to", client.getEmail())
                .queryString("subject", "New Invoice")
                .queryString("text", "Hello " + client.getNameContact() + ", a new invoice has been added in your profile.").asJson();
        System.out.println(request.getBody());
    }
}
