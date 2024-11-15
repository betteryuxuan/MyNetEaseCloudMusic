package com.example.myneteasecloudmusic.ui.login.mvp.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myneteasecloudmusic.R;
import com.example.myneteasecloudmusic.base.BaseActivity;
import com.example.myneteasecloudmusic.base.BaseView;
import com.example.myneteasecloudmusic.databinding.ActivityLoginBinding;
import com.example.myneteasecloudmusic.ui.login.mvp.contract.ILoginContract;
import com.example.myneteasecloudmusic.ui.login.mvp.presenter.LoginPresenter;
import com.example.myneteasecloudmusic.ui.main.mainpage.view.MainActivity;
import com.example.myneteasecloudmusic.ui.webActivity.WebActivity;
import com.example.myneteasecloudmusic.utils.AnimationUtil;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContract.View {
    private ActivityLoginBinding binding;

    @Override
    protected void initView(Bundle savedInstanceState) {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestNotificationPermission();
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("LoginActivity", "Notification permission granted.");
            } else {
            }
        }
    }


    @Override
    protected void initData() {
        setEdit();

        binding.tvLoginStartnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loginActivity();
            }
        });

        binding.btnLoginCodestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNum = String.valueOf(binding.etLoginPhonenum.getText());
                if (TextUtils.isEmpty(phoneNum)) {
                    AnimationUtil.setShakeAnimateView(view);
                    Toast.makeText(getApplicationContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                } else if (phoneNum.length() != 13) {
                    AnimationUtil.setShakeAnimateView(view);
                    Toast.makeText(getApplicationContext(), "请输入11位数字的手机号", Toast.LENGTH_SHORT).show();
                } else if (!binding.cbLoginPrivacy.isChecked()) {
                    Toast.makeText(getApplicationContext(), "请同意服务条款及隐私政策", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.loginActivity();
                }
            }
        });

        binding.tvLoginPrivacy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });
        binding.tvLoginPrivacy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loginActivity() {
        finish();
        overridePendingTransition(0,R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("listenTag1", "LoginActivity: onBackPressed");
        finishAffinity();
    }

    private void setEdit() {
        binding.etLoginPhonenum.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;
            private final StringBuilder sb = new StringBuilder();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    return;
                }

                isUpdating = true;

                sb.setLength(0);
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if (c != ' ') {
                        sb.append(c);
                    }
                }

                if (sb.length() > 3) {
                    sb.insert(3, " ");
                }
                if (sb.length() > 8) {
                    sb.insert(8, " ");
                }

                binding.etLoginPhonenum.setText(sb.toString());
                binding.etLoginPhonenum.setSelection(sb.length()); // 将光标移动到文本最后

                isUpdating = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.etLoginPhonenum.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // 获取焦点时隐藏 hint
                binding.etLoginPhonenum.setHint("");
            } else {
                binding.etLoginPhonenum.setHint("输入手机号");
            }
        });
        binding.ivLoginDelate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etLoginPhonenum.setText("");
                binding.etLoginPhonenum.setHint("输入手机号");
            }
        });
    }


    @Override
    public LoginPresenter getmPresenterInstance() {
        return new LoginPresenter();
    }

    @Override
    protected BaseView getMvpView() {
        return this;
    }

    @Override
    public void onClick(View view) {

    }
}