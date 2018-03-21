package com.example.shriji.app60;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shriji on 19/3/18.
 */

public class DataAdpter extends RecyclerView.Adapter<DataAdpter.DataViewHolder> {

    public static final String ITEM_KEY = "parceable" ;
    private Context context;
    private List<DataModel> dataItems;

    public DataAdpter(Context context, List<DataModel> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_adpter, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        final DataModel data =  dataItems.get(position);
        holder.txtName.setText(data.getmName());
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(data.getmImage());
            Drawable d = Drawable.createFromStream(inputStream,null);
            holder.imgImage.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Main2Activity.class);
                intent.putExtra(ITEM_KEY,data);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgImage)
        ImageView imgImage;
        @BindView(R.id.txtName)
        TextView txtName;
        View view;
        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;

        }
    }

}
