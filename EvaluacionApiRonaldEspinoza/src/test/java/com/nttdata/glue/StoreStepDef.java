package com.nttdata.glue;

import com.nttdata.steps.StoreStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class StoreStepDef {
    @Steps
    StoreStep storeStep;

    //Crear Orden
    @When("creo la siguiente orden con el id {string}, petId {string}, cantidad {string}")
    public void creoLaSiguienteOrdenConElIdPetIdCantidad(String id, String petId, String cantidad) {
        storeStep.crearOrden(id, petId, cantidad);
    }

    @Then("valido el codigo de respuesta es {int}")
    public void validoElCodigoDeRespuestaEs(int codigoRespuesta) {
        storeStep.validarCodidoRespuestaCreacion(codigoRespuesta);
    }

    //Consultar Orden
    @Given("que ingreso a la url {string}")
    public void queIngresoALaUrl(String url) {
        storeStep.ingresarUrl(url);
    }

    @When("busco por el orderId {int}")
    public void buscoPorElOrderId(int orderId) {
        storeStep.buscoPorOrderId(orderId);
    }

    @Then("valido que el codigo de respuesta es {int}")
    public void elCodigoDeRespuestaEs(int codigoRespuesta) {
        storeStep.validoCodigoRespuestaConsulta(codigoRespuesta);
    }


    @And("valido si el campo status es {string}")
    public void validoSiElCampoStatusEs(String estado) {
        storeStep.validoCampoStatus(estado);
        Assert.assertEquals("Valor de campo Status es: ", "placed", estado);
    }


    @And("valido si el campo complete es {}")
    public void validoSiElCampoCompleteEs(boolean estadoT) {
        storeStep.validoCampoComplete(estadoT);
        boolean valorTrue = true;
        Assert.assertEquals("Valor de campo Complete es: ", valorTrue, estadoT);

    }
}
