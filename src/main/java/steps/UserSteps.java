package steps;

import dto.UserCreateResponse;
import dto.UserGetResponse;
import dto.UserUpdateResponse;
import dto.UsersListResponse;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserSteps {


    RequestSpecification requestSpecification = given()
            .contentType(ContentType.JSON)
            .baseUri("https://reqres.in")
            .basePath("api");
    UserCreateResponse userCreateResponse;

    UserUpdateResponse userUpdateResponse;

    UserGetResponse userResponse;

    Response response;

    UsersListResponse usersListResponse;

    @When("create user with name and job")
    public void createUserWithNameAndJob(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String name = dataTable.get(0).get("name");
        String job = dataTable.get(0).get("job");
        userCreateResponse = requestSpecification
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
            userUpdateResponse = requestSpecification
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
        requestSpecification
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

    @Then("user data is displayed")
    public void userDataIsDisplayed() {
        userResponse = response.then().statusCode(200).extract().as(UserGetResponse.class);
        Assert.assertEquals(userResponse.data.id, 2);
    }

    @When("get user by id")
    public void getUserById(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        int id = Integer.parseInt(dataTable.get(0).get("id"));
        response = requestSpecification
                .get("users/"+id);
    }

    @Then("user is not found")
    public void userIsNotFound() {
        response.then().statusCode(404);
        System.out.println("user is not found");
    }


    @When("get user list")
    public void getUserList() {
       usersListResponse =  requestSpecification
                .get("users?page=2")
                .then()
                .statusCode(200)
                .extract()
                .as(UsersListResponse.class);
    }

    @Then("user list is not empty")
    public void userListIsNotEmpty() {
        Assert.assertEquals(usersListResponse.data.length, 6);
       Assert.assertEquals(usersListResponse.total, usersListResponse.per_page* usersListResponse.page);
    }
}
