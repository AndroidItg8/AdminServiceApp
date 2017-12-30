package itg8.com.adminserviceapp.tender.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.BaseFragment;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.Logs;
import itg8.com.adminserviceapp.tender.TenderActivity;
import itg8.com.adminserviceapp.tender.TenderDetailsRejectedActivity;
import itg8.com.adminserviceapp.tender.adapter.RejectedTenderAdapter;
import itg8.com.adminserviceapp.tender.model.PendingTenderModel;
import itg8.com.adminserviceapp.tender.model.TenderModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RejectedTenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RejectedTenderFragment extends BaseFragment implements RejectedTenderAdapter.ItemClickedListener, TenderActivity.TenderExpiredListListner {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String SAVED_LAYOUT_MANAGER = "SAVED_LAYOUT_MANAGER";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.img_no)
    ImageView imgNo;
    @BindView(R.id.rl_no_item)
    RelativeLayout rlNoItem;
    Unbinder unbinder;
    @BindView(R.id.progressView)
    ProgressBar progressView;
    private int positionIndex;
    private Parcelable layoutManagerState;
    private LinearLayoutManager layoutManager;
    private int topView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;
    private List<TenderModel> list;
    private Snackbar snackbar;
    private RejectedTenderAdapter adapter;
    private boolean noList = false;
    private boolean isFinished = false;
    private boolean isLoading = false;
    private int page = 0;
    private boolean isViewVisible=false;
    private int isProgressShow=-1;


    public RejectedTenderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RejectedTenderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RejectedTenderFragment newInstance(String param1, String param2) {
        RejectedTenderFragment fragment = new RejectedTenderFragment();
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
        adapter = new RejectedTenderAdapter(getActivity(), this);

    }

    @Override
    public void getItems(int page, int from) {
        this.page = page;
        isLoading = true;
        ((TenderActivity) context).onLoadMoreItem(page, from);
    }

    @Override
    public int getPage() {
        Logs.d("OnRejectedTenderList: Page" + page);
        return page;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expired_tender, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (savedInstanceState != null) {
            layoutManagerState = savedInstanceState.getParcelable(SAVED_LAYOUT_MANAGER);
        }
        isViewVisible=true;
        if(isProgressShow>0)
        {
            onProgressShow();
        }else
        {
            onProgressHide();
        }

        init();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_LAYOUT_MANAGER, recyclerView.getLayoutManager().onSaveInstanceState());


    }

    private void init() {
        checkNoList();



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isViewVisible=false;
        noList=false;
        ((TenderActivity)this.context).onDetach();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        ((TenderActivity) this.context).rejectedFragmentAttachment(this);
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void onItemClicked(int position, PendingTenderModel model) {
        Intent intent = new Intent(getActivity(), TenderDetailsRejectedActivity.class);
        intent.putExtra(CommonMethod.TENDER, model);
        startActivity(intent);
    }


    @Override
    public void onExpiredTenderList(List<PendingTenderModel> list) {
        checkList(list);
    }


    @Override
    public void onPaginationError(boolean show) {
        adapter.removeFooter();
    }

    @Override
    public void onShowPaginationLoading(boolean show) {
        if(adapter==null)
        {
            return;
        }
        if (show) {
            adapter.addFooter();
        } else {
            adapter.removeFooter();
        }
    }

    @Override
    public void onNoMoreList(String message) {
        // showSnackbar(message);
        isFinished = true;
        adapter.removeFooter();

    }

    private void checkNoList() {
        if (noList) {
            CommonMethod.showHideItem(rlNoItem, recyclerView);
        }
        else
        {
            CommonMethod.showHideItem(recyclerView, rlNoItem);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addOnScrollListener(getRecyclerViewScroll(layoutManager, CommonMethod.FROM_REJECT));
            recyclerView.setAdapter(adapter);

        }
    }

    @Override
    public void onFinished(boolean b) {
        isFinished = b;
    }

    @Override
    public void isLoading(boolean b) {
        isLoading = b;
    }

    @Override
    public void onEmptyList() {
        noDataAvailableToSetInAdapterInPageOne();
    }

    public void noDataAvailableToSetInAdapterInPageOne() {
        noList = true;
        if (rlNoItem != null)
            checkNoList();
    }



    @Override
    public void onProgressHide() {
        if(isViewVisible)
            progressView.setVisibility(View.GONE);
        else
            isProgressShow=0;

    }
    @Override
    public void onDetach() {
        super.onDetach();
        ((TenderActivity)this.context).onDetach();
        isViewVisible=false;

    }

    @Override
    public void onProgressShow() {
        if(isViewVisible)
            progressView.setVisibility(View.VISIBLE);
        else
            isProgressShow=1;
    }

    private void checkList(List<PendingTenderModel> list) {
        adapter.addItems(list);
    }

    @Override
    public void onResume() {
        super.onResume();
        restoreLayoutManagerPosition();
        if (positionIndex != -1) {
            layoutManager.scrollToPositionWithOffset(positionIndex, topView);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        positionIndex = layoutManager.findFirstVisibleItemPosition();
        View startView = recyclerView.getChildAt(0);
        topView = (startView == null) ? 0 : (startView.getTop() - recyclerView.getPaddingTop());

    }

    private void restoreLayoutManagerPosition() {
        if (layoutManagerState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(layoutManagerState);
        }
    }
}
