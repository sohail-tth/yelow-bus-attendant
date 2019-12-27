package com.tth.yelowbus_attendant.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tth.yelowbus_attendant.R;

public class ReferEarnFragment extends BaseFragment implements View.OnClickListener {

    private ImageView ivBack;
    private TextView tvInvite, tvInviteCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_refer_earn, container, false);

        ivBack = view.findViewById(R.id.ivBack);
        tvInvite = view.findViewById(R.id.tvInvite);
        tvInviteCode = view.findViewById(R.id.tvInviteCode);


        ivBack.setOnClickListener(this);
        tvInvite.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                getActivity().onBackPressed();
                break;

            case R.id.tvInvite:
                shareTextUrl(tvInviteCode.getText().toString());
                break;
        }
    }

    private void shareTextUrl(String inviteCode) {

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT, "Refer and earn!");
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.tth.yelowbus_attendant");
        startActivity(Intent.createChooser(share, "Share link!"));


//        Go to play store ----- intent
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.tth.yelowbus_attendant"));
//        startActivity(intent);
    }
}
