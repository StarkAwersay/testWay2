package tests.api;

import constants.Constants;
import helpers.JdbcTemplateHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.Posts;
import rowMappers.PostRowMapper;
import tables.Post;

import static steps.ApiSteps.requestSpecification;

@Epic("Api Тесты сайта test (wordpress)")
public class CreatePost {
    @BeforeMethod
    public void beforeApiTest() {
        JdbcTemplateHelper.jdbcTemplate().update("delete from wp_posts");
    }

    @Feature("Тесты апи")
    @Story("Тест добавление поста")
    @Test
    public void createPostTest() {
        Posts parametersPost = new Posts("leader", "test", "publish");
        RestAssured.given()
                .spec(requestSpecification())
                .body(parametersPost)
                .post(Constants.END_POINT)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        Post post = JdbcTemplateHelper.getCreatedPostForCreateTest();
        Assert.assertEquals(parametersPost.getTitle(), post.getPostTitle());
        Assert.assertEquals(parametersPost.getPassword(), post.getPastPassword());
        Assert.assertEquals(parametersPost.getStatus(), post.getPostStatus());
    }
}
