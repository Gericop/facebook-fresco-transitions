package com.takisoft.frescotransitions.recyclerview;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takisoft.frescotransitions.R;
import com.takisoft.frescotransitions.recyclerview.photo.ImagePhotoDetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewListFragment extends Fragment implements ListAdapter.ImageSelectListener {
    /**
     * lorempixel.com image categories.
     */
    private static final String[] CATEGORIES = {
            "animals",
            "sports",
            "nature",
            "city",
            "food",
            "people",
            "nightlife",
            "fashion",
            "transport",
            "cats",
            "business",
            "technics",
    };

    /**
     * How many images per each category.
     */
    private static final int NUM_ENTRIES_PER_CATEGORY = 10;

    /**
     * Number of recycler view spans
     */
    private static final int SPAN_COUNT = 3;

    public static final String EXTRA_MODE = "com.takisoft.frescotransitions.recyclerview.MODE";
    public static final int MODE_NORMAL = 0;
    public static final int MODE_PHOTO = 1;

    public static RecyclerViewListFragment createNormal() {
        return create(MODE_NORMAL);
    }

    public static RecyclerViewListFragment createPhoto() {
        return create(MODE_PHOTO);
    }

    private static RecyclerViewListFragment create(int mode) {
        RecyclerViewListFragment fragment = new RecyclerViewListFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_MODE, mode);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("DefaultLocale")
    public static List<Uri> createDummyData() {
        List<Uri> data = new ArrayList<>();
        for (int i = 0; i < NUM_ENTRIES_PER_CATEGORY; i++) {
            for (String category : CATEGORIES) {
                data.add(Uri.parse(String.format("http://lorempixel.com/400/400/%s/%d", category, i + 1)));
            }
        }
        return data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.list);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ListAdapter(createDummyData(), this));
    }

    @Override
    public void onItemSelected(ListAdapter.ViewHolder viewHolder) {
        int pos = viewHolder.getAdapterPosition();
        int mode = getArguments().getInt(EXTRA_MODE, MODE_NORMAL);

        Fragment fragment = null;

        switch (mode) {
            case MODE_NORMAL:
                break;
            case MODE_PHOTO:
                fragment = ImagePhotoDetailsFragment.create(pos);
                break;
        }

        if (fragment != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, fragment)
                    .addSharedElement(viewHolder.draweeView, "image_" + pos)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
