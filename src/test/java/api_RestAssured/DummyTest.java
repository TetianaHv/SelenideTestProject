package api_RestAssured;

import api.Employee;
import api.EmployeeResponse;
import api.PostEmployeeModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class DummyTest {
    @BeforeClass
    public void start() {
        baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void getAllEmployeesPositiveTest() {
        given()
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", hasItems("1", "2", "3"));
    }

    @Test
    public void getAllEmployeesNegativeTest() {
        given()
                .when()
                .get("/employee")
                .then()
                .log().all() //log response
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Error Occured! Page Not found, contact rstapi2example@gmail.com"));
    }

    @Test
    public void getEmployeeByIdPositiveTest() {
        Employee expectedEmployee = new Employee("Tiger Nixon", 320800, 61, "");
        EmployeeResponse expectedResponse = new EmployeeResponse("success", expectedEmployee, "Successfully! Record has been fetched.");

        EmployeeResponse response = given()
                .when()
                .get("/employee/1")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(response, expectedResponse);
    }

    @Test
    public void getEmployeeByIdNegativeTest() {
        given()
                .when()
                .get("/employees/1")
                .then()
                .statusCode(404)
                .log().all()
                .assertThat()
                .body("message", equalTo("Error Occured! Page Not found, contact rstapi2example@gmail.com"));
    }

    @Test
    public void postEmployeePositiveTest() {
        //{"name":"new","salary":"90000","age":"12"}
        PostEmployeeModel employeeModel = new PostEmployeeModel("new", "90000", "12");
        EmployeeResponse expectedResponse = new EmployeeResponse("success", new Employee(), "Successfully! Record has been added.");

        EmployeeResponse response = given()
                .with()
                .body(employeeModel)
                .post("/create")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(response, expectedResponse);
    }

    @Test
    public void postEmployeeNegativeTest() {
        PostEmployeeModel employeeModel = new PostEmployeeModel("", "90000", "12");

        given()
                .with()
                .body(employeeModel)
                .post("/created")
                .then()
                .statusCode(405)
                .log().all();
    }

    @Test
    public void putEmployeePositiveTest() {
        Employee employeeModel = new Employee("Tiger Nixon", 320800, 61, "");

        given()
                .body(employeeModel)
                .when()
                .put("/update/21")
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("message", equalTo("Successfully! Record has been updated."));
    }

    @Test
    public void putEmployeeNegativeTest() {
        Employee employeeModel = new Employee("Tiger", 320800, 61, "");

        given()
                .body(employeeModel)
                .when()
                .put("/updated/21")
                .then()
                .statusCode(405);
    }

    @Test
    public void deleteEmployeePositiveTest() {
        given()
                .when()
                .delete("/delete/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data", equalTo("1"))
                .body("message", equalTo("Successfully! Record has been deleted"));
    }

    @Test
    public void deleteEmployeeNegativeTest() {
        given()
                .when()
                .delete("/delete/0")
                .then()
                .statusCode(400)
                .assertThat()
                .body("status", equalTo("error"))
                .body("message", equalTo("Not found record"))
                .body("code", equalTo(400))
                .body("errors", equalTo("id is empty"));
    }
}
