package apiEngine;

import apiEngine.model.requests.AddBooksRequest;
import apiEngine.model.requests.AuthorizationRequest;
import apiEngine.model.requests.LoginRequest;
import apiEngine.model.requests.RemoveBookRequest;
import apiEngine.model.responses.Books;
import apiEngine.model.responses.LoginResponse;
import apiEngine.model.responses.Token;
import apiEngine.model.responses.UserAccount;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class EndPoints {


    private final RequestSpecification request;



    public EndPoints(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("accept","ContentType.JSON");
    }

    public void authenticateUser(AuthorizationRequest authRequest) {
        Response response = request.body(authRequest).post(Route.generateToken());
        if (response.statusCode() != HttpStatus.SC_OK)
            throw new RuntimeException("Authentication Failed. Content of failed Response: " + response.toString() + " , Status Code : " + response.statusCode());

        Token tokenResponse = response.body().jsonPath().getObject("$", Token.class);
        request.header("Authorization", "Bearer " + tokenResponse.token);
    }

    public IRestResponse<Books> getBooks() {
        Response response = request.get(Route.books());
        return new  RestResponse<>(Books.class, response);
    }

    public IRestResponse<UserAccount> addBook(AddBooksRequest addBooksRequest) {
        Response response = request.body(addBooksRequest).post(Route.books());
        return new RestResponse<>(UserAccount.class, response);
    }

    public Response removeBook(RemoveBookRequest removeBookRequest) {
        return request.body(removeBookRequest).delete(Route.book());
    }

    public IRestResponse<Books> getBookISBN(){
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("ISBN","9781449365035");
        Response response = request.queryParams(paramsMap).get(Route.book());
        return new RestResponse<>(Books.class, response);
    }

    public IRestResponse<UserAccount> authorizedUser(AuthorizationRequest authRequest){
        Response response = request.body(authRequest).post(Route.authorized());
        return new RestResponse<>(UserAccount.class,response);

    }


    public IRestResponse<LoginResponse> AuthenticateUser(LoginRequest loginRequest){
        Response response = request.body(loginRequest).post(Route.login());
        return new RestResponse<>(LoginResponse.class,response);
    }

    public IRestResponse<UserAccount> getUserAccount(String userId) {
        Response response = request.get(Route.userAccount(userId));
        return new RestResponse<>(UserAccount.class, response);
    }
}
