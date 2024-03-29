package tests.api;

import constants.Constants;
import helpers.JdbcTemplateHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.DbPost;
import tables.Post;

import static steps.ApiSteps.requestSpecification;

@Epic("Api Тесты сайта test (wordpress)")
public class CreatePost extends BasicApiTestClass {

    @Feature("Тесты апи")
    @Story("Тест добавление поста")
    @Test
    public void createPostTest() {
        DbPost parametersPost = new DbPost("leader", "test", "publish");
        Integer id = RestAssured.given()
                .spec(requestSpecification())
                .body(parametersPost)
                .post(Constants.END_POINT)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response()
                .body()
                .path("id");
        Post actualPost = JdbcTemplateHelper.getPostByID(id);
        Assert.assertEquals(parametersPost.getTitle(), actualPost.getPostTitle());
        Assert.assertEquals(parametersPost.getPassword(), actualPost.getPastPassword());
        Assert.assertEquals(parametersPost.getStatus(), actualPost.getPostStatus());
    }
}
