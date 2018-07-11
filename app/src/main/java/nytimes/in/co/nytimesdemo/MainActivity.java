package nytimes.in.co.nytimesdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Downloader;

import java.util.List;

import Adapters.ArticleAdapter;
import Models.ArticleResponse;
import Models.Result;
import Services.ArticleService;
import Uitility.RecyclerTouchListener;
import Uitility.SpinnerManager;
import comman.HttpCallResponse;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter mArticleAdapter;
   private List<Result> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViews();
        this.getArticleList(MainActivity.this);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Result mResult = mList.get(position);
                Intent mIntent = new Intent(MainActivity.this,DeatiledArticle.class);
                mIntent.putExtra("url",mResult.getUrl());
                startActivity(mIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }


    /*
    this method will  help us to fetch the data from server
     */
    private void getArticleList(final Context mContext) {
        SpinnerManager.showSpinner(mContext, "Laoding...");
        ArticleService.fetchArticleList(new HttpCallResponse() {
            @Override
            public void OnSuccess(Object obj) {
                Response<ArticleResponse> mRes = (Response<ArticleResponse>) obj;
                ArticleResponse mData = mRes.body();
                 mList = mData.getResults();
                mArticleAdapter = new ArticleAdapter(mContext, mList);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(mArticleAdapter);
                SpinnerManager.hideSpinner(mContext);
            }

            @Override
            public void OnFailure(Throwable t) {
                SpinnerManager.hideSpinner(mContext);

            }
        });
    }
}
