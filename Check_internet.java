package com.example.filenber.my_new_app;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.AccessControlContext;

/**
 * Created by filenber on 15/11/2018.
 */
public class Check_internet extends AsyncTask<String,Void,Integer> {
    Context context;

    public Check_internet(Context context) {
        this.context=context;
    }



    public  boolean isConnected()
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Service.CONNECTIVITY_SERVICE);

        if (connectivityManager!=null)
        {
            NetworkInfo info=connectivityManager.getActiveNetworkInfo();
            if (info!=null)
            {
                if (info.getState()==NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    protected Integer doInBackground(String... params) {

        Integer result=0;
        try {
            Socket socket=new Socket();
            SocketAddress socketAddress=new InetSocketAddress("8.8.8.8",53);
            socket.connect(socketAddress,1500);
            socket.close();
            result=1;
        } catch (IOException e) {
            e.printStackTrace();
            result=0;
        }

        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (isConnected())
        {
            if (result==1)
            {
                Toast.makeText(context, "  internet available ", Toast.LENGTH_SHORT).show();
            }

            if(result==0)
            {
                 AlertDialog alertDialog = new android.app.AlertDialog.Builder(context).create();
                alertDialog.setTitle("No Internet Connection");

                alertDialog.setMessage("Your Order sent Successfully");

                alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        }
        else
        {
            Toast.makeText(context, " No internet available ", Toast.LENGTH_SHORT).show();
        }
        super.onPostExecute(result);
    }
}