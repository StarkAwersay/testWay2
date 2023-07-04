package tests.api;

import constants.Constants;
import helpers.JdbcTemplateHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.Posts;
import rowMappers.PostRowMapper;
import steps.ApiSteps;
import tables.Post;

import static steps.ApiSteps.requestSpecification;

@Epic("Api Тесты сайта test (wordpress)")
public class UpdateAndDeletePost {

    private static Integer idCreatePost;

    @BeforeEach
    public void beforeApiTest() {
        JdbcTemplateHelper.jdbcTemplate().update("delete from wp_posts");
    }

    @BeforeMethod
    public void getIdCreatedPost() {
        idCreatePost = ApiSteps.createPost();
    }


    @Feature("Тесты апи")
    @Story("Тест удаление поста")
    @Test()
    public void deletePostTest() {
        RestAssured.given()
                .spec(requestSpecification())
                .delete(Constants.END_POINT + idCreatePost)
                .then()
                .statusCode(HttpStatus.SC_OK);
        Post delete = JdbcTemplateHelper.jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + idCreatePost, new PostRowMapper());
        Assert.assertEquals("trash", delete.getPostStatus());
    }

    @Feature("Тесты апи")
    @Story("Тест обновление поста")
    @Test()
    public void updatePostTest() {
        Posts updatedPost = new Posts("leaders", "tests", "publish");
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
