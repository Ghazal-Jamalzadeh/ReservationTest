package com.jmzd.ghazal.reservationtest.ui.slideshow;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.jmzd.ghazal.reservationtest.databinding.FragmentSlideshowBinding;
import com.jmzd.ghazal.reservationtest.models.BodyGetUser;
import com.jmzd.ghazal.reservationtest.models.BodyUserLogin;
import com.jmzd.ghazal.reservationtest.models.BodyUserRegister;
import com.jmzd.ghazal.reservationtest.models.ResponseUserLogin;
import com.jmzd.ghazal.reservationtest.models.ResponseUserRegister;
import com.jmzd.ghazal.reservationtest.server.ApiClient;
import com.jmzd.ghazal.reservationtest.server.ApiServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    //binding
    private FragmentSlideshowBinding binding;
    //context
    private Context context = getContext();
    //api
    public ApiServices apiServices ;
    //other variables
    private String token = "" ; 


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //client
        apiServices = ApiClient.getClient().create(ApiServices.class);

        try {
            //collect data
            binding.btnRegister.setOnClickListener(view -> {

                BodyUserRegister body = new BodyUserRegister() ;
                body.name = binding.edtName.getText().toString() ;
                body.email = binding.edtEmail.getText().toString() ;
                body.password = binding.edtPassword.getText().toString() ;

                //call api
                Call<ResponseUserRegister> call1  = apiServices.registerUser(body);

                call1.enqueue(new Callback<ResponseUserRegister>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseUserRegister> call, @NonNull Response<ResponseUserRegister> response) {

                        assert response.body() != null;
                        Log.d("ghazal", "name : "  + response.body().name);
                        Log.d("ghazal", "id  : "  + response.body().id);
                        Log.d("ghazal", "email : "  + response.body().email);
                        Log.d("ghazal", "created at : "  + response.body().created_at);
                        Log.d("ghazal", "updated at : "  + response.body().updated_at);
                    }

                    @Override
                    public void onFailure(Call<ResponseUserRegister> call, Throwable t) {
                        Log.d("ghazal", "onFailure: " + t.getMessage());
                    }
                });
            });
        }catch (Exception e){
            Log.d("ghazal", "onCreateView: " + e.getMessage());
        }

        binding.btnLogin.setOnClickListener(view -> {
            BodyUserLogin body = new BodyUserLogin() ;
            body.username = binding.edtUserName.getText().toString() ;
            body.password = binding.edtPassword2.getText().toString() ;

            Call<ResponseUserLogin> call2  = apiServices.loginUser(body);

            call2.enqueue(new Callback<ResponseUserLogin>() {
                @Override
                public void onResponse(Call<ResponseUserLogin> call, Response<ResponseUserLogin> response) {
                    try {
                        if (response.body() != null){
                        token = response.body().access_token ;
                        Log.d("ghazal", "access_token : "  + response.body().access_token);
                        Log.d("ghazal", "refresh_token  : "  + response.body().refresh_token);
                        Log.d("ghazal", "token_type : "  + response.body().token_type);
                        }
                    }catch (Exception e ){
                        Log.d("ghazal", "err : " + e.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<ResponseUserLogin> call, Throwable t) {
                    Log.d("ghazal", "onFailure: " + t.getMessage());
                }
            });
        });
        
        binding.btnGetUser.setOnClickListener(view -> {
            BodyGetUser body = new BodyGetUser() ; 
            body.authorization = token ;

         try {
             token = "Bearer " + token ;
             Call<ResponseUserRegister> call3  = apiServices.getUser(token);
             call3.enqueue(new Callback<ResponseUserRegister>() {
                 @Override
                 public void onResponse(Call<ResponseUserRegister> call, Response<ResponseUserRegister> response) {
                     Log.d("ghazal", "onResponse: get user success  " + response.body());
//                     binding.txtUserInfo.setText("hello " + response.body().name);
                 }

                 @Override
                 public void onFailure(Call<ResponseUserRegister> call, Throwable t) {
                     Log.d("ghazal", "onFailure : get user error :  " +t.getMessage());

                 }
             });
         }catch (Exception e){
             Log.d("ghazal", "err: " + e.getMessage());
         }

        });





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}