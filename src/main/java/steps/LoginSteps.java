package steps;
import dto.AuthSuccessResponse;
import dto.WrongResponse;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.testng.Assert;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;



public class LoginSteps {

    AuthSuccessResponse authSuccessResponse;
    WrongResponse authWrongResponse;
    @When("login user with email and password")
    public void loginUserWithEmailAndPassword(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");
        authSuccessResponse = given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in")
                .basePath("api")
                .body("{\n" +
                        "    \"email\": \""+ email +"\",\n" +
                        "    \"password\": \""+ password+ "\"\n" +
                        "}")
                .post("login")
                .then()
                .statusCode(200)
                .extract()
                .as(AuthSuccessResponse.class);
    }

    @When("login user with email and without password")
    public void loginUserWithEmailAndWithoutPassword(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");
        authWrongResponse = given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in")
                .basePath("api")
                .body("{\n" +
                        "    \"email\": \""+ email +"\",\n" +
                        "    \"password\": \"\"\n" +
                        "}")
                .post("login")
                .then()
                .statusCode(400)
                .extract()
                .as(WrongResponse.class);
    }

    @Then("user is successfully authorized")
    public void userIsSuccessfullyAuthorized() {
        Assert.assertNotNull(authSuccessResponse.token);
        System.out.println("new user is successfully registered");
    }

    @Then("login error is displayed")
    public void errorIsDisplayed() {
        Assert.assertEquals(authWrongResponse.error, "Missing password");
    }
}
