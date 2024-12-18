package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;


public class StoreStep {

    Response response;

    private static String URL_BASE = "https://petstore.swagger.io/v2/store/order";
    private String url_consulta;


    //crear orden
    public void crearOrden(String id, String petId, String cantidad) {
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body("{\n" +
                        "  \"id\": "+id+ ",\n" +
                        "  \"petId\": "+petId+",\n" +
                        "  \"quantity\": "+cantidad+",\n" +
                        "  \"shipDate\": \"2024-12-18T01:40:42.755Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .post(URL_BASE)
                .then()
                .log().all();
    }
    public void validarCodidoRespuestaCreacion(int codigoRespuesta) {
        restAssuredThat(response -> response.statusCode(codigoRespuesta));
    }


    //consultar orden
    public void ingresarUrl(String url) {
    url_consulta=url;
    }

    public void buscoPorOrderId(int orderId) {
        response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .baseUri(url_consulta)
                .log().all()
                .get("/store/order/"+ orderId)
                .then()
                .log().all()
                .extract().response();
    }

    public void validoCodigoRespuestaConsulta(int codigoRespuesta) {
            restAssuredThat(response -> response.statusCode(codigoRespuesta));
        }


    public void validoCampoStatus(String estado) {
        restAssuredThat(response -> response.body("'status'", equalTo(estado)));
        System.out.println("status: " + SerenityRest.lastResponse().body().path("status").toString());
    }

    public void validoCampoComplete(boolean estadoT) {
        restAssuredThat(response -> response.body("'complete'", equalTo(estadoT)));
        System.out.println("Complete: " + SerenityRest.lastResponse().body().path("complete").toString());

    }
}

