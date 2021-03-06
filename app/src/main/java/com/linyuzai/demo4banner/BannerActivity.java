package com.linyuzai.demo4banner;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linyuzai.banner.Banner;
import com.linyuzai.banner.BannerAdapter;
import com.linyuzai.banner.OnBannerChangeListener;
import com.linyuzai.banner.OnBannerItemClickListener;
import com.linyuzai.banner.ViewHolder;
import com.linyuzai.banner.adapter.ImageBannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {

    Banner banner1, banner2;
    Drawable hz1, hz2, hz3, jj1, jj2, jj3;
    List<Drawable> drawables;
    BannerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        banner1 = (Banner) findViewById(R.id.banner1);
        banner2 = (Banner) findViewById(R.id.banner2);

        hz1 = getResources().getDrawable(R.mipmap.hz01);
        hz2 = getResources().getDrawable(R.mipmap.hz02);
        hz3 = getResources().getDrawable(R.mipmap.hz03);

        drawables = new ArrayList<>();
        drawables.add(hz1);
        drawables.add(hz2);
        drawables.add(hz3);

        jj1 = getResources().getDrawable(R.mipmap.jj01);
        jj2 = getResources().getDrawable(R.mipmap.jj02);
        jj3 = getResources().getDrawable(R.mipmap.jj03);

        class MyViewHolder extends ViewHolder {
            ImageView image;
            TextView text;

            MyViewHolder(View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.image);
                text = (TextView) itemView.findViewById(R.id.text_position);
            }
        }
        banner1.setAdapter(adapter = new BannerAdapter<MyViewHolder>() {

            @Override
            public int getBannerCount() {
                return drawables.size();
            }

            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item, parent, false);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, int position) {
                //holder.text.setText(String.valueOf(position));
                holder.image.setImageDrawable(drawables.get(position));
            }

            @Override
            public boolean isLoop() {
                return true;
            }
        });
        banner1.setOnBannerItemClickListener(new OnBannerItemClickListener() {
            @Override
            public void onBannerItemClick(ViewHolder holder, int position) {
                Log.i("onBannerItemClick", holder + "," + position);
            }
        });
        banner1.setOnBannerChangeListener(new OnBannerChangeListener() {
            @Override
            public void onBannerScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("banner1", "onBannerScrolled:" + position);
            }

            @Override
            public void onBannerSelected(int position) {
                Log.i("banner1", "onBannerSelected:" + position);
            }

            @Override
            public void onBannerScrollStateChanged(int state) {
                Log.i("banner1", "onBannerScrollStateChanged:" + state);
            }
        });
        banner1.startAutoScroll(2000);

        banner2.setAdapter(new ImageBannerAdapter() {
            @Override
            public void onImageViewCreated(ImageView view) {
                view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }

            @Override
            public void onBindImage(ImageView image, int position) {
                switch (position) {
                    case 0:
                        image.setImageDrawable(hz1);
                        break;
                    case 1:
                        image.setImageDrawable(hz2);
                        break;
                    case 2:
                        image.setImageDrawable(hz3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public int getBannerCount() {
                return 3;
            }

            @Override
            public boolean isLoop() {
                return true;
            }
        });
        banner2.startAutoScroll(2000);
    }
}
