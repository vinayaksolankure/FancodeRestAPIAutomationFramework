package fancode.apiendpoints;

import fancode.utilities.ReadConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiClient {
	private static ReadConfig readConfig = new ReadConfig();
    private static final String BASE_URL = readConfig.getBaseUrl();

    public static Response getUsers() {
        return RestAssured.get(BASE_URL + "/users");
    }

    public static Response getTodos() {
        return RestAssured.get(BASE_URL + "/todos");
    }
}
