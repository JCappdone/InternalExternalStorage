package com.example.app67.adpters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app67.CallBacks.ActionListionr;
import com.example.app67.R;
import com.example.app67.database.model.DataItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shriji on 20/3/18.
 */

public class ItemViewAdpter extends RecyclerView.Adapter<ItemViewAdpter.MyViewHolder> {

    Context mContext;
    List<DataItem> mItemList;
    ActionListionr mListionr;


    public ItemViewAdpter(Context context, List<DataItem> itemList, ActionListionr listionr) {
        mContext = context;
        mItemList = itemList;
        mListionr = listionr;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adpter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DataItem dataItem = mItemList.get(position);
        holder.mTxtName.setText(dataItem.getItemName());
        holder.mTxtCategory.setText(dataItem.getCategory());
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListionr.onLongPress(position, dataItem);
                notifyDataSetChanged();
                Toast.makeText(mContext, dataItem.getItemName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListionr.onSingleClick(position, dataItem);
            }
        });
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mListionr.onCheckChange(position,dataItem,isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtCategory)
        TextView mTxtCategory;
        @BindView(R.id.txtName)
        TextView mTxtName;
        View mView;
        @BindView(R.id.checkBox)
        CheckBox mCheckBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }
    }

}
