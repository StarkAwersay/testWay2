package tests.Api;

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
import pojo_сlass.Posts;
import rowMappers.PostRowMapper;
import steps.ApiSteps;
import tables.Post;

import static steps.ApiSteps.requestSpecification;

@Epic("Api Тесты сайта test (wordpress)")
public class ApiTests {

    @BeforeTest
    public void beforeApiTest() {
        JdbcTemplateHelper.jdbcTemplate().update("delete from wp_posts");
    }

    @BeforeTest
    public Integer getIdCreatedPost() {
        return ApiSteps.createPost();
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
        Post post = JdbcTemplateHelper.jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp\n", new PostRowMapper());
        Assert.assertEquals("leader", post.getPostTitle());
        Assert.assertEquals("test", post.getPastPassword());
        Assert.assertEquals("publish", post.getPostStatus());
    }

    @Feature("Тесты апи")
    @Story("Тест удаление поста")
    @Test()
    public void deletePostTest() {
        Post post = JdbcTemplateHelper.jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + getIdCreatedPost(), new PostRowMapper());
        Assert.assertEquals("publish", post.getPostStatus());
        RestAssured.given()
                .spec(requestSpecification())
                .delete(Constants.END_POINT + getIdCreatedPost())
                .then()
                .statusCode(HttpStatus.SC_OK);
        Post delete = JdbcTemplateHelper.jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + getIdCreatedPost(), new PostRowMapper());
        Assert.assertEquals("trash", delete.getPostStatus());
    }

    @Feature("Тесты апи")
    @Story("Тест обновление поста")
    @Test()
    public void updatePostTest() {
        JdbcTemplateHelper.jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + getIdCreatedPost(), new PostRowMapper());
        Integer oldId = ApiSteps.createPost();
        RestAssured.given()
                .spec(requestSpecification())
                .body(new Posts("leaders", "tests", "publish"))
                .post(Constants.END_POINT + oldId)
                .then()
                .statusCode(HttpStatus.SC_OK);
        Post upd = JdbcTemplateHelper.jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + oldId, new PostRowMapper());
        Assert.assertEquals("leaders", upd.getPostTitle());
        Assert.assertEquals("tests", upd.getPastPassword());
    }
}
