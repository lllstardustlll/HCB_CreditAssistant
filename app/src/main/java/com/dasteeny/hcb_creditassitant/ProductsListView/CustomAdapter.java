package com.dasteeny.hcb_creditassitant.ProductsListView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dasteeny.hcb_creditassitant.Activities.CreditsActivity;
import com.dasteeny.hcb_creditassitant.JsonObjects.GetProducts.GetProductsResponse.ProductsData.ClientProducts.ClientProduct.ClientProduct;
import com.dasteeny.hcb_creditassitant.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by User on 10/10/2017.
 */

public class CustomAdapter extends ArrayAdapter<ClientProduct> implements View.OnClickListener {

    private List<ClientProduct> listItem;
    private Context mContext;

    @Override
    public void onClick(View view) {
    }

    private static class ViewHolder {
        TextView txtProdName;
        TextView txtDetails;
        ImageView imgType;
    }

    public CustomAdapter(List<ClientProduct> item, Context context) {
        super(context, R.layout.list_item, item);
        this.listItem = item;
        this.mContext = context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ClientProduct listItem = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.txtProdName = (TextView) convertView.findViewById(R.id.list_item_product_name);
            viewHolder.txtDetails = (TextView) convertView.findViewById(R.id.list_item_details);
            viewHolder.imgType = (ImageView) convertView.findViewById(R.id.list_item_pic);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        String itemTitleType = listItem.getProductType();
        int itemYear = listItem.getCreditOpenDate().getYear();
        int itemMonth = listItem.getCreditOpenDate().getMonth();
        int itemDay = listItem.getCreditOpenDate().getDay();
        String itemOpenDate = createDate(itemDay, itemMonth, itemYear);

        switch (itemTitleType) {
            case "SS":
                viewHolder.txtProdName.setText(R.string.loansCashType);
                viewHolder.imgType.setImageResource(R.mipmap.cash);
                //viewHolder.imgType.setImageResource(R.mipmap.goods);
                break;
            case "RD":
                viewHolder.txtProdName.setText(R.string.loansRDType);
                viewHolder.imgType.setImageResource(R.mipmap.rd);
                break;
        }
        viewHolder.txtDetails.setText(String.format(convertView.getResources().getString(R.string.loansSubHeader),
                listItem.getCreditAmount(), listItem.getCurrency(), itemOpenDate));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientProduct listItem = getItem(position);

                Intent intent = new Intent(mContext.getApplicationContext(), CreditsActivity.class);
                intent.putExtra("product", listItem);
                mContext.startActivity(intent);
            }
        });

        return convertView;

    }

    private String createDate(int itemDay, int itemMonth, int itemYear) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, itemYear);
        cal.set(Calendar.MONTH, itemMonth);
        cal.set(Calendar.DAY_OF_MONTH, itemDay);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        return format1.format(cal.getTime());
    }

}
