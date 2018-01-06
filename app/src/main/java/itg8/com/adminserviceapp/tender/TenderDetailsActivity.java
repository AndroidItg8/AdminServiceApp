package itg8.com.adminserviceapp.tender;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.enquiry.model.StatusModel;
import itg8.com.adminserviceapp.tender.adapter.TenderDocumentAdapter;
import itg8.com.adminserviceapp.tender.model.CustomTenderDocumentModel;
import itg8.com.adminserviceapp.tender.model.DocName;
import itg8.com.adminserviceapp.tender.model.DocumentModel;
import itg8.com.adminserviceapp.tender.model.PendingTenderModel;
import itg8.com.adminserviceapp.tender.mvp_document.TenderDocumentMVP;
import itg8.com.adminserviceapp.tender.mvp_document.TenderDocumentPresenterImp;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenderDetailsActivity extends AppCompatActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks, TenderDocumentAdapter.DocumentItemClickedListener, TenderDocumentMVP.TenderDocumentView {
    //, TenderDocumentAdapter.DocumentItemClickedListener

    private static final int RC_STORAGE = 234;
    private static final String TAG = "TenderDetailsActivity";
    private static final String PDF_MIME_TYPE = "application/pdf";
    private static final int FROM_SUCCESS = 0;
    private static final int FROM_ERROR = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.lbl_title)
    TextView lblTitle;
    @BindView(R.id.lbl_open_date)
    TextView lblOpenDate;
    @BindView(R.id.lbl_price)
    TextView lblPrice;
    @BindView(R.id.lbl_price_symbol)
    TextView lblPriceSymbol;
    @BindView(R.id.lbl_description)
    TextView lblDescription;
    @BindView(R.id.lbl_end_date)
    TextView lblEndDate;

    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_sync)
    Button btnSync;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.lbl_open_date_value)
    TextView lblOpenDateValue;
    @BindView(R.id.lbl_end_date_value)
    TextView lblEndDateValue;
    @BindView(R.id.img_pdf)
    ImageButton imgPdf;

    @BindView(R.id.lbl_location_value)
    TextView lblLocationValue;
    @BindView(R.id.lbl_location)
    TextView lblLocation;
    @BindView(R.id.lbl_emd_value)
    TextView lblEmdValue;
    @BindView(R.id.lbl_emd)
    TextView lblEmd;
    @BindView(R.id.img_no)
    ImageView imgNo;
    @BindView(R.id.rl_no_item)
    RelativeLayout rlNoItem;
    @BindView(R.id.progressView)
    ProgressBar progressView;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    ArrayList<Long> list = new ArrayList<>();
    Intent intent = null;
    HashMap<Integer, CustomTenderDocumentModel> hashMap = new HashMap<>();
    HashMap<DocumentModel, Boolean> hashMapChange = new HashMap<>();
    PendingTenderModel tenderModel;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String[] permissions;
    private boolean canReadStorage;
    private boolean canWriteStorage;
    private DownloadManager downloadManager;
    private File tempFile;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private String from = null;
    private List<CustomTenderDocumentModel> documentList = new ArrayList<>();
    private Snackbar snackbar;
    private TenderDocumentMVP.TenderDocumentPresenter presenter;
    private boolean isViewVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        isViewVisible = false;

        init();
    }

    private void init() {
        checkStoragePerm();
        imgPdf.setOnClickListener(this);
        btnSync.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        checkIntent();
    }

    private void checkIntent() {
        String title = null;

        if (getIntent().hasExtra(CommonMethod.TENDER)) {
            tenderModel = getIntent().getParcelableExtra(CommonMethod.TENDER);


        }

        if (getIntent().hasExtra(CommonMethod.FROM_SUBMITED)) {
            from = getIntent().getStringExtra(CommonMethod.FROM_SUBMITED);
            title = "Submitted Tender";
            btnSubmit.setText("Rejected");
            btnSync.setVisibility(View.GONE);


        } else {
            //tenderModel = getIntent().getParcelableExtra(CommonMethod.TENDER);
            from = getIntent().getStringExtra(CommonMethod.FROM_PENDINGS);
            title = "Pending Tender";
//            btnSync.setText("Rejected");
            btnSubmit.setText("Submit");
            btnSync.setVisibility(View.VISIBLE);



        }

        getSupportActionBar().setTitle(title);


        hideDocumentButton(tenderModel);
        setAllEditText(tenderModel);
        setCustomDocumentModel(tenderModel);
        setChangeHashMap(tenderModel.getDocuments());

        setRecyclerView(tenderModel);
    }

    private void setChangeHashMap(List<DocumentModel> documents) {
        for (DocumentModel model :
                documents) {
            hashMapChange.put(model, false);

        }
    }

    private void hideDocumentButton(PendingTenderModel tenderModel) {
        if (TextUtils.isEmpty(tenderModel.getTenderFile())) {
            imgPdf.setVisibility(View.GONE);
        } else
            imgPdf.setVisibility(View.VISIBLE);
    }

    private void setCustomDocumentModel(PendingTenderModel tenderModel) {
        for (DocumentModel model : tenderModel.getDocuments()) {
            CustomTenderDocumentModel model1 = new CustomTenderDocumentModel();
            model1.setCover_fkid(model.getCover().get(0).getPkid());
            model1.setActualDoc_fkid(model.getDocName().get(0).getPkid());
            model1.setDocuType_fkid(model.getDocType().get(0).getPkid());
            model1.setEmployeeStatus(null);
            model1.setPkid(model.getPkid());
            model1.setTender_fkid(model.getTenderFkid());
            if (model.getSuperAdminStatus() != null)
                model1.setSuperAdminStatus(Integer.parseInt((model.getSuperAdminStatus())));

            hashMap.put(model.getPkid(), model1);
        }

    }

    private void setRecyclerView(PendingTenderModel model) {
        if (model.getDocuments().size() > 0) {
            CommonMethod.showHideItem(recyclerView, rlNoItem);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(decoration);
            recyclerView.setAdapter(new TenderDocumentAdapter(getApplicationContext(), model.getDocuments(), from, this));
        } else {
            CommonMethod.showHideItem(rlNoItem, recyclerView);
        }


    }

    private void setAllEditText(PendingTenderModel model) {
        lblTitle.setText(CommonMethod.checkEmpty(model.getTitle()));
        lblDescription.setText(CommonMethod.checkEmpty(model.getDescription()));
        lblEndDateValue.setText(CommonMethod.checkEmpty(model.getClosedDate()));
        lblOpenDateValue.setText(CommonMethod.checkEmpty(model.getOpenDate()));
        lblPrice.setText(CommonMethod.checkEmpty(model.getTenderFeeIn()));
        lblEmdValue.setText(CommonMethod.checkEmpty(model.getEMDAMT()));
        lblLocationValue.setText(CommonMethod.checkEmpty(model.getLocation()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_pdf:
                downloadPDF();
                break;
            case R.id.btn_sync:
                sendDocumentDetailsToServer();
                break;
            case R.id.btn_submit:
                checkFrom();
                break;
        }
    }

    private void checkFrom() {
        if (from.equalsIgnoreCase(CommonMethod.FROM_PENDINGS)) {
            tenderModel.setTenderStatus("1");
            tenderModel.setTenderFillorNot("1");
        } else {
            btnSubmit.setText("Reject");
            tenderModel.setTenderStatus("2");
            tenderModel.setTenderFillorNot("0");
        }
        sendFundValueToServer();

    }

    private void sendFundValueToServer() {

        CommonMethod.showHideItem(progressView,btnSubmit);

        Call<StatusModel> call = AppApplication.getInstance().getRetroController().updateTender(getString(R.string.url_update_tender), tenderModel);
        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if (response.isSuccessful()) {
                    CommonMethod.showHideItem(btnSubmit, progressView);

                    if (response.body().isFlag()) {
                        showSnackbar(false, FROM_SUCCESS, response.body().getStatus());
                    } else {
                        showSnackbar(false, CommonMethod.FROM_ERROR, response.body().getStatus());
                    }
                } else {
                    CommonMethod.showHideItem(btnSubmit, progressView);
                    showSnackbar(false, CommonMethod.FROM_ERROR, response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                t.printStackTrace();
                CommonMethod.showHideItem(btnSubmit, progressView);
                if (t instanceof NoConnectivityException) {
                    showSnackbar(true, CommonMethod.FROM_ERROR, getString(R.string.no_internet));
                } else {
                    showSnackbar(false, CommonMethod.FROM_ERROR, t.getMessage());
                }

            }
        });


    }

    private void showSnackbar(boolean isConnected, final int from, String message) {
        int color = 0;
        if (from == CommonMethod.FROM_INTERNET) {
            if (isConnected) {

                color = Color.RED;
            }
        } else {
            color = Color.WHITE;

        }

        snackbar = Snackbar
                .make(fab, message, Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        textView.setMaxLines(2);
        snackbar.show();


        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSnackbarOkClicked(view, from);

            }
        });
        snackbar.show();
    }



    private void sendDocumentDetailsToServer() {
        getDocumentFromHashMap();

        if (!checkChangesHappend()) {
            Toast.makeText(this, "No changes done in documents.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (documentList.size() > 0) {
            presenter = new TenderDocumentPresenterImp(this);
            presenter.sendTenderDocumentModelList(getString(R.string.url_upadte_tender_document), documentList);
        } else {
            showSnackbar(getString(R.string.select_item), false, FROM_ERROR);

        }
    }

    private boolean checkChangesHappend() {
        for (Map.Entry<DocumentModel, Boolean> entry : hashMapChange.entrySet()) {
            if (entry.getValue()) {
                return true;
            }
        }
        return false;
    }

    private void getDocumentFromHashMap() {
        for (Map.Entry<Integer, CustomTenderDocumentModel> entry : hashMap.entrySet()) {
            documentList.add(entry.getValue());
        }

    }

    private void downloadPDF() {
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri Download_Uri = Uri.parse(CommonMethod.BASE_URL + tenderModel.getTenderFile());

        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle(tenderModel.getTitle());
        request.setDescription("Downloading " + tenderModel.getTitle());
        request.setMimeType("application/pdf");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(true);
        request.setVisibleInDownloadsUi(true);
        tempFile = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), CommonMethod.FOLDER_NAME);
        Log.e(TAG, "File Path:" + tempFile);
        if (tempFile.exists()) {
            // If we have downloaded the file before, just go ahead and show it.
            openPDF(getApplicationContext(), Uri.fromFile(tempFile));
        } else {
            tempFile.mkdirs();
        }
        request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOCUMENTS, "/" + CommonMethod.FOLDER_NAME);
