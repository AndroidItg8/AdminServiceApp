package itg8.com.adminserviceapp.sales.mvpadd;

import android.view.View;



/**
 * Created by Android itg 8 on 12/15/2017.
 */

public interface SalesPersonAddMVP {

    public  interface  SalesPersonAddView
    {
        String getPersonName();
        String getMobileNumber();
        String getAnotherMobileNumber();
        String getEmail();
        String getAddress();
        void onSuccess(String status);
        void onFail(String message);
        void onError(Object t);
        void onPersonNameInvalid(String err);
        void onAnotherMobileNumberInvalid(String err);
        void onMobileNumberInvalid(String err);
        void onEmailInvalid(String err);
        void onAddressInvalid(String err);
        void showProgress();
        void hideProgress();
        void onNoInternetConnect(boolean b);

        void onSuceesUpdate(String status);

        void onSuccessDelete(String success);

        void showAllUpdateValues(String name, String mobile, String anotherMobile, String address, String email);

    }
    public interface SalesPersonAddPresenter
    {
        void onDestroy();
        void onSalesPersonAddClicked(View view);

        void onUpdatePersonClicked(View view, int pkid);

        void onDeleteSalesPerson(String url, int pkid);
    }
    public interface SalesPersonAddModule
    {
        void onDestroy();
        void onButtonClicked( String url, String person, String mobile,String anotherMobile,String email,String address ,SalesPersonAddListener listener);

        void onUpdateSalesPerson(String url, int pkid, String name, String mobile, String anotherMobile, String email, String address, SalesPersonAddListener listener);

        void onDeleteEngg(String url, int pkid, SalesPersonAddListener listener);
    }

    public interface SalesPersonAddListener{
        void onSuccess(String status);
        void onUpadteSuccess(String status);
        void onError(Object t);
        void onNoInternetConnect(boolean b);

        void onSuccessDelete(String success);
    }
}
