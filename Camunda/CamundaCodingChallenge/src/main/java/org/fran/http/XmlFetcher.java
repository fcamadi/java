/**********************************************************************************************/
/*                                                                                            */
/*  Camunda Coding Challenge                                                                  */
/*                                                                                            */
/*  Francisco Camacho - April 2021                                                            */
/*                                                                                            */
/**********************************************************************************************/
package org.fran.http;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.fran.model.Bpmn20DTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class that reads an URL and returns the xml stored in that URL
 */
public class XmlFetcher {

    public final String BPM_SOURCE =
            "https://n35ro2ic4d.execute-api.eu-central-1.amazonaws.com/prod/engine-rest/process-definition/key/invoice/xml";

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();


    private HttpResponse<String> sendGet(String url) {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // print status code
            //System.out.println("Response status code: "+response.statusCode());
            // print response body
            //System.out.println("Response body: "+response.body());

            return response;

        } catch (IOException | InterruptedException e) {
            System.out.println("Error getting diagram: "+e.getLocalizedMessage());
            System.exit(-1);
        }
        return null;
    }

    public String getBpmn20Xml(String url) {

        HttpResponse<String> response = sendGet(url);

        if (response!= null && (response.statusCode() == 200 || response.statusCode() == 201)) {
            try {
                Bpmn20DTO bpm20dto = new Gson().fromJson(response.body(), Bpmn20DTO.class);

                //System.out.println("Diagram Id: "+bpm20dto.getId());
                return bpm20dto.getBpmn20Xml();
            }
            catch (JsonSyntaxException e) {
                System.out.println("Error parsing Bpmn20Xml");
                System.exit(-1);
            }
        }
        return null;
    }

    //(for testing)
    public static void main(String[] args) {
        XmlFetcher xmlFetcher = new XmlFetcher();
        String result = xmlFetcher.getBpmn20Xml(xmlFetcher.BPM_SOURCE);
        System.out.println("Diagram xml: "+result);
    }
}
