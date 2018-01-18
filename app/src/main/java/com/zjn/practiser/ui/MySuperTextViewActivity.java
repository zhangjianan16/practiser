package com.zjn.practiser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.zjn.practiser.R;

/**
 * Created by Administrator on 2017/12/29.
 */

public class MySuperTextViewActivity extends AppCompatActivity {

    public TextInputLayout textInputLayout;
    public Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mysupertextview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        // 设置navigation点击事件监听
        //   app:navigationIcon="@mipmap/ic_back"
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textInputLayout = (TextInputLayout) findViewById(R.id.til_name);
        textInputLayout.getEditText().addTextChangedListener(new MyTextChangeLetenner(textInputLayout,"长度超过6位数"));
        //开启计数
        textInputLayout.setCounterEnabled(true);
        // 最大输入
        textInputLayout.setCounterMaxLength(6);


    }

    public void showSnackBar(View view) {
        Snackbar snackbar = Snackbar.make(view, "渐变按钮", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MySuperTextViewActivity.this,"点击",Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                Toast.makeText(MySuperTextViewActivity.this,"消失",Toast.LENGTH_SHORT).show();
                super.onDismissed(snackbar, event);
            }

            @Override
            public void onShown(Snackbar snackbar) {
                Toast.makeText(MySuperTextViewActivity.this,"显示动画完成",Toast.LENGTH_SHORT).show();
                super.onShown(snackbar);
            }
        });
        snackbar.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_view,menu);
        // searchView在menu里面
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        // 设置一出来就呈现搜索框
        searchView.setIconified(false);
        searchView.onActionViewExpanded();// 当展开无输入内容的时候，没有关闭的图标
        //进来就呈现搜索框，并且不能被隐藏 默认为true在框内，设置false则在框外
//        actionView.setIconifiedByDefault(false);
        ImageView search_go_btn= searchView.findViewById(R.id.search_go_btn);
        // 修改搜索按钮显示图片
//        search_go_btn.setImageResource(R.drawable.abc_ic_commit_search_api_mtrl_alpha);
//        search_go_btn.setVisibility(View.VISIBLE);
//        searchView.setMaxWidth(100);
        // 设置提交按钮是否可用（可见）
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("输入用户查找");//设置默认无内容时的文字提示

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交按钮的点击事件
                Toast.makeText(MySuperTextViewActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //当输入框内容改变的时候回调
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    public class MyTextChangeLetenner implements TextWatcher{
        public final TextInputLayout textInputLayout;
        public final String errotext;

        public MyTextChangeLetenner(TextInputLayout textInputLayout,String errotext) {
            this.textInputLayout = textInputLayout;
            this.errotext = errotext;
        }

        @Override

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //文字变化前回调
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //改变的时候回调
        }

        @Override
        public void afterTextChanged(Editable s) {
            // 文字改变后回调
            if (textInputLayout.getEditText().getText().toString().length() <=6) {
                textInputLayout.setErrorEnabled(false);
            } else {
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(errotext);
            }
        }
    }
}
