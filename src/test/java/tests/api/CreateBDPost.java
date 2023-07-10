package tests.api;

import helpers.JdbcTemplateHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.ApiPost;
import pojo.Title;
import steps.ApiSteps;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Api Тесты сайта test (wordpress)")
public class CreateBDPost extends BasicApiTestClass {

    @Feature("Тесты апи")
    @Story("Тест добавление поста через БД")
    @Test
    public void createPost() {
        ApiPost postsRoot = ApiSteps.getPostsRoot(JdbcTemplateHelper.getCreatedPostId());
        Assert.assertEquals(postsRoot.getStatus(), "publish");
        assertThat(postsRoot.getTitle()).extracting(Title::getRendered).isEqualTo("test");
    }
}
