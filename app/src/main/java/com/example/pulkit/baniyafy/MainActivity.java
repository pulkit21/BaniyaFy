package com.example.pulkit.baniyafy;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iangclifton.android.floatlabel.FloatLabel;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenant_sign_in);
        final EditText flStoreName = ((FloatLabel) findViewById(R.id.flStoreName)).getEditText();
        final EditText flTenantEmail = ((FloatLabel) findViewById(R.id.flTenantEmail)).getEditText();
        final EditText flTenantPassword = ((FloatLabel) findViewById(R.id.flTenantPassword)).getEditText();
        Button tenantSignIn = (Button) findViewById(R.id.tenantSignIn);

        tenantSignIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String storeName = flStoreName.getText().toString();
                        final String tenantEmail = flTenantEmail.getText().toString();
                        final String tenantPassword = flTenantPassword.getText().toString();

                        new FetchUserTask().execute(storeName, tenantEmail,tenantPassword);
                    }
                }
        );

    }

    public class FetchUserTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String storeName = params[0];
            String tenantEmail = params[1];
            String tenantPassword = params[2];

            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://" + storeName +".baniyafy.in")
                    .build();
            BaniyafyServices baniyafyServices = restAdapter.create(BaniyafyServices.class);

            Callback<Object> userSignInCallback = new Callback<Object>() {
                @Override
                public void success(Object o, Response response) {
                    if (response.getStatus() == 201) {
                        signInSuccessful();
                    }
                }

                private void signInSuccessful() {
                    Toast.makeText(getBaseContext(),"You are successfully signin",Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    if (error.getResponse() != null) {
                        RestError body = (RestError) error.getBodyAs(RestError.class);
                        switch (body.code) {
                            case 401:
                                body.errorDetails.getBytes();
                                return;
                        }
                    }
                }
            };

            UserObject userObject = new UserObject();
            User user = new User();
            user.email = tenantEmail;
            user.password = tenantPassword;
            user.login_for = "admin";
            userObject.user = user;


            baniyafyServices.signInUser(userObject, userSignInCallback);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
