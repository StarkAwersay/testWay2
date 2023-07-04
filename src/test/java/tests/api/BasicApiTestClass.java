package tests.api;

import helpers.JdbcTemplateHelper;
import org.testng.annotations.BeforeMethod;

public class BasicApiTestClass {
    @BeforeMethod
    public void beforeApiTest() {
        JdbcTemplateHelper.jdbcTemplate().update("delete from wp_posts");
    }
}
