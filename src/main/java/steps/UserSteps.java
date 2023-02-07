package steps;

import dto.AuthSuccessResponse;
import dto.UserCreateResponse;
import dto.UserUpdateResponse;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserSteps {


    UserCreateResponse userCreateResponse;

    UserUpdateResponse userUpdateResponse;

    @When("create user with name and job")
    public void createUserWithNameAndJob(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String name = dataTable.get(0).get("name");
        String job = dataTable.get(0).get("job");
        userCreateResponse = given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in")
                .basePath("api")
                .body("{\n" +
                        "    \"name\": \""+ name +"\",\n" +
                        "    \"job\": \""+ job+ "\"\n" +
                        "}")
                .post("create")
                .then()
                .statusCode(201)
                .extract()
                .as(UserCreateResponse.class);

    }

    @When("update user with job")
    public void updateUserWithNameAndJob(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String job = dataTable.get(0).get("job");
            userUpdateResponse = given()
                    .contentType(ContentType.JSON)
                    .baseUri("https://reqres.in")
                    .basePath("api")
                    .body("{\n" +
                            "    \"job\": \""+ job+ "\"\n" +
                            "}")
                    .post("update/1")
                    .then()
                    .statusCode(201)
                    .extract()
                    .as(UserUpdateResponse.class);
        }

    @When("delete user by id")
    public void deleteUserbyId() {
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in")
                .basePath("api")
                .delete("delete/2")
                .then()
                .statusCode(204);
    }

    @Then("user is successfully created")
    public void userIsSuccessfullyCreated() {
        Assert.assertNotNull(userCreateResponse.id);
        System.out.println("new user is successfully created");
    }

    @Then("user is successfully updated with job")
    public void userIsSuccessfullyUpdated(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String job = dataTable.get(0).get("job");
        Assert.assertEquals(userUpdateResponse.job, job);
        System.out.println("user is successfully updated");
    }

    @Then("user is successfully deleted")
    public void userIsSuccessfullyDeleted() {
        System.out.println("user is successfully deleted");
    }
}
