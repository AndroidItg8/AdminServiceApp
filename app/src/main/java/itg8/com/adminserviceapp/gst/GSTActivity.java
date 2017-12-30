package itg8.com.adminserviceapp.gst;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.enquiry.model.EnquiryModel;
import itg8.com.adminserviceapp.gst.mvp.GSTMVP;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;
import itg8.com.adminserviceapp.ticket.adapter.ViewPagerAdapter;

public class GSTActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int JAN_MENU_ITEM = 0;
    private static final String TAG = GSTActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lbl_description)
    TextView lblDescription;
    @BindView(R.id.lbl_gst_payable)
    TextView lblGstPayable;
    @BindView(R.id.tableRow1)
    TableRow tableRow1;
    @BindView(R.id.lbl_actual_payable)
    TextView lblActualPayable;
    @BindView(R.id.tableRow2)
    TableRow tableRow2;
    @BindView(R.id.lbl_upload_slip)
    TextView lblUploadSlip;
    @BindView(R.id.lbl_downlaod_slip)
    TextView lblDownlaodSlip;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.tableRow3)
    TableRow tableRow3;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.lbl_month)
    TextView lblMonth;
    @BindView(R.id.lbl_year)
    TextView lblYear;
    private Snackbar snackbar;
    GSTDateListener listener;
    List<EnquiryModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

    }

    private void init() {
        lblYear.setOnClickListener(this);
        lblMonth.setOnClickListener(this);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SalesFragment(), "Sales");
        adapter.addFragment(new PurchaseFragment(), "Purchase");
        viewPager.setAdapter(adapter);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.lbl_year:
              showPopUp();
                break;
            case R.id.lbl_month:
                showMonthPopUp();
                break;

        }

    }

    private void showMonthPopUp() {

        PopupMenu popup = new PopupMenu(this, lblMonth);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup_month_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(
                        GSTActivity.this,
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                listener.onSelectedMonth( item.getTitle().toString());
                return true;
            }
        });

        popup.show(); //showing popup menu

    }

    private void showPopUp() {
        PopupMenu popup = new PopupMenu(this, lblYear);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup_year_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(
                        GSTActivity.this,
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                listener.onSelectedYear(item.getTitle().toString());
                return true;
            }
        });

        popup.show(); //showing popup menu
    }


    public interface GSTDateListener{
        void onSelectedMonth(String month);
        void onSelectedYear(String year);
    }

}
