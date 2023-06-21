package tests.ApiTests;

import io.restassured.RestAssured;
import org.apache.hc.core5.http.HttpStatus;
import org.hamcrest.Matchers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojoClass.posts;
import steps.ApiSteps;

public class ApiTests extends BasicApiTestClass {

    private JdbcTemplate jdbcTemplate;

    @BeforeTest
    public void beforeApiTest() {
        jdbcTemplate = new JdbcTemplate(BasicApiTestClass.mysqlDataSource());
        /*jdbcTemplate.update("delete from wp_posts");*/
    }

    @Test()
    public void test() {
        RestAssured.given()
                .spec(ApiSteps.requestSpecification())//---> Указание RequestSpecification для формирования request
                .body(new posts( "leader"))//---> body для запроса с методом POST
                .post("/index.php?rest_route=/wp/v2/posts")//---> Endpoint для выполнения запроса GET
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("weight", Matchers.is(1))//---> Проверка Body по key и value в json
                .body("job", Matchers.is("leader"))
                .body("date", Matchers.is("11.05.2015"));//---> Проверка Body по key и value в json
    }
}
