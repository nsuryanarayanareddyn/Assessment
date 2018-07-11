package Services;

import Constants.Constant;
import Models.ArticleResponse;
import RetrofitConfig.BaseService;
import comman.HttpCallResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class ArticleService extends BaseService {

    public static void fetchArticleList(final HttpCallResponse mHttpCallResponse){
        ArticleInterface mInterface = retrofit.create(ArticleInterface.class);
        Call<ArticleResponse> mCall = mInterface.getArticles();
        mCall.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if(response.isSuccessful()){
                    mHttpCallResponse.OnSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                mHttpCallResponse.OnFailure(t);
            }
        });
    }




    public interface ArticleInterface{
        @Headers("Content-Type: application/json; charset=utf-8")
        @GET("all-sections/7.json?apikey=828e64b670894f49b24617fa4fdbc1d4")
        Call<ArticleResponse> getArticles();
    }
}
