package tests.ApiTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojoClass.Posts;
import rowMappers.PostRowMapper;
import tables.Post;

import static steps.ApiSteps.requestSpecification;

@Epic("Api Тесты сайта test (wordpress)")
public class ApiTests extends BasicApiTestClass {

    private JdbcTemplate jdbcTemplate;

    @BeforeTest
    public void beforeApiTest() {
        jdbcTemplate = new JdbcTemplate(BasicApiTestClass.mysqlDataSource());
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты апи")
    @Story("Тест добавление поста")
    @Test
    public void createPostTest() {
        jdbcTemplate.update("delete from wp_posts");
        RestAssured.given()
                .spec(requestSpecification())
                .auth()
                .preemptive()
                .basic("test", "qwerreverse1337")//---> Указание RequestSpecification для формирования request
                .body(new Posts("leader", "test", "publish"))//---> body для запроса с методом POST
                .post("/index.php?rest_route=/wp/v2/posts")//---> Endpoint для выполнения запроса GET
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        Post post = jdbcTemplate.queryForObject("SELECT *\n" +
                "from wp_posts wp\n", new PostRowMapper());
        Assert.assertEquals("leader", post.getPost_title());
        Assert.assertEquals("test", post.getPast_password());
        Assert.assertEquals("publish", post.getPost_status());
        jdbcTemplate.update("delete from wp_posts");
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты апи")
    @Story("Тест удаление поста")
    @Test()
    public void deletePostTest() {
        jdbcTemplate.update("delete from wp_posts");
        Integer id;
        id = RestAssured.given()
                .spec(requestSpecification())
                .auth()
                .preemptive()
                .basic("test", "qwerreverse1337")//---> Указание RequestSpecification для формирования request
                .body(new Posts("leader", "test", "publish"))//---> body для запроса с методом POST
                .post("/index.php?rest_route=/wp/v2/posts")//---> Endpoint для выполнения запроса GET
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response()
                .body()
                .path("id");
        Post post = jdbcTemplate.queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + id, new PostRowMapper());
        Assert.assertEquals("publish", post.getPost_status());
        RestAssured.given()
                .spec(requestSpecification())
                .auth()
                .preemptive()
                .basic("test", "qwerreverse1337")
                .delete("/index.php?rest_route=/wp/v2/posts/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK);
        Post delete = jdbcTemplate.queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + id, new PostRowMapper());
        Assert.assertEquals("trash", delete.getPost_status());
        jdbcTemplate.update("delete from wp_posts");
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты апи")
    @Story("Тест обновление поста")
    @Test()
    public void updatePostTest() {
        jdbcTemplate.update("delete from wp_posts");
        int id;
        id = RestAssured.given()
                .spec(requestSpecification())
                .auth()
                .preemptive()
                .basic("test", "qwerreverse1337")//---> Указание RequestSpecification для формирования request
                .body(new Posts("leader", "test", "publish"))//---> body для запроса с методом POST
                .post("/index.php?rest_route=/wp/v2/posts")//---> Endpoint для выполнения запроса GET
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response()
                .body()
                .path("id");
        Post post = jdbcTemplate.queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + id, new PostRowMapper());
        Assert.assertEquals("leader", post.getPost_title());
        Assert.assertEquals("test", post.getPast_password());
        Assert.assertEquals("publish", post.getPost_status());
        RestAssured.given()
                .spec(requestSpecification())
                .auth()
                .preemptive()
                .basic("test", "qwerreverse1337")
                .body(new Posts("leaders", "tests", "publish"))
                .post("/index.php?rest_route=/wp/v2/posts/" + (id))
                .then()
                .statusCode(HttpStatus.SC_OK);
        Post upd = jdbcTemplate.queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + id, new PostRowMapper());
        Assert.assertEquals("leaders", upd.getPost_title());
        Assert.assertEquals("tests", upd.getPast_password());
        Assert.assertEquals("publish", post.getPost_status());
        jdbcTemplate.update("delete from wp_posts");
    }
}
