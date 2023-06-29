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
import pojo.Posts;
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
        Integer postId = ApiSteps.createPost();
        Post post = JdbcTemplateHelper.jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + postId, new PostRowMapper());
        Assert.assertEquals("publish", post.getPostStatus());
        RestAssured.given()
                .spec(requestSpecification())
                .delete(Constants.END_POINT + postId)
                .then()
                .statusCode(HttpStatus.SC_OK);
        Post delete = JdbcTemplateHelper.jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + postId, new PostRowMapper());
        Assert.assertEquals("trash", delete.getPostStatus());
    }

    @Feature("Тесты апи")
    @Story("Тест обновление поста")
    @Test()
    public void updatePostTest() {
        Posts updatedPost = new Posts("leaders", "tests", "publish");
        Integer postId = ApiSteps.createPost();
        JdbcTemplateHelper.jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + postId, new PostRowMapper());
        Integer oldId = ApiSteps.createPost();
        RestAssured.given()
                .spec(requestSpecification())
                .body(updatedPost)
                .post(Constants.END_POINT + oldId)
                .then()
                .statusCode(HttpStatus.SC_OK);
        Post upd = JdbcTemplateHelper.jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + oldId, new PostRowMapper());
        Assert.assertEquals(updatedPost.getTitle(), upd.getPostTitle());
        Assert.assertEquals(updatedPost.getPassword(), upd.getPastPassword());
    }
}
