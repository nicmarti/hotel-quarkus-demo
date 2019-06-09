package org.acme.hibernate.orm.panache;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNot.not;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class HotelsEndpointTest {

    @Test
    public void testListAllFruits() {
        //List all, should have some hotel initially
        given()
              .when().get("/hotels")
              .then()
              .statusCode(200)
              .body(
                    containsString("Maillot"),
                    containsString("Méridien")
                    );
    }

}
