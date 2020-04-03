package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiRequests {
    static {
        RestAssured.baseURI = "http://dillali.lancertool.com/api";
    }

    public String getAuthToken(String email, String password){
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("username", email);
        userDetails.put("password", password);

       Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userDetails)
                .when()
                .post("/auth/login");
       String token = response.path("access_token").toString();
       return token;


    }

//    public Response apiUserCreateViaObj(ApiUser user){
//        Response response = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body("")
//                .when()
//                .post("/auth/login");
//        return response;
//    }



}
