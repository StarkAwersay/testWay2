package steps;

import constants.Constants;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.core5.http.HttpStatus;
import pojo_сlass.Posts;

import static io.restassured.http.ContentType.JSON;

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
                .setContentType(JSON)
                .setAccept(JSON)
                .setAuth(setPreemptiveBasicAuthScheme())
                .build();
    }

    @Step("Создание поста и получение его id")
    public static Integer createPost() {
        return RestAssured.given()
                .spec(requestSpecification())
                .auth()
                .preemptive()
                .basic(Constants.API_LOGIN, Constants.API_PASSWORD)
                .body(new Posts("leader", "test", "publish"))
                .post(Constants.END_POINT)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response()
                .body()
                .path("id");
    }
}
