package steps;

import constants.Constants;
import io.qameta.allure.Step;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.core5.http.HttpStatus;
import pojo.ApiPost;
import pojo.DbPost;

import static io.restassured.RestAssured.given;

public class ApiSteps {
    private static PreemptiveBasicAuthScheme setPreemptiveBasicAuthScheme() {
        final PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(Constants.API_LOGIN);
        authScheme.setPassword(Constants.API_PASSWORD);
        return authScheme;
    }

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(Constants.BASE_URI)
                .setContentType(ContentType.JSON)
                .setAuth(setPreemptiveBasicAuthScheme())
                .build();
    }

    @Step("Создание поста и получение его id")
    public static Integer createPost() {
        return given()
                .spec(requestSpecification())
                .auth()
                .preemptive()
                .basic(Constants.API_LOGIN, Constants.API_PASSWORD)
                .body(new DbPost("leader", "test", "publish"))
                .post(Constants.END_POINT)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response()
                .body()
                .path("id");
    }

    @Step("Отправка Get запроса для получения root")
    public static ApiPost getPostsRoot(Integer id) {
        return given().spec(requestSpecification())
                .when().get(Constants.END_POINT + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().as(ApiPost.class);
    }
}
