package Listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import BackgroundTasks.UserGatewayBackgroundTask;
import Fragments.CreateAccountStepOneFragment;
import Fragments.ForgotPasswordFragment;
import Utilities.TextSanitizer;
import allblacks.com.ibaleka_android_prototype.MainActivity;
import allblacks.com.ibaleka_android_prototype.R;

/**
 * Created by Okuhle on 5/26/2016.
 */
public class LoginButtonListener implements View.OnClickListener {

    private Activity currentContext;
    private TextView toolbarTextView;
    private FragmentManager fragmentManager;
    private UserGatewayBackgroundTask userGatewayTask;

    private EditText forgotPasswordEmailEditText;

    public LoginButtonListener(Activity currentContext) {
        this.currentContext = currentContext;
        toolbarTextView = (TextView) currentContext.findViewById(R.id.LoginActivityToolbarTextView);
        fragmentManager = currentContext.getFragmentManager();
        userGatewayTask = new UserGatewayBackgroundTask(currentContext);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                Intent mainActivity = new Intent(currentContext, MainActivity.class);
                currentContext.startActivity(mainActivity);
                currentContext.finish();
                break;
            case R.id.registerAccountbtn:
                CreateAccountStepOneFragment registerAccountFragment = new
                        CreateAccountStepOneFragment();
                FragmentTransaction createAccountTransaction = fragmentManager.beginTransaction();
                createAccountTransaction.replace(R.id.LoginActivityContentArea,
                        registerAccountFragment, "RegisterFragmentStepOne");
                createAccountTransaction.addToBackStack("RegisterFragmentStepOne");
                createAccountTransaction.commit();
                toolbarTextView.setText("Register Account - Step 1 of 2");
                break;
            case R.id.forgotPasswordButton:
                ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
                FragmentTransaction forgotPasswordTransaction = fragmentManager.beginTransaction();
                forgotPasswordTransaction.replace(R.id.LoginActivityContentArea,
                        forgotPasswordFragment, "ForgotPasswordFragment");
                toolbarTextView.setText("Reset Your Password");
                forgotPasswordTransaction.addToBackStack("ForgotPasswordFragment");
                forgotPasswordTransaction.commit();
                break;
            case R.id.ForgotPasswordNextStepButton:
                forgotPasswordEmailEditText = (EditText) currentContext.findViewById(R.id
                        .ForgotPasswordEmailEditText);
                String enteredEmail = TextSanitizer.sanitizeText(forgotPasswordEmailEditText
                        .getText().toString(), true);
                boolean isValidText = TextSanitizer.isValidText(enteredEmail, 10, 100);
                if (isValidText) {
                    userGatewayTask.setExecutionMode(1); //Forgot Password Action
                    userGatewayTask.execute(enteredEmail);


                } else {
                    displayMessage("Invalid Email Entered", "Please enter an email address that " +
                            "is betweeen 10 and 100 characters");
                }

                break;
        }

    }

    public void displayMessage(String title, String message) {
        AlertDialog.Builder messageBox = new AlertDialog.Builder(currentContext);
        messageBox.setTitle(title);
        messageBox.setMessage(message);
        messageBox.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        messageBox.show();
    }
}
