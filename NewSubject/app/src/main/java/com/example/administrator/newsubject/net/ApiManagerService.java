package com.example.administrator.newsubject.net;

import com.example.administrator.newsubject.entity.PageDataBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author 涂文远
 * @version $Rev$
 * @time 2016/12/24 10:38
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public interface ApiManagerService {
    String DOU_BAN_HOST = "http://jl.xuecheyi.com/api/";
    String SSB = "http://120.76.242.81:8080/ssb-app/user/";
    String MOKAO = "http://mokao.xuecheyi.com/api/";
    String UGOU = "http://test.ugou88.com/ugou-wx/";
    String TWY = "http://120.78.137.175/Test1/";

    /*//http://jl.xuecheyi.com/api/JlTeacher/GetTeacherDetail?teacherId=2201&topCount=2
    @GET("JlTeacher/GetTeacherDetail")
    Observable<HttpResultForXueCheYi<JlTeacher>> getJlTeacherDetailsById(@Query("teacherId") int teacherId, @Query("topCount") int topCount);

    @GET("Ad/GetAll")
    Observable<HttpResultForXueCheYi<List<AD>>> getAdAll();

    @POST("login.do")
    Observable<SSBHttpResult<User>> login(@Body RequestBody object);*/

    @GET("i/custom_page/getPageData")
    Observable<HttpResultForUgou<PageDataBean>> getPageData(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize, @Query("pcid") Integer pcid, @Query("pid") int pid);

    @FormUrlEncoded
    @POST("i/custom_page/getPageData")
    Observable<HttpResultForUgou<PageDataBean>> getPageData1(@Field("pageNumber") int pageNumber, @Field("pageSize") int pageSize, @Field("pcid") Integer pcid, @Field("pid") int pid);

    @Multipart
    @POST("Upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part("file") MultipartBody.Part file);
    /**
     * 注意1：必须使用{@code @POST}注解为post请求<br>
     * 注意2：使用{@code @Body}注解参数，则不能使用{@code @Multipart}注解方法了<br>
     * 直接将所有的{@link MultipartBody.Part}合并到一个{@link MultipartBody}中
     */
    @Multipart
    @POST("Upload")
    Call<ResponseBody> upload1(
            @Body RequestBody body
    );

    @Multipart
    @POST("Upload")
    Observable<String> upload2(@PartMap Map<String, RequestBody> params);



}
