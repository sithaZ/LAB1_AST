package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProductApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    @Test
    public void getAllProductsShouldReturnSuccess() {
        given()
            .log().all()
        .when()
            .get("/products")
        .then()
            .log().all()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test
    public void getSingleProductShouldReturnCorrectData() {
        given()
            .log().all()
        .when()
            .get("/products/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("title", notNullValue())
            .body("price", greaterThan(0f))
            .body("category", notNullValue());
    }

    @Test
    public void validateProductJsonSchema() {
        given()
            .log().all()
        .when()
            .get("/products/1")
        .then()
            .log().all()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/product-schema.json"));
    }
}