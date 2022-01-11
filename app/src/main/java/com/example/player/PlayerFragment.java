package com.example.player;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.util.PrefManager;
//import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.rcloud.netflix.R;
import com.rcloud.netflix.databinding.FragmentPlayerBinding;


public class PlayerFragment extends Fragment {

    private PlayerViewModel mPlayerViewModel;
//    private SimpleExoPlayerView mSimpleExoPlayerView;
    private ImageView ic_media_stop;
    private RelativeLayout payer_pause_play;
    private View view;
    private Boolean done =  false;
    private Boolean isLive =  false;
    private TextView text_view_exo_player_live;
    private TextView exo_duration;
    private TextView exo_live;
    private ImageView image_view_exo_player_rotation;

    private Boolean isLandscape =  true;

    // lists
    private PrefManager pref;
    private ImageView image_view_dialog_source_plus;
    private static Integer videoId;
    private ImageView image_view_exo_player_replay_10;
    private ImageView image_view_exo_player_forward_10;
    private ImageView image_view_exo_player_back;

    @Override
    public void onResume() {
        super.onResume();
        mPlayerViewModel.play();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPlayerViewModel.pause();
    }
    public static PlayerFragment newInstance(String videoUrl, Boolean isLive, String videoType, String videoTitle, String videoImage, Integer videoId_) {
        PlayerFragment playerFragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putString("videoUrl", videoUrl);
        args.putString("videoType", videoType);
        args.putString("videoTitle", videoTitle);
        args.putString("videoImage", videoImage);
        args.putBoolean("isLive", isLive);
        videoId = videoId_;
        playerFragment.setArguments(args);
        return playerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPlayerBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false);
        view = (View) binding.getRoot();
        this.pref =new  PrefManager(getActivity());

        initView(binding);
        initAction();
        return view;
    }
    private void initView(FragmentPlayerBinding binding) {
        mPlayerViewModel = new PlayerViewModel(getActivity());

        image_view_exo_player_back = view.findViewById(R.id.image_view_exo_player_back);
        image_view_exo_player_replay_10 = view.findViewById(R.id.image_view_exo_player_replay_10);
        image_view_exo_player_forward_10 = view.findViewById(R.id.image_view_exo_player_forward_10);
        payer_pause_play = view.findViewById(R.id.payer_pause_play);
        text_view_exo_player_live = view.findViewById(R.id.text_view_exo_player_live);
        image_view_exo_player_rotation = view.findViewById(R.id.image_view_exo_player_rotation);
        image_view_dialog_source_plus = view.findViewById(R.id.image_view_dialog_source_plus);
        exo_duration = view.findViewById(R.id.exo_duration);
        exo_live = view.findViewById(R.id.exo_live);
        isLive  =  getUrlExtra().getBoolean("isLive");
        mPlayerViewModel.setPayerPausePlay(payer_pause_play);
        binding.setPlayerVm(mPlayerViewModel);
//        mSimpleExoPlayerView = binding.videoView;
//        mSimpleExoPlayerView.setShutterBackgroundColor(Color.TRANSPARENT);
        if (isLive) {
            text_view_exo_player_live.setVisibility(View.VISIBLE);
            exo_duration.setVisibility(View.GONE);
            exo_live.setVisibility(View.VISIBLE);
        }else{
            text_view_exo_player_live.setVisibility(View.GONE);
            exo_duration.setVisibility(View.VISIBLE);
            exo_live.setVisibility(View.GONE);
        }
    }

    private void initAction(){
        this.image_view_exo_player_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        this.image_view_dialog_source_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pref.getInt("subtitle_text_size") < 48) {
                    pref.setInt("subtitle_text_size", pref.getInt("subtitle_text_size") + 1);
                }
            }
        });
        this.image_view_exo_player_forward_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((mPlayerViewModel.mExoPlayer.getCurrentPosition() + 10000) > mPlayerViewModel.mExoPlayer.getDuration()) {
                    mPlayerViewModel.mExoPlayer.seekTo(mPlayerViewModel.mExoPlayer.getDuration());
                } else {
                    mPlayerViewModel.mExoPlayer.seekTo(mPlayerViewModel.mExoPlayer.getCurrentPosition() + 10000);
                }
            }
        });
        this.image_view_exo_player_replay_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerViewModel.mExoPlayer.getCurrentPosition() < 10000) {
                    mPlayerViewModel.mExoPlayer.seekTo(0);
                } else {
                    mPlayerViewModel.mExoPlayer.seekTo(mPlayerViewModel.mExoPlayer.getCurrentPosition() - 10000);
                }
            }
        });
        this.image_view_exo_player_rotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLandscape) {
                    PlayerFragment.this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    isLandscape = false;
                } else {
                    PlayerFragment.this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    isLandscape = true;
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mPlayerViewModel.onStart(mSimpleExoPlayerView, getUrlExtra());
    }

    public void setFull(){
       // mPlayerViewModel.setMediaFull();
    }
    public void setNormal(){
      //  mPlayerViewModel.setMediaNormal();

    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Nullable
    private Bundle getUrlExtra() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle;
        }
        return null;
    }
}
