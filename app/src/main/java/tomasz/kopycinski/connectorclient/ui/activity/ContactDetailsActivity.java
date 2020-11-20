package tomasz.kopycinski.connectorclient.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tomasz.kopycinski.connectorclient.R;
import tomasz.kopycinski.connectorclient.api.model.Contact;
import tomasz.kopycinski.connectorclient.api.model.GISTResponse;
import tomasz.kopycinski.connectorclient.api.service.GISTResponseService;
import tomasz.kopycinski.connectorclient.ui.RecyclerViewAdapter;

public class ContactDetailsActivity extends AppCompatActivity {

    public static String CONTACT_ID = "contactID";

    private TextView name;
    private TextView company;
    private TextView job;
    private TextView phone;
    private TextView mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        name = findViewById(R.id.contact_name);
        company = findViewById(R.id.contact_company);
        job = findViewById(R.id.contact_job);
        phone = findViewById(R.id.contact_phone);
        mobile = findViewById(R.id.contact_mobile);

        Intent intent = getIntent();
        int id = intent.getIntExtra(CONTACT_ID, 0);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tk-connector-api.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        GISTResponseService service = retrofit.create(GISTResponseService.class);
        Call<Contact> callAsync = service.getContact(id);

        callAsync.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                String full_name = response.body().getFirst_name() + " " + response.body().getLast_name();
                name.setText(full_name);
                company.setText(response.body().getCompany_name());
                job.setText(response.body().getJob_title());
                phone.setText(response.body().getPhone_number());
                mobile.setText(response.body().getMobile_phone_number());
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}