package Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import allblacks.com.ibaleka_android_prototype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountStepOneFragment extends Fragment {

    private EditText nameEditText, surnameEditText, emailEditText;
    private Spinner athleteTypeSpinner;
    private ArrayAdapter spinnerAdapter;
    private Button nextStepButton;

    public CreateAccountStepOneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_create_account_step_one, container, false);
        nameEditText = (EditText) myView.findViewById(R.id.NameEditText);
        surnameEditText = (EditText) myView.findViewById(R.id.SurnameEditText);
        emailEditText = (EditText) myView.findViewById(R.id.EmailEditText);
        athleteTypeSpinner = (Spinner) myView.findViewById(R.id.AccountTypeSpinner);
        nextStepButton = (Button) myView.findViewById(R.id.NextStepButton);
        spinnerAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R
                .array.account_array, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        athleteTypeSpinner.setAdapter(spinnerAdapter);

        return myView;
    }

    @Override
    public void onPause() {
        setRetainInstance(true);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getRetainInstance();
    }
}
