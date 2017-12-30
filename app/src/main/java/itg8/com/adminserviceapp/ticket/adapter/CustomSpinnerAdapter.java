package itg8.com.adminserviceapp.ticket.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;

/**
 * Created by Android itg 8 on 12/29/2017.
 */

public class CustomSpinnerAdapter extends BaseAdapter {
    Context context;
    int flags[];
    String[] countryNames;
    LayoutInflater inflter;
    private List<SalesPersonModel> body;

    public CustomSpinnerAdapter(Context applicationContext, List<SalesPersonModel> body) {
        this.context = applicationContext;

        inflter = (LayoutInflater.from(applicationContext));
        this.body = body;
    }

    @Override
    public int getCount() {
        return body.size();
    }

    @Override
    public SalesPersonModel getItem(int i) {
        return body.get(i) ;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        TextView names = (TextView) view.findViewById(R.id.textView);
        names.setText(body.get(i).getCustomerName());
        return view;
    }
}
