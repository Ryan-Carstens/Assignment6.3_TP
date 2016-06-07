package za.ac.cput.assignment6;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import za.ac.cput.assignment6.conf.databases.GlobalContext;
import za.ac.cput.assignment6.domain.Contract;
import za.ac.cput.assignment6.domain.ContractDetails;
import za.ac.cput.assignment6.services.Impl.ContractServiceImpl;

public class addContractActivity extends AppCompatActivity {

    private ContractServiceImpl service;
    private boolean isBound;
    private Contract addObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contract);

        Intent myIntent = getIntent();

        TextView IdCheckNum = (TextView) findViewById(R.id.IdCheckNumDisplayView);
        TextView DetailsCheckNum = (TextView) findViewById(R.id.DetailsCheckNumDisplayView);
        TextView contractType = (TextView) findViewById(R.id.contractTypeDisplayView);
        TextView contractNum = (TextView) findViewById(R.id.contractNumDisplayView);

        ContractDetails theContractDetails = new ContractDetails.Builder()
                .contractType(myIntent.getStringExtra("contractType"))
                .contractNum(myIntent.getIntExtra("contractNum",123))
                .build();

        Contract theObject = new Contract.Builder()
                .IdCheckNum(myIntent.getIntExtra("IdCheckNum",1))
                .DetailsCheckNum(myIntent.getIntExtra("DetailsCheckNum",1))
                .contractDetails(theContractDetails)
                .build();

        addObject = new Contract.Builder()
                .copy(theObject)
                .id(null)
                .build();

        IdCheckNum.setText(theObject.getIdCheckNum()+"");
        DetailsCheckNum.setText(theObject.getDetailsCheckNum()+"");
        contractType.setText(theObject.getContractDetails().getContractType()+"");
        contractNum.setText(theObject.getContractDetails().getContractNum() + "");
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder localService) {
            ContractServiceImpl.ActivateServiceLocalBinder binder
                    = (ContractServiceImpl.ActivateServiceLocalBinder) localService;
            service = binder.getService();
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onStart()
    {
        super.onStart();
        Intent intent = new Intent(this, ContractServiceImpl.class);


        GlobalContext.context = this;
        service = ContractServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public void addContract(View view)
    {
        addObject = service.storeContract(addObject);

        if(addObject.getId() != null) {
            Toast.makeText(addContractActivity.this, "SUCCESSFULLY ADDED LIVING AREA", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, viewContractActivity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(addContractActivity.this,"COULD NOT ADD RECORD",Toast.LENGTH_LONG).show();
    }


}
