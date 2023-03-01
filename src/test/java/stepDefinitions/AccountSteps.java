package stepDefinitions;

import apiEngine.IRestResponse;
import apiEngine.model.requests.AuthorizationRequest;
import apiEngine.model.responses.UserAccount;
import cucumber.TestContext;
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

    @When("I send positive credentials in POST request")
    public void iSendPositiveCredentialsInPOSTRequest() {
        AuthorizationRequest authRequest = new AuthorizationRequest("Testing1234!", "passwordTest1234!");
        IRestResponse<UserAccount> userAccountResponse = getEndPoints().authorizedUser(authRequest);
        getScenarioContext().setContext(Context.USER_ACCOUNT_RESPONSE,userAccountResponse);
    }
}
