package itg8.com.adminserviceapp.sales.mvpadd;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.BaseWeakPresenter;

/**
 * Created by Android itg 8 on 12/15/2017.
 */

public class SalesPersonAddPresenterImp extends BaseWeakPresenter<SalesPersonAddMVP.SalesPersonAddView> implements SalesPersonAddMVP.SalesPersonAddPresenter, SalesPersonAddMVP.SalesPersonAddListener {
    private final SalesPersonAddMVP.SalesPersonAddModule module;
    private Snackbar snackbar;
    private String name;
    private String mobile;
    private String anotherMobile;
    private String address;
    private String email;

    public SalesPersonAddPresenterImp(SalesPersonAddMVP.SalesPersonAddView salesPersonAddView) {
        super(salesPersonAddView);
        module = new SalesPersonAddModuleImp();
    }

    @Override
    public void onDestroy() {
        if (hasView()) {
            module.onDestroy();
        }

    }

    @Override
    public void onSalesPersonAddClicked(View view) {
        boolean isValid = checkIsValidate(view);
        if (isValid) {
            getView().showProgress();
            module.onButtonClicked(view.getContext().getString(R.string.url_add_eng), name, mobile, anotherMobile, email, address, this);

        }

    }

    private boolean checkIsValidate(View view) {
        name = getView().getPersonName();
        mobile = getView().getMobileNumber();
        anotherMobile = getView().getAnotherMobileNumber();
        address = getView().getAddress();
        email = getView().getEmail();

        boolean isValid = true;
        if (TextUtils.isEmpty(name)) {
            isValid = false;
            getView().onPersonNameInvalid(view.getContext().getString(R.string.empty));
        }
        if (TextUtils.isEmpty(address)) {
            isValid = false;
            getView().onAddressInvalid(view.getContext().getString(R.string.empty));
        }
        if (TextUtils.isEmpty(mobile)) {
            isValid = false;
            getView().onMobileNumberInvalid(view.getContext().getString(R.string.empty));
        }
        if (TextUtils.isEmpty(anotherMobile)) {
            isValid = false;
            getView().onAnotherMobileNumberInvalid(view.getContext().getString(R.string.empty));
        }
        if (TextUtils.isEmpty(email)) {
            isValid = false;
            getView().onEmailInvalid(view.getContext().getString(R.string.empty));
        }


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false;
            getView().onEmailInvalid(view.getContext().getString(R.string.invalid_email));
        }
        if (mobile.length() != 10) {
            isValid = false;
            getView().onMobileNumberInvalid(view.getContext().getString(R.string.invalid_number));
        }
        if (anotherMobile.length() != 10) {
            isValid = false;
            getView().onAnotherMobileNumberInvalid(view.getContext().getString(R.string.invalid_number));
        }
        return isValid;

    }

    @Override
    public void onUpdatePersonClicked(View view, int pkid) {
        boolean isValid = checkIsValidate(view);

        if (isValid) {
            getView().showProgress();
            module.onUpdateSalesPerson(view.getContext().getString(R.string.url_update_engg), pkid, name, mobile, anotherMobile, email, address, this);

        }
    }

    @Override
    public void onDeleteSalesPerson(String url, int pkid) {
        if (hasView()) {
            getView().showProgress();
            module.onDeleteEngg(url, pkid, this);
        }
    }

    @Override
    public void onSuccess(String status) {
        if (hasView()) {
            getView().hideProgress();
            getView().onSuccess(status);
        }
    }

    @Override
    public void onUpadteSuccess(String status) {
        if (hasView()) {
            getView().hideProgress();
            getView().onSuceesUpdate(status);
        }
    }

    @Override
    public void onError(Object t) {
        if (hasView()) {
            getView().hideProgress();
            getView().onError(t);
        }

    }

    @Override
    public void onNoInternetConnect(boolean b) {
        if (hasView()) {
            getView().hideProgress();
            getView().onNoInternetConnect(b);
        }
    }

    @Override
    public void onSuccessDelete(String success) {
        if (hasView()) {
            getView().hideProgress();
            getView().onSuccessDelete(success);
        }
    }


}
