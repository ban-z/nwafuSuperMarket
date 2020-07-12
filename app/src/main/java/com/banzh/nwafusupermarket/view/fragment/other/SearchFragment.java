package com.banzh.nwafusupermarket.view.fragment.other;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.banzh.nwafusupermarket.R;
import com.banzh.nwafusupermarket.databinding.FragmentSearchBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchFragment extends Fragment {

    FragmentSearchBinding searchBinding;
    ImageButton btnGoBack;
    EditText etSearchGood;
    Button btnSearchGood;

    BottomNavigationView bNav;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        // Inflate the layout for this fragment
        searchBinding = FragmentSearchBinding.inflate(inflater);
        bNav = getActivity().findViewById(R.id.navigation_bottom);
        bNav.setVisibility(View.GONE);
        return searchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        btnGoBack = searchBinding.btnGoBack;
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();//销毁自己
            }
        });

        btnSearchGood = searchBinding.btnSearchGood;
        btnSearchGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*NavHostFragment.findNavController(SearchFragment.this)
                        .navigate(R.id.action_searchFragment_to_goodFragment);*/
                Toast.makeText(getActivity(), "服务器出小差了，稍候重试...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        bNav.setVisibility(View.VISIBLE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
