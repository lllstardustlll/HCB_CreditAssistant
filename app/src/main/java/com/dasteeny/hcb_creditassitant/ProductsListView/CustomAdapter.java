package com.dasteeny.hcb_creditassitant.ProductsListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasteeny.hcb_creditassitant.R;

import java.util.ArrayList;

/**
 * Created by User on 10/10/2017.
 */

public class CustomAdapter extends ArrayAdapter<ListItem> implements View.OnClickListener {

    private ArrayList<ListItem> listItem;
    Context mContext;

    @Override
    public void onClick(View view) {

    }

    private static class ViewHolder{
        TextView txtProdName;
        TextView txtDetails;
        ImageView imgType;
    }

    public CustomAdapter(ArrayList<ListItem> item, Context context){
        super(context, R.layout.list_item, item);
        this.listItem = item;
        this.mContext = context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ListItem listItem = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null){

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.txtProdName = (TextView) convertView.findViewById(R.id.list_item_product_name);
            viewHolder.txtDetails = (TextView) convertView.findViewById(R.id.list_item_details);
            viewHolder.imgType = (ImageView) convertView.findViewById(R.id.list_item_pic);

            result = convertView;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtProdName.setText(listItem.getProdName());
        viewHolder.txtDetails.setText(listItem.getDetails());
        switch (listItem.getProdName()){
            case "Кредит на товар":
                viewHolder.imgType.setImageResource(R.mipmap.goods);
                break;
            case "Кредит наличными":
                viewHolder.imgType.setImageResource(R.mipmap.cash);
                break;
            case "Карточка с лимитом":
                viewHolder.imgType.setImageResource(R.mipmap.rd);
                break;
        }

        return convertView;

    }

}
