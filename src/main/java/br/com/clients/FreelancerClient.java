package br.com.clients;

import br.com.clients.exceptions.CommunicationException;
import br.com.clients.exceptions.HttpClientException;
import br.com.dtos.requests.freelancer.CreateFreelancerDTO;
import br.com.entities.Freelancer;

import com.fasterxml.jackson.core.type.TypeReference;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static br.com.clients.routes.FreelancerRoutes.*;

public class FreelancerClient extends BaseClient {

    public HttpResponse<String> createFreelancer(CreateFreelancerDTO dto) {
        try {
            String json = toJson(dto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);
            return response;

        } catch (HttpClientException e) {
            throw e;
        } catch (Exception e) {
            throw new CommunicationException("Erro ao criar freelancer", e);
        }
    }

    public List<Freelancer> findActives() {
        return fetchList(FREELANCER_ACTIVES);
    }

    public List<Freelancer> findInactives() {
        return fetchList(FREELANCER_INACTIVES);
    }

    private List<Freelancer> fetchList(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);

            return mapper.readValue(
                    response.body(),
                    new TypeReference<List<Freelancer>>() {}
            );

        } catch (HttpClientException e) {
            throw e;
        } catch (Exception e) {
            throw new CommunicationException("Erro ao buscar freelancers: " + url, e);
        }
    }
}
