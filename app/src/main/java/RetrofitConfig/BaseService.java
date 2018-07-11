package RetrofitConfig;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import Constants.Constant;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {

    protected static final String BASE_URL ="http://api.nytimes.com/svc/mostpopular/v2/mostviewed/";
    protected static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    protected static TokenInterceptor tokenInterceptor = new TokenInterceptor();
    protected static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(tokenInterceptor).build();
    protected static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    protected static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


    private static class TokenInterceptor implements Interceptor {


        private TokenInterceptor() {

        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request initialRequest = chain.request();
            String sToken = "";
            if (sToken != null) {
                initialRequest = initialRequest.newBuilder()
                        .addHeader("Accept", "application/json; charset=utf-8")
                        .build();
            }

            Response response = chain.proceed(initialRequest);
            return response;
        }
    }


}
