package tomasz.kopycinski.connectorclient.api.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tomasz.kopycinski.connectorclient.api.model.Contact;
import tomasz.kopycinski.connectorclient.api.model.GISTResponse;

public interface GISTResponseService {

    @GET("/contacts")
    Call<GISTResponse> getGISTResponse();

    @GET("/contacts/{id}")
    Call<Contact> getContact(@Path("id") int id);
}
