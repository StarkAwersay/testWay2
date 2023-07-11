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
import pojo.ApiPost;
import pojo.DbPost;
import pojo.Title;
import steps.ApiSteps;
import tables.Post;

import static org.assertj.core.api.Assertions.assertThat;
import static steps.ApiSteps.requestSpecification;

@Epic("Api Тесты сайта test (wordpress)")
public class UpdateDeleteGetPost extends BasicApiTestClass {
    /**
     * idCreatePost.
     */
    private static Integer idCreatePost;

    @BeforeMethod
    public void getIdCreatedPost() {
        idCreatePost = JdbcTemplateHelper.createPost();
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
        Post actualPost = JdbcTemplateHelper.getPostByID(idCreatePost);
        Assert.assertEquals("trash", actualPost.getPostStatus());
    }

    @Feature("Тесты апи")
    @Story("Тест обновление поста")
    @Test()
    public void updatePostTest() {
        DbPost expectedPost = new DbPost("leaders", "tests", "publish");
        RestAssured.given()
                .spec(requestSpecification())
                .body(expectedPost)
                .post(Constants.END_POINT + idCreatePost)
                .then()
                .statusCode(HttpStatus.SC_OK);
        Post actualPost = JdbcTemplateHelper.getPostByID(idCreatePost);
        Assert.assertEquals(expectedPost.getTitle(), actualPost.getPostTitle());
        Assert.assertEquals(expectedPost.getPassword(), actualPost.getPastPassword());
    }

    @Feature("Тесты апи")
    @Story("Тест метода get")
    @Test
    public void getPost() {
        ApiPost postsRoot = ApiSteps.getPostsRoot(idCreatePost);
        Assert.assertEquals(postsRoot.getStatus(), "publish");
        assertThat(postsRoot.getTitle()).extracting(Title::getRendered).isEqualTo("test");
    }
}
