package za.ac.cput.assignment6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import za.ac.cput.assignment6.domain.Contract;
import za.ac.cput.assignment6.domain.ContractDetails;

public class enterContractDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_contract_details);
    }

    protected void openAddContract(View view)
    {
        EditText IdCheckNum = (EditText)findViewById(R.id.editTextIdCheckNum);
        EditText DetailsCheckNum = (EditText)findViewById(R.id.editTextDetailsCheckNum);
        EditText contractType = (EditText)findViewById(R.id.editTextcontractType);
        EditText contractNum = (EditText)findViewById(R.id.editTextcontractNum);

        ContractDetails newContractDetails = new ContractDetails.Builder()
                .contractType(contractType.getText().toString())
                .contractNum(Integer.parseInt(contractNum.getText().toString()))
                .build();

        Contract newContract = new Contract.Builder()
                .IdCheckNum(Integer.parseInt(IdCheckNum.getText().toString()))
                .DetailsCheckNum(Integer.parseInt(DetailsCheckNum.getText().toString()))
                .contractDetails(newContractDetails)
                .build();

        Intent contractActivity = new Intent(this, addContractActivity.class);
        contractActivity.putExtra("IdCheckNum", newContract.getIdCheckNum());
        contractActivity.putExtra("DetailsCheckNum", newContract.getDetailsCheckNum());
        contractActivity.putExtra("contractType", newContract.getContractDetails().getContractType());
        contractActivity.putExtra("contractNum", newContract.getContractDetails().getContractNum());

        startActivity(contractActivity);
    }
}