//        request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOCUMENTS, "/" + "android_tutorial" + ".pdf");
        long refid = downloadManager.enqueue(request);
        list.add(refid);
        Log.d(TAG, "Download Manager:" + new Gson().toJson(list));

        downloadCompleted(refid);


    }

    private void downloadCompleted(long refid) {
        Cursor c = downloadManager.query(new DownloadManager.Query().setFilterById(refid));

        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            if (status == DownloadManager.STATUS_SUCCESSFUL) {

                openPDF(getApplicationContext(), Uri.fromFile(tempFile));
            }

        }
        c.close();
    }


    private void openPDF(Context applicationContext, Uri uri) {
        intent = new Intent(Intent.ACTION_VIEW);

        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                intent.setDataAndType(uri, "application/pdf");
            } else {
                File file = new File(uri.getPath());
                if (file.exists()) {
                    uri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".fileprovider", file);
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
            }
        } catch (Exception e) {
            Log.d(getApplicationContext().getClass().getName(), String.valueOf(e));
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
    }


    @AfterPermissionGranted(RC_STORAGE)
    private void checkStoragePerm() {
        permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if ((EasyPermissions.hasPermissions(this, permissions[0]))) {
            canReadStorage = true;
        }
        if ((EasyPermissions.hasPermissions(this, permissions[1]))) {
            canWriteStorage = true;
        }

        if (!canReadStorage || !canWriteStorage) {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_no_permission), RC_STORAGE, permissions);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        checkPrems(perms, true);
    }

    private void checkPrems(List<String> perms, boolean isGranted) {
        if (perms.contains(permissions[0])) {
            canReadStorage = isGranted;
        }
        if (perms.contains(permissions[1])) {
            canWriteStorage = isGranted;
        }

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        checkPrems(perms, false);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    private void sendEmailForDocument(DocName model) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject) + model.getTenderActualDocument());
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.mail_body));
        startActivity(Intent.createChooser(intent, getString(R.string.title_sending_mail)));
    }

    @Override
    public void onDocumentClickedItem(int position, DocumentModel model) {
        sendEmailForDocument(model.getDocName().get(0));
    }

    @Override
    public void onDocumnetCheckItem(int position, DocumentModel model, boolean b, List<DocumentModel> list) {
        setAllDocumentDetails(model, b, list);
    }

    private void setAllDocumentDetails(DocumentModel model, boolean b, List<DocumentModel> list) {
        model.setChecked(b);
        CustomTenderDocumentModel model1 = hashMap.get(model.getPkid());
        model1.setCheck(b);
        if (b) {
            model1.setSuperAdminStatus(1);
        } else {
            model1.setSuperAdminStatus(0);
        }
        hashMapChange.put(model, !hashMapChange.get(model));

        hashMap.put(model.getPkid(), model1);
    }

    @Override
    public void onProgressHide() {
        CommonMethod.showHideItem(btnSync, progressView);


    }

    @Override
    public void onProgressShow() {
        CommonMethod.showHideItem(progressView, btnSync);
    }

    @Override
    public void onNoInternet(boolean b) {
        showSnackbar(getString(R.string.no_internet), b, FROM_ERROR);

    }

    @Override
    public void onError(String mesg) {
        showSnackbar(mesg, false, FROM_ERROR);

    }

    @Override
    public void onSuccess(String message) {
        showSnackbar(message, false, FROM_SUCCESS);

    }

    private void showSnackbar(String message, boolean b, final int from) {
        int color = 0;
        if (b) {
            color = Color.RED;
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
                onSnackbarOkClicked(view, from);

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View view, int from) {
        if (from == FROM_SUCCESS) {
            setResult(RESULT_OK);
            finish();
            onBackPressed();
        }
        hideSnackbar();
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
