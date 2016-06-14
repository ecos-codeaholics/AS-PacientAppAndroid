package ecos.uniandes.davidmtz.patientapplication;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class RestConnection {
	
	public String requestContent(String url) {
		//http://extremocundinamarca.com/api/v1/filters
		HttpClient httpclient = new DefaultHttpClient();
		String result = null;
		HttpGet httpget = new HttpGet(url);
	 
		try {
			HttpEntity entity = httpclient.execute(httpget).getEntity();
			result = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String postRequest(String url, String param){
		HttpClient client = new DefaultHttpClient();
		String result = null;
		HttpPost postClient = new HttpPost(url);
		InputStream instream = null;
		try {
		    
		    StringEntity entity = new StringEntity(param, HTTP.UTF_8);
		    entity.setContentType("application/json");
		    postClient.setEntity(entity);
		    
		    // Execute HTTP Post Request
		    HttpEntity entity1 = client.execute(postClient).getEntity();
			result = EntityUtils.toString(entity1);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (Exception exc) {
 
				}
			}
		}
		return result;
	}
}