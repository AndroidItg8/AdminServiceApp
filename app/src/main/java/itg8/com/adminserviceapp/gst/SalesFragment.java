package itg8.com.adminserviceapp.gst;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.gst.adapter.SalesAdapter;
import itg8.com.adminserviceapp.gst.mvp.GSTMVP;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SalesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SalesFragment extends Fragment implements GSTMVP.GSTView, GSTActivity.GSTDateListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.progressView)
    ProgressBar progressView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Snackbar snackbar;
    private SalesAdapter adapter;
    GSTActivity.GSTDateListener listener;
    private String month;
    private String year;
    private Context context;


    public SalesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SalesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SalesFragment newInstance(String param1, String param2) {
        SalesFragment fragment = new SalesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        ((GSTActivity) this.context).listener = this;

    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SalesAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onProgressHide() {
        progressView.setVisibility(View.GONE);


    }

    @Override
    public void onProgressShow() {
         progressView.setVisibility(View.VISIBLE);


    }

    @Override
    public void onNoInternet(boolean b) {
        showSnackbar(b, CommonMethod.FROM_INTERNET, getString(R.string.no_internet));

    }

    @Override
    public void onError(String mesg) {
        showSnackbar(false, CommonMethod.FROM_ERROR, mesg);

    }

    @Override
    public void onNoMoreList(int from) {
        //        adapter.removeFooter();


    }

    @Override
    public void onShowPaginationLoading(boolean show, int from) {
        if (adapter == null)
            return;
        if (show) {
            //  adapter.addFooter();
            adapter.notifyItemInserted(adapter.getItemCount() - 1);
//
        } else {
            //   adapter.removeFooter();
            // adapter.notifyItemRemoved(adapter.getModelSize());
//

        }

    }

    @Override
    public void onPaginationError(boolean show, int from) {

    }

    @Override
    public void getGSTList(List<SalesPersonModel> list) {
//        adapter.addItems(list);

    }

    private void showSnackbar(boolean isConnected, int from, String message) {

        int color = 0;
        if (from == CommonMethod.FROM_INTERNET) {
            if (isConnected) {

                color = Color.RED;
            }
        } else {
            color = Color.WHITE;

        }

        snackbar = Snackbar
                .make(recyclerView, message, Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        textView.setMaxLines(2);
        snackbar.show();


        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSnackbarOkClicked(view);

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View view) {
//        presenter.onSalesPersonAddClicked(view);
        hideSnackbar();
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    public void onSelectedMonth(String month) {
        this.month = month;
    }

    @Override
    public void onSelectedYear(String year) {
        this.year = year;

    }
}
