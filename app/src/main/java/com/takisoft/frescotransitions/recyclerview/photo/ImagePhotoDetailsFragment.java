package com.takisoft.frescotransitions.recyclerview.photo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.DraweeTransition;
import com.takisoft.frescotransitions.R;
import com.takisoft.frescotransitions.recyclerview.RecyclerViewListFragment;
import com.takisoft.frescotransitions.widget.MultiTouchViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImagePhotoDetailsFragment extends Fragment {
    public static final String EXTRA_POSITION = "com.takisoft.frescotransitions.recyclerview.photo.POSITION";

    private MultiTouchViewPager viewPager;
    private PhotoAdapter photoAdapter;

    public static ImagePhotoDetailsFragment create(int position) {
        ImagePhotoDetailsFragment fragment = new ImagePhotoDetailsFragment();

        Bundle args = new Bundle();
        args.putInt(EXTRA_POSITION, position);
        fragment.setArguments(args);

        fragment.setEnterTransition(new Fade(Fade.IN));
        fragment.setExitTransition(new Fade(Fade.OUT));

        /*fragment.setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                Log.d("PhotoSharedEnter[START]", "names: " + sharedElementNames);
                Log.d("PhotoSharedEnter[START]", "views: " + sharedElements);
                Log.d("PhotoSharedEnter[START]", "snapshots: " + sharedElementSnapshots);
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                Log.d("PhotoSharedEnter[END]", "names: " + sharedElementNames);
                Log.d("PhotoSharedEnter[END]", "views: " + sharedElements);
                Log.d("PhotoSharedEnter[END]", "snapshots: " + sharedElementSnapshots);
            }

            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                super.onMapSharedElements(names, sharedElements);
                Log.d("PhotoSharedEnter[MAP]", "names: " + names);

                if (sharedElements != null) {
                    for (Map.Entry<String, View> entry : sharedElements.entrySet()) {
                        Log.d("PhotoSharedEnter[MAP]", "key: " + entry.getKey() + ", value: " + entry.getValue());
                    }
                }

                if (names.isEmpty()) {

                }
            }
        });*/

        fragment.setExitSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                List<String> sharedElementsHash = new ArrayList<>();

                for(View view : sharedElements){
                    sharedElementsHash.add(Integer.toHexString(view.hashCode()));
                }

                Log.d("PhotoSharedExit[START]", "names: " + sharedElementNames);
                Log.d("PhotoSharedExit[START]", "views: " + sharedElementsHash);
                Log.d("PhotoSharedExit[START]", "snapshots: " + sharedElementSnapshots);
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                List<String> sharedElementsHash = new ArrayList<>();

                for(View view : sharedElements){
                    sharedElementsHash.add(Integer.toHexString(view.hashCode()));
                }

                Log.d("PhotoSharedExit[END]", "names: " + sharedElementNames);
                Log.d("PhotoSharedExit[END]", "views: " + sharedElementsHash);
                Log.d("PhotoSharedExit[END]", "snapshots: " + sharedElementSnapshots);
            }

            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                super.onMapSharedElements(names, sharedElements);
                Log.d("PhotoSharedExit[MAP]", "names: " + names);

                if (sharedElements != null) {
                    for (Map.Entry<String, View> entry : sharedElements.entrySet()) {
                        Log.d("PhotoSharedExit[MAP]", "key: " + entry.getKey() + ", value: " + Integer.toHexString(entry.getValue().hashCode()));
                    }
                }
            }
        });

        TransitionSet sharedEnterTransition = DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.FIT_CENTER);
        sharedEnterTransition.addTransition(new ChangeTransform());
        sharedEnterTransition.setDuration(1000);

        /*TransitionSet sharedReturnTransition = DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.FIT_XY, ScalingUtils.ScaleType.FIT_CENTER);
        sharedReturnTransition.addTransition(new ChangeTransform());
        sharedReturnTransition.setDuration(1000);*/

        fragment.setSharedElementEnterTransition(sharedEnterTransition);
        //fragment.setSharedElementReturnTransition(sharedReturnTransition);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setReturnTransition();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition();
        return inflater.inflate(R.layout.photo_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.view_pager);

        photoAdapter = new PhotoAdapter(RecyclerViewListFragment.createDummyData());

        viewPager.setAdapter(photoAdapter);
        viewPager.setCurrentItem(getArguments().getInt(EXTRA_POSITION));

        startPostponedEnterTransition();
    }

    private void setReturnTransition() {
        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                List<String> sharedElementsHash = new ArrayList<>();

                for(View view : sharedElements){
                    sharedElementsHash.add(Integer.toHexString(view.hashCode()));
                }

                Log.d("PhotoSharedEnter[START]", "names: " + sharedElementNames);
                Log.d("PhotoSharedEnter[START]", "views: " + sharedElementsHash);
                Log.d("PhotoSharedEnter[START]", "snapshots: " + sharedElementSnapshots);
            }

            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                List<String> sharedElementsHash = new ArrayList<>();

                for(View view : sharedElements){
                    sharedElementsHash.add(Integer.toHexString(view.hashCode()));
                }

                Log.d("PhotoSharedEnter[END]", "names: " + sharedElementNames);
                Log.d("PhotoSharedEnter[END]", "views: " + sharedElementsHash);
                Log.d("PhotoSharedEnter[END]", "snapshots: " + sharedElementSnapshots);
            }

            @Override
            public void onMapSharedElements(@NonNull List<String> names, @NonNull Map<String, View> sharedElements) {
                super.onMapSharedElements(names, sharedElements);

                int pos = viewPager.getCurrentItem();
                String name = "image_" + pos;

                if (names.isEmpty()) {
                    Log.w("PhotoSharedEnter[MAP]", "names list was empty, adding current item");
                    names.add(name);

                    sharedElements.put(name, viewPager.findViewWithTag(name));
                } else if (!names.contains(name)) {
                    Log.w("PhotoSharedEnter[MAP]", "names list does not contain '" + name + "', adding current item");

                    names.clear();

                    names.add(name);
                    sharedElements.put(name, viewPager.findViewWithTag(name));
                }/*else if (sharedElements.isEmpty()) {
                    Log.w("PhotoSharedEnter[MAP]", "sharedElements list was empty, adding current item");

                    int pos = viewPager.getCurrentItem();
                    String name = "image_" + pos;

                    sharedElements.put(names.get(0), viewPager.findViewWithTag(name));
                }*/


                Log.d("PhotoSharedEnter[MAP]", "names: " + names);

                for (Map.Entry<String, View> entry : sharedElements.entrySet()) {
                    Log.d("PhotoSharedEnter[MAP]", "key: " + entry.getKey() + ", value: " + Integer.toHexString(entry.getValue().hashCode()));
                }
            }
        });
    }
}
