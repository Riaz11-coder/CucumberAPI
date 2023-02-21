package apiEngine;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class RestResponse<T>  implements IRestResponse<T> {
ObjectMapper objectMapper = new ObjectMapper();
    private T data;
    private Response response;
    private Exception e;

    public RestResponse(Class<T> t, Response response) {
        this.response = response;
        try{
            this.data = t.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("There should be a default constructor in the Response POJO");

        }
    }

    public String getContent() {
        return response.getBody().asString();
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public boolean isSuccessful() {
        int code = response.getStatusCode();
        if( code == 200 || code == 201 || code == 202 || code == 203 || code == 204 || code == 205) return true;
        return false;
    }

    public String getStatusDescription() {
        return response.getStatusLine();
    }

    public Response getResponse() {
        return response;
    }


    public T getBody()  {
        try {
            data = (T) response.body().as(data.getClass());

        }catch (Exception e) {
            this.e=e;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally {
            data = (T) response.body().as(data.getClass());
        }


        return data;
    }

    public Exception getException() {
        return e;
    }
}
