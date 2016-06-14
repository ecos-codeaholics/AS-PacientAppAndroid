package ecos.uniandes.davidmtz.patientapplication;

import org.apache.http.client.methods.HttpUriRequest;

import android.content.Context;
import android.os.AsyncTask;

import ecos.uniandes.davidmtz.patientapplication.RestConnection;

public class RestTask extends AsyncTask<HttpUriRequest, Void, String>{
    public static final String HTTP_RESPONSE = "httpResponse";
 
    private String mAction;
    private String mUrl;
    private String mparams;
 
    public RestTask(Context context, String action, String url, String params){
        mAction = action;
        mUrl = url;
        mparams = params;
    }
 
    @Override
    protected String doInBackground(HttpUriRequest... params){
        try{
            if(mAction.equals("BUSINESS")){
            	RestConnection rest = new RestConnection();
            	return rest.requestContent(mUrl);
//            	return new JSONObject().put("deportes", new JSONArray()
//					            			.put(new JSONObject().put("id","1").put("desc","BasketBall"))
//					            			.put(new JSONObject().put("id","2").put("desc","Patinaje")))
//            							.put("servicios", new JSONArray()
//            								.put(new JSONObject().put("id","1").put("desc","Alojamiento"))
//            								.put(new JSONObject().put("id","2").put("desc","Parqueadero"))).toString();
            }
            if(mAction.equals("episodeID")){
            	RestConnection rest = new RestConnection();
            	return rest.postRequest(mUrl, mparams);
//            	return new JSONObject().put("companies", new JSONArray()
//        											.put(new JSONObject().put("idempresa","1").put("empresa","greentech").put("idciudad","1").put("ciudad", "bogota").put("iddireccion","1"))
//        											.put(new JSONObject().put("idempresa","2").put("empresa","greentech").put("idciudad","2").put("ciudad", "barranquilla").put("iddireccion","3"))).toString();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
        
        return "";
    }
 
    @Override
    protected void onPostExecute(String result){
        
    }
 
}