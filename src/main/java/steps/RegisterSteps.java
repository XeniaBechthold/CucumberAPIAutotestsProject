package steps;

import dto.RegisterSuccessResponse;
import dto.WrongResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.http.ContentType;
import org.testng.Assert;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;



public class RegisterSteps {

    RegisterSuccessResponse registerSuccessResponse;
    WrongResponse registerWrongResponse;

    @When("register new user with email and password")
    public void registerNewUserWithEmailAndPassword(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");
        registerSuccessResponse = given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in")
                .basePath("api")
                .body("{\n" +
                        "    \"email\": \""+ email +"\",\n" +
                        "    \"password\": \""+ password+ "\"\n" +
                        "}")
                .post("register")
                .then()
                .statusCode(200)
                .extract()
                .as(RegisterSuccessResponse.class);
    }

    @When("register new user with email and without password")
    public void registerNewUserWithEmailAndWithoutPassword(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");
        registerWrongResponse = given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in")
                .basePath("api")
                .body("{\n" +
                        "    \"email\": \""+ email +"\",\n" +
                        "    \"password\": \"\"\n" +
                        "}")
                .post("register")
                .then()
                .statusCode(400)
                .extract()
                .as(WrongResponse.class);
    }

    @Then("new user is successfully registered")
    public void newUserIsSuccessfullyRegistered() {
        Assert.assertNotNull(registerSuccessResponse.token);
        System.out.println("new user is successfully registered");
    }

    @Then("error is displayed")
    public void errorIsDisplayed() {
        Assert.assertEquals(registerWrongResponse.error, "Missing password");
    }

}
