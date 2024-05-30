package stepDefinitions;

import apiEngine.IRestResponse;
import apiEngine.model.requests.AuthorizationRequest;
import apiEngine.model.requests.LoginRequest;
import apiEngine.model.responses.LoginResponse;
import apiEngine.model.responses.UserAccount;
import cucumber.TestContext;
import dataProvider.ConfigReader;
import enums.Context;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class AccountSteps extends BaseStep{
    public AccountSteps(TestContext testContext){
        super(testContext);
    }

    @Given("^I am an authorized user$")
    public void iAmAnAuthorizedUser() {
        AuthorizationRequest authRequest = new AuthorizationRequest("Testing1234!", "passwordTest1234!");
        getEndPoints().authenticateUser(authRequest);
    }
    @When("I provide valid login details \\(username and password)")
    public void iProvideValidLoginDetailsUsernameAndPassword() {
        LoginRequest loginRequest = new LoginRequest(ConfigReader.getInstance().getEmail(), ConfigReader.getInstance().getPassword());
        IRestResponse<LoginResponse> loginResponse = getEndPoints().AuthenticateUser(loginRequest);
        getScenarioContext().setContext(Context.LOGIN_RESPONSE,loginResponse);
    }

    @When("I send positive credentials in POST request")
    public void iSendPositiveCredentialsInPOSTRequest() {
        AuthorizationRequest authRequest = new AuthorizationRequest("Testing1234!", "passwordTest1234!");
        IRestResponse<UserAccount> userAccountResponse = getEndPoints().authorizedUser(authRequest);
        getScenarioContext().setContext(Context.USER_ACCOUNT_RESPONSE,userAccountResponse);
    }


    @When("I provide invalid login details \\(username and password),")
    public void iProvideInvalidLoginDetailsUsernameAndPassword() {
        LoginRequest loginRequest = new LoginRequest(ConfigReader.getInstance().getEmail(), ConfigReader.getInstance().getPassword());
        IRestResponse<LoginResponse> loginResponse = getEndPoints().AuthenticateUser(loginRequest);
        getScenarioContext().setContext(Context.LOGIN_RESPONSE,loginResponse);
    }
}
