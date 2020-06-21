package com.example.icsproject.user_management;

import android.app.Activity;

import com.example.icsproject.events.SignInFailedEvent;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.FacebookAuthProvider;

import org.greenrobot.eventbus.EventBus;

import timber.log.Timber;

import static com.example.icsproject.user_management.UMGlobalManagement.firebaseAuthWithCredential;

public class UMFacebook {

    private static String TAG = UMFacebook.class.getSimpleName();
    private static UMFacebook instance;
    private static CallbackManager callbackManager;
    private static LoginManager loginManager;

    public static UMFacebook getInstance() {
        if (instance == null) {
            instance = new UMFacebook();
            callbackManager = CallbackManager.Factory.create();
        }
        return instance;
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public LoginManager getLoginManager(Activity activity) {
        loginManager = LoginManager.getInstance();
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Timber.tag(TAG).d("facebook:onSuccess:%s", loginResult);
                firebaseAuthWithCredential(activity, FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken()));
            }

            @Override
            public void onCancel() {
                EventBus.getDefault().post(new SignInFailedEvent("facebook:onCancel"));
            }

            @Override
            public void onError(FacebookException error) {
                EventBus.getDefault().post(new SignInFailedEvent("facebook:onError"));
            }
        });

        return loginManager;
    }
}


