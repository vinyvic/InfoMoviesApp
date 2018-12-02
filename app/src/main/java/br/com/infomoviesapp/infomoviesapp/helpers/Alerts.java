package br.com.infomoviesapp.infomoviesapp.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import br.com.infomoviesapp.infomoviesapp.R;

public class Alerts {
    private Activity activity;

    public Alerts (Context context){
        this.activity = (Activity) context;
    }

    public void noConnection(){
        final AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle(R.string.noConnectionTitle)
                .setMessage(activity.getString(R.string.noConnectionMessage, activity.getString(R.string.tryAgain).toUpperCase()))
                .setPositiveButton(R.string.connectOnInternet, null)  //Set to null. We override the onclick
                .setNegativeButton(R.string.tryAgain,null)  //Set to null. We override the onclick
                .setNeutralButton(R.string.exit, null) //Set to null. We override the onclick
                .setCancelable(false)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button buttonPositive = (dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                Button buttonNeutral = (dialog).getButton(AlertDialog.BUTTON_NEUTRAL);
                Button buttonNegative = (dialog).getButton(AlertDialog.BUTTON_NEGATIVE);


                // Botão Positivo
                buttonPositive.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                       activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                });

                // Botão Neutro
                buttonNeutral.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        activity.finish();
                    }
                });

                // Botão Negativo
                buttonNegative.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        activity.recreate();
                    }
                });


            }
        });
        dialog.show();
    }

}
