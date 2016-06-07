package za.ac.cput.assignment6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class homeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void enterContractDetailsActivity(View view)
    {
        Toast.makeText(homeActivity.this, "WELCOME", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (this, enterContractDetailsActivity.class);
        startActivity(intent);
    }
}
