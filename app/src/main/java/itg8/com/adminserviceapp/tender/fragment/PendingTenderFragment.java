package itg8.com.adminserviceapp.tender.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import itg8.com.adminserviceapp.tender.TenderDetailsActivity;
import itg8.com.adminserviceapp.tender.adapter.PendingTenderAdapter;
import itg8.com.adminserviceapp.tender.model.PendingTenderModel;
import itg8.com.adminserviceapp.ticket.TicketActivity;
import itg8.com.adminserviceapp.ticket.adapter.TicketStatusPendingAdapter;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PendingTenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class PendingTenderFragment extends BaseFragment implements PendingTenderAdapter.ItemClickedListener, TenderActivity.TenderPendingListListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = PendingTenderFragment.class.getSimpleName();
    private static final String SAVED_LAYOUT_MANAGER = "SAVED_LAYOUT_MANAGER";
    private static final int RC_CODE = 456;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.img_no)
    ImageView imgNo;

    @BindView(R.id.progressView)
    ProgressBar progressView;
    private boolean isViewVisible;
    private int isProgressShow=-1;
    private int positionIndex;
    private Parcelable layoutManagerState;
    private int topView;
    private LinearLayoutManager layoutManager;

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @BindView(R.id.rl_no_item)
    RelativeLayout rlNoItem;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;
    private PendingTenderAdapter adapter;
    private Snackbar snackbar;
    private boolean noList = false;
    private int page = 0;
    boolean isLoading = false;
    boolean isFinished = false;

    public void resetGlobalVariable() {
        page=0;
        noList=false;
        isProgressShow=-1;
        isLoading=false;
        isFinished=false;
        isViewVisible=false;

    }

    private void resetEveryThing()
    {
        resetGlobalVariable();
        ((TenderActivity) this.context).fragmentAttach(this);
        resetAdapter();
        resetOnCreateView();
    }

    private void resetOnCreateView() {
        isViewVisible=true;
        noList=false;
        init();

        if(isProgressShow>0)
        {
            onProgressShow();
        }else
        {
            onProgressHide();
        }
    }

    private void resetAdapter()
    {
        adapter = new PendingTenderAdapter(getActivity(), this, CommonMethod.FROM_PENDING);


    }


    public PendingTenderFragment() {
        super();
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PendingTenderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PendingTenderFragment newInstance(String param1, String param2) {
        PendingTenderFragment fragment = new PendingTenderFragment();
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
        adapter = new PendingTenderAdapter(getActivity(), this, CommonMethod.FROM_PENDING);
        Log.d(TAG, "OnCreate");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            layoutManagerState = savedInstanceState.getParcelable(SAVED_LAYOUT_MANAGER);
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_tender, container, false);
        unbinder = ButterKnife.bind(this, view);

        resetOnCreateView();


        return view;
    }


    private void init() {
        checkNoList();

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //presenter.scrollListener(layoutManager, CommonMethod.FROM_COMPLAINT)
        recyclerView.addOnScrollListener(getRecyclerViewScroll(layoutManager, CommonMethod.FROM_PENDING));
        recyclerView.setAdapter(adapter);
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

    private void checkNoList() {
        if (noList)
            CommonMethod.showHideItem(rlNoItem, recyclerView);
        else {
            CommonMethod.showHideItem(recyclerView, rlNoItem);

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isViewVisible=false;
        noList=false;
        isProgressShow=-1;
    }

    @Override
    public void onItemClicked(int position, PendingTenderModel model) {
        Intent intent = new Intent(getActivity(), TenderDetailsActivity.class);
        intent.putExtra(CommonMethod.TENDER, model);
//        intent.putParcelableArrayListExtra(CommonMethod.TENDER_DOCUMENT, (ArrayList<? extends Parcelable>) model.getDocuments());
        intent.putExtra( CommonMethod.FROM_PENDINGS,CommonMethod.FROM_PENDINGS);
        startActivityForResult(intent,RC_CODE);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        ((TenderActivity) this.context).fragmentAttach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((TenderActivity)this.context).onDetach();
        isViewVisible=false;
        noList=false;
        isProgressShow=-1;

    }

    @Override
    public void onPendingTenderList(List<PendingTenderModel> list) {
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
//        isFinished = true;
//        adapter.removeFooter();
    }

    /**
     * After attaching the fragment we start to download data from server. (WITH PAGE NO 1)
     * But in server response there is no list returned(Means there is no data yet available,
     * so we will show use a page about there is no list available.
     */
    public void noDataAvailableToSetInAdapterInPageOne() {
        noList = true;
        isProgressShow=0;
        if (isViewVisible)
            checkNoList();
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

    @Override
    public void onProgressHide() {
        if(isViewVisible)
            progressView.setVisibility(View.GONE);
        else
            isProgressShow=0;

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
        isProgressShow=0;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_CODE && resultCode==RESULT_OK)
        {
            resetEveryThing();
            ((TenderActivity) this.context).refreshSubmitFragment(this);
        }

    }
}
