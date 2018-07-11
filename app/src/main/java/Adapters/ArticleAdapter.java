package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;


import java.util.List;

import Models.ArticleResponse;
import Models.Medium;
import Models.Result;
import Uitility.CircleTransform;
import Uitility.CustomFonts;
import nytimes.in.co.nytimesdemo.R;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private Context mContext;
    private List<Result>  mArticleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mArticletitle, mArticlebylabel,mArticledate;
        public ImageView mArticleThumbnail;

        public MyViewHolder(View view) {
            super(view);
            mArticletitle = (TextView) view.findViewById(R.id.aticletitle);
            mArticlebylabel = (TextView) view.findViewById(R.id.bylable);
            mArticledate  = (TextView) view.findViewById(R.id.datelable);
            mArticleThumbnail = (ImageView) view.findViewById(R.id.articleimage);

        }
    }


    public ArticleAdapter(Context mContext, List<Result> results) {
        this.mContext = mContext;
        this.mArticleList = results;
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Result mResult = mArticleList.get(position);
       List<Medium> mMediaData = mResult.getMedia();
       Medium mImage = mMediaData.get(0);

        holder.mArticletitle.setTypeface(CustomFonts.getNexaBold(mContext));
        holder.mArticlebylabel.setTypeface(CustomFonts.getNexaRegular(mContext));
        holder.mArticledate.setTypeface(CustomFonts.getNexaBold(mContext));
        holder.mArticletitle.setText(mResult.getTitle());
        holder.mArticlebylabel.setText(mResult.getByline());
        holder.mArticledate.setText("Published On : "+mResult.getPublishedDate());
        Picasso.with(mContext).load(mImage.getMediaMetadata().get(0).getUrl()).transform(new CircleTransform()).into(holder.mArticleThumbnail);



    }

}