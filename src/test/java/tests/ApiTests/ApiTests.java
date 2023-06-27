package tests.ApiTests;

import constants.Constants;
import helpers.JdbcTemplateHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo_Class.Posts;
import helpers.SqlConnection;
import steps.ApiSteps;
import tables.Post;

import static steps.ApiSteps.requestSpecification;

@Epic("Api Тесты сайта test (wordpress)")
public class ApiTests extends SqlConnection {

    @BeforeTest
    public void beforeApiTest() {
        JdbcTemplateHelper.jdbcTemplateUpdate(SqlConnection.mysqlDataSource(), "delete from wp_posts");
    }

    @Feature("Тесты апи")
    @Story("Тест добавление поста")
    @Test
    public void createPostTest() {
        RestAssured.given()
                .spec(requestSpecification())
                .body(new Posts("leader", "test", "publish"))
                .post(Constants.END_POINT)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        Post post = JdbcTemplateHelper.jdbcTemplateGetObject(SqlConnection.mysqlDataSource(), "SELECT *\n" +
                "from wp_posts wp\n");
        Assert.assertEquals("leader", post.getPostTitle());
        Assert.assertEquals("test", post.getPastPassword());
        Assert.assertEquals("publish", post.getPostStatus());
    }

    @Feature("Тесты апи")
    @Story("Тест удаление поста")
    @Test()
    public void deletePostTest() {
        Integer id = ApiSteps.getIdCreatedPost();
        Post post = JdbcTemplateHelper.jdbcTemplateGetObject(SqlConnection.mysqlDataSource(), "SELECT *\n" +
                "from wp_posts wp where id like" + " " + id);
        Assert.assertEquals("publish", post.getPostStatus());
        RestAssured.given()
                .spec(requestSpecification())
                .delete(Constants.END_POINT + id)
                .then()
                .statusCode(HttpStatus.SC_OK);
        Post delete = JdbcTemplateHelper.jdbcTemplateGetObject(SqlConnection.mysqlDataSource(), "SELECT *\n" +
                "from wp_posts wp where id like" + " " + id);
        Assert.assertEquals("trash", delete.getPostStatus());
    }

    @Feature("Тесты апи")
    @Story("Тест обновление поста")
    @Test()
    public void updatePostTest() {
        Integer id = ApiSteps.getIdCreatedPost();
        Post post = JdbcTemplateHelper.jdbcTemplateGetObject(SqlConnection.mysqlDataSource(), "SELECT *\n" +
                "from wp_posts wp where id like" + " " + id);
        Assert.assertEquals("leader", post.getPostTitle());
        Assert.assertEquals("test", post.getPastPassword());
        Assert.assertEquals("publish", post.getPostStatus());
        RestAssured.given()
                .spec(requestSpecification())
                .body(new Posts("leaders", "tests", "publish"))
                .post(Constants.END_POINT + (id))
                .then()
                .statusCode(HttpStatus.SC_OK);
        Post upd = JdbcTemplateHelper.jdbcTemplateGetObject(SqlConnection.mysqlDataSource(), "SELECT *\n" +
                "from wp_posts wp where id like" + " " + id);
        Assert.assertEquals("leaders", upd.getPostTitle());
        Assert.assertEquals("tests", upd.getPastPassword());
        Assert.assertEquals("publish", post.getPostStatus());
    }
}
