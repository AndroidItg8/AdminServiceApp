package itg8.com.adminserviceapp.common;


import java.util.List;

import itg8.com.adminserviceapp.enquiry.model.EnquiryModel;
import itg8.com.adminserviceapp.enquiry.model.StatusModel;
import itg8.com.adminserviceapp.feedback.model.FeedbackModel;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;
import itg8.com.adminserviceapp.tender.model.CustomTenderDocumentModel;
import itg8.com.adminserviceapp.tender.model.PendingTenderModel;
import itg8.com.adminserviceapp.ticket.model.TicketModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetroController {


    @FormUrlEncoded
    @POST()
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<ResponseBody> checkAuthentication(@Url String url,
                                           @Field("grant_type") String password,
                                           @Field("username") String emailId,
                                           @Field("password") String pswd);

    @GET
    Call<List<SalesPersonModel>> getSalesPersonList(@Url String url);

    @GET
    Call<List<EnquiryModel>> getEnquiryList(@Url String url,
                                            @Query("PageNo") int page,
                                            @Query("PageSize") int limit);

    @FormUrlEncoded
    @POST()
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<StatusModel> addEngg(@Url String url,
                              @Field("Email") String email,
                              @Field("Password") String password,
                              @Field("ConfirmPassword") String cpassword,
                              @Field("UserRoles") String employee,
                              @Field("Mobileno") String mobile,
                              @Field("Name") String person,
                              @Field("Address") String address);

    //    pkid:36
//    customer_Name:sachin jadav
//    AddressLine1:jalna, Jalna
//    mobileno:9579235311
//    email:sachins@gmail.com
//    UserRole:Employee
    @FormUrlEncoded
    @POST()
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<StatusModel> updateEngg(@Url String url,
                                 @Field("pkid") int id,
                                 @Field("email") String email,
                                 @Field("UserRole") String employee,
                                 @Field("mobileno") String mobile,
                                 @Field("customer_Name") String person,
                                 @Field("AddressLine1") String address);

    @GET
    Call<List<PendingTenderModel>> getPendingTenderList(@Url String url,
                                                        @Query("PageNo") int page,
                                                        @Query("PageSize") int limit);

    @GET
    Call<List<PendingTenderModel>> getSubmittedTenderList(@Url String url,
                                                          @Query("PageNo") int page,
                                                          @Query("PageSize") int limit);

    @GET
    Call<List<PendingTenderModel>> getRejectedTenderList(@Url String url,
                                                         @Query("PageNo") int page,
                                                         @Query("PageSize") int limit,
                                                         @Query("Type") int type);

    @POST()
    Call<StatusModel> submitDocumentList(@Url String url, @Body List<CustomTenderDocumentModel> list);

    @FormUrlEncoded
    @POST()
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<StatusModel> deleteEngg(@Url String url, @Field("pkid") int pkid);

    @GET
    Call<List<FeedbackModel>> getFeedbackList(@Url String url,
                                              @Query("PageNo") int page,
                                              @Query("PageSize") int limit);
    @GET
    Call<List<TicketModel>> getOpenTicketList(@Url String url,
                                          @Query("PageNo") int page,
                                          @Query("PageSize") int limit,
                                          @Query("ST")int status);
  @GET
    Call<List<TicketModel>> getCloseTicketList(@Url String url,
                                          @Query("PageNo") int page,
                                          @Query("PageSize") int limit,
                                          @Query("ST")int status);
  @GET
    Call<List<TicketModel>> getAcceptTicketList(@Url String url,
                                          @Query("PageNo") int page,
                                          @Query("PageSize") int limit,
                                          @Query("ST")int status);

  @POST
    Call<StatusModel> updateTicket(@Url String url,@Body TicketModel model);
  @POST

    Call<StatusModel> sendFirebaseTokenToServer(@Url String url,@Body CommonMethod.JsonObject jsonObjectModel);
}
