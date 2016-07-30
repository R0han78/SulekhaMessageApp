package mayankbatra.andro.idroid;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class SMS_Listner_for_Web extends BroadcastReceiver{


    Boolean isInternetPresent = false;
    ConnectionDetector CD;
    String date_time_of_sms;
    String SMS_Send_Status;
    String Message_Body = "", Message_Sender_Number;

    String Message_Body_For_Sulekha;
    UserListDbHelper uldh;

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);

        uldh = new UserListDbHelper(context, null, null, 3);
            uldh.createTable();

        CD = new ConnectionDetector(context);
        // get Internet status
        isInternetPresent = CD.isConnectingToInternet();
        if(isInternetPresent)
            Log.i("Internet","Connected");
            //Toast.makeText(context , "Internet Connected",Toast.LENGTH_SHORT).show();
        else
            Log.i("Not Connected","");
            //Toast.makeText(context , "Internet not Connected yet",Toast.LENGTH_SHORT).show();




        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {

            String temp = SP.getString("Cache_Status", "OFF");

            if (temp.equals("OFF"))
            {
                Toast.makeText(context,"Application in off mode ",Toast.LENGTH_LONG).show();

            } else
            {
                for (android.telephony.SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {

                    Message_Body = Message_Body + smsMessage.getMessageBody();
                    Message_Sender_Number = smsMessage.getOriginatingAddress();

                    // Toast.makeText(context, "Sender's Number : "+Message_Sender_Number+"\n"+"SMS Body : "+Message_Body, Toast.LENGTH_LONG).show();
                    //System.out.println("System SMS Body 1 " + Message_Body);

                }

                if (Message_Sender_Number.toString().equals("IM-SULTRN") || Message_Sender_Number.toString().equals("VM-SULTRN") || Message_Sender_Number.toString().equals("VK-SULTRN") || Message_Sender_Number.toString().equals("TD-SULEKH") || Message_Sender_Number.toString().equals("+919717460081")){

                    String Check = Message_Body.substring(0, 7);
                    // Toast.makeText(context,""+Check,Toast.LENGTH_LONG).show();
                    if(Check.equalsIgnoreCase("Enquiry"))
                    {
                        Calendar C = Calendar.getInstance();

                        SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        date_time_of_sms = DF.format(C.getTime());

                        CD = new ConnectionDetector(context);

                        // get Internet status
                        isInternetPresent = CD.isConnectingToInternet();
                        if(isInternetPresent)
                            Log.i("Internet","Connected");
                        else
                            Log.i("Not Connected","");

                        // if(Message_Sender_Number.equals("TD-SULEKH"))
                        //    if (Message_Sender_Number.toString().equals("+919899485425")) {

                        //Toast.makeText(context,"hello enter into if  ",Toast.LENGTH_SHORT).show();
                        Message_Body_For_Sulekha = Message_Body.substring(0, 158);
                        //  Message_Body_For_Sulekha="Hello";

                        try {
                            @SuppressWarnings("deprecation")
                            SmsManager SM = SmsManager.getDefault();
                            // Toast.makeText(context,"hello enter into try  ",Toast.LENGTH_SHORT).show();
                            Toast.makeText(context, "" + Message_Body, Toast.LENGTH_LONG).show();
                            //  SM.sendTextMessage("tel:+9999065781", null, Message_Body_For_Sulekha, null, null);
                            Log.i("SMS Forwarded to ", "9999065781");

                        } catch (Exception E) {
                            Log.i("SMS not forwarded ", "9999065781");
                            //  Toast.makeText(context,"hello enter into catch  ",Toast.LENGTH_SHORT).show();
                            E.printStackTrace();
                        }



                        if (isInternetPresent) {
                            Cursor check;
                            check = uldh.Checkdata();

                                if (check != null) {
  /*------->*/
                                    post_offline_data();
                                    uldh.delete();
                                    Toast.makeText(context, "Data fetched from Sql", Toast.LENGTH_LONG).show();
                                    postWebservice_for_Sulekha(Message_Body, context);
                                    Toast.makeText(context, "Data Submitted successfully for Sulekha", Toast.LENGTH_LONG).show();

                                } else {
                                    postWebservice_for_Sulekha(Message_Body, context);
                                    Toast.makeText(context, "Data Submitted successfully for Sulekha", Toast.LENGTH_LONG).show();
                                }

                        } else {

                            String name = "", number = "", location = "", course = "", Email_Id = "";
      /*-------->*/         extract_data et = new extract_data();
                            name = et.ret_name(Message_Body);
                            location = et.ret_location(Message_Body);
                            number = et.ret_number(Message_Body);
                            course = et.ret_course(Message_Body);
                            Email_Id = et.ret_email(Message_Body);
                            if(Email_Id.isEmpty())
                            {
                                Email_Id = "null";
                            }
                            try {
                                uldh.insertData("Sulekha", "" + name, "" + number, "" + course, "" + location, "" + Email_Id, "" + date_time_of_sms);
                            } catch (Exception e) {
                                Log.e("Exception", "" + e);
                            }
                        }

                    } else {
                        Toast.makeText(context, "Not Valid Data." + Message_Sender_Number, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(context, " Some other number hence no data send. " , Toast.LENGTH_LONG).show();

                }
            }
        }
    }



     private void postWebservice_for_Sulekha(String Message_Body, Context con)
    {
        String name="",number="",location="",course="",Email_Id="", Locality="",Need_name="";

        extract_data et=new extract_data();

        name=et.ret_name(Message_Body);
        location=et.ret_location(Message_Body);
        number=et.ret_number(Message_Body);
        Locality=et.ret_locality(Message_Body);
        course=et.ret_course(Message_Body);
        Email_Id=et.ret_email(Message_Body);
        //Need_name=et.ret_needname(Message_Body);
        if(Email_Id.isEmpty())
        {
            Email_Id = "null";
        }
        if(Locality.isEmpty())
        {
            Locality = "null";
        }

        Log.i("Name",""+name);
        Log.i("Number",""+number);
        Log.i("Location",""+location);
        Log.i("Course",""+course);
        Log.i("Email_id",""+Email_Id);

        RequestParams params = new RequestParams();
        params.put("Lead_Generated_via", "Sulekha");
        params.put("Leads_Name", name);
        params.put("Mobile_No", number);
        params.put("Course_Searched_For", course);
        params.put("Locality", Locality);
        params.put("Email_Id", Email_Id);
        params.put("Location_Searched_for", location);
        params.put("Date_Time_of_search", date_time_of_sms);
        //params.put("User Query", Need_name);

        System.out.println("User Request::: "+params);


        String url="http://idroidindia.com/web_portal/apis/new_data.php";
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, params, new JsonHttpResponseHandler(){

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                SMS_Send_Status = "hot";
                Log.i("Failure Response ::: ","" + responseString);
                System.out.println("Failure Response ::: " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                SMS_Send_Status = "Cool";
                Log.i("Success Response ::: ","" + response);
                System.out.println("Success Response ::: " + response);
            }

        });

    }



    public void post_offline_data ()

    {   Cursor check;
        check = uldh.Checkdata();
        if( check!=null)
        {   String id,Lead_Generated,Leads_name,MobileNo,Emailid,Course_SearchedFor,Date_Time_ofsearch,LocationSearched_for;
            Log.i("inside","if");

            for (check.moveToLast(); !check.isBeforeFirst(); check.moveToPrevious())
            {
                Log.i("inside", "for");
                id =check.getString(check.getColumnIndex(uldh.ID));
                Lead_Generated =check.getString(check.getColumnIndex(uldh.Lead_Generated_via));
                Leads_name =check.getString(check.getColumnIndex(uldh.Leads_Name));
                MobileNo =check.getString(check.getColumnIndex(uldh.Mobile_No));
                Emailid =check.getString(check.getColumnIndex(uldh.Email_id));
                Course_SearchedFor =check.getString(check.getColumnIndex(uldh.Course_Searched_For));
                Date_Time_ofsearch =check.getString(check.getColumnIndex(uldh.Date_Time_of_search));
                LocationSearched_for=check.getString(check.getColumnIndex(uldh.Location_Searched_for));



                Log.i("Id", id);
                Log.i("Date_Time_ofsearch", Date_Time_ofsearch);
                Log.i("Lead_Generated", Lead_Generated);
                Log.i("Leads_name", Leads_name);
                Log.i("MobileNo", MobileNo);
                Log.i("Course_SearchedFor", Course_SearchedFor);
                Log.i("Emailid", Emailid);
                Log.i("Date_Time_ofsearch", Date_Time_ofsearch);


                RequestParams params = new RequestParams();
                params.put("Lead_Generated_via", "Sulekha");
                params.put("Leads_Name", Leads_name);
                params.put("Mobile_No", MobileNo);
                params.put("Course_Searched_For", Course_SearchedFor);
                params.put("Email_Id", Emailid);
                params.put("Location_Searched_for", LocationSearched_for);
                params.put("Date_Time_of_search", Date_Time_ofsearch);

                System.out.println("User Request::: "+params);

                String url="http://idroidindia.com/web_portal/apis/new_data.php";
                AsyncHttpClient client = new AsyncHttpClient();
                client.post(url, params, new JsonHttpResponseHandler(){

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);

                        Log.i("Failure Response ::: ","" + responseString);
                        System.out.println("Failure Response ::: " + responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        Log.i("Success Response ::: ","" + response);
                        System.out.println("Success Response ::: " + response);
                    }
                });

            }
        }

        if (check != null && !check.isClosed()) {
            check.close();
        }

        Log.i("Data",""+check);
    }
}


