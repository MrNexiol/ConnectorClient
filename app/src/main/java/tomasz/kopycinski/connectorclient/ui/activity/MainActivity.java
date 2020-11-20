package tomasz.kopycinski.connectorclient.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tomasz.kopycinski.connectorclient.api.model.Contact;
import tomasz.kopycinski.connectorclient.api.model.GISTResponse;
import tomasz.kopycinski.connectorclient.api.service.GISTResponseService;
import tomasz.kopycinski.connectorclient.R;
import tomasz.kopycinski.connectorclient.ui.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_recycler);
        adapter = new RecyclerViewAdapter(contacts, MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tk-connector-api.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        GISTResponseService service = retrofit.create(GISTResponseService.class);
        Call<GISTResponse> callAsync = service.getGISTResponse();

        callAsync.enqueue(new Callback<GISTResponse>() {
            @Override
            public void onResponse(Call<GISTResponse> call, Response<GISTResponse> response) {
                if (response != null){
                    contacts = response.body().getContacts();
                    adapter = new RecyclerViewAdapter(contacts, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GISTResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}