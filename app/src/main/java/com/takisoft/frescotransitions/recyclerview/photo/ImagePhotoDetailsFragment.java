package com.takisoft.frescotransitions.recyclerview.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.DraweeTransition;
import com.takisoft.frescotransitions.R;
import com.takisoft.frescotransitions.recyclerview.RecyclerViewListFragment;
import com.takisoft.frescotransitions.widget.MultiTouchViewPager;

public class ImagePhotoDetailsFragment extends Fragment {
    public static final String EXTRA_POSITION = "com.takisoft.frescotransitions.recyclerview.photo.POSITION";

    public static ImagePhotoDetailsFragment create(int position) {
        ImagePhotoDetailsFragment fragment = new ImagePhotoDetailsFragment();

        Bundle args = new Bundle();
        args.putInt(EXTRA_POSITION, position);
        fragment.setArguments(args);

        fragment.setSharedElementEnterTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.FIT_CENTER));
        fragment.setSharedElementReturnTransition(new Explode());

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition();
        return inflater.inflate(R.layout.photo_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        MultiTouchViewPager viewPager = view.findViewById(R.id.view_pager);

        viewPager.setAdapter(new PhotoAdapter(RecyclerViewListFragment.createDummyData()));
        viewPager.setCurrentItem(getArguments().getInt(EXTRA_POSITION));

        startPostponedEnterTransition();
    }
}
