package steps;

import dto.*;
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

public class ResourceSteps {

    RequestSpecification requestSpecification = given()
            .contentType(ContentType.JSON)
            .baseUri("https://reqres.in")
            .basePath("api");

    Response resourceResponse;

    ResourceListResponse resourceListResponse;

    @Then("resource data is displayed")
    public void resourceDataIsDisplayed() {
        ResourceGetResponse resourceGetResponse = resourceResponse.then().statusCode(200).extract().as(ResourceGetResponse.class);
        Assert.assertEquals(resourceGetResponse.data.id, 2);
        System.out.println("user is successfully found");
    }

    @When("get resource by id")
    public void getResourceById(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        int id = Integer.parseInt(dataTable.get(0).get("id"));
        resourceResponse = requestSpecification
                .get("unknown/" + id);
    }

    @Then("resource is not found")
    public void resourceIsNotFound() {
        resourceResponse.then().statusCode(404);
        System.out.println("resource is not found");
    }


    @When("get resource list")
    public void getResourceList() {
        resourceListResponse =  requestSpecification
                .get("unknown?page=2")
                .then()
                .statusCode(200)
               .extract()
               .as(ResourceListResponse.class);
    }

    @Then("resource list is not empty")
    public void resourceListIsNotEmpty() {
        Assert.assertEquals(resourceListResponse.data.length, 6);
       Assert.assertEquals(resourceListResponse.total, resourceListResponse.per_page* resourceListResponse.page);
    }
}
