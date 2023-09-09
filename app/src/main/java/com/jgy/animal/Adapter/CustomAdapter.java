package com.jgy.animal.Adapter;



import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jgy.animal.R;
import com.jgy.animal.activities.RecyclerviewItemActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.PUT;

//<CustomAdapter.ViewHolder>
public class CustomAdapter extends RecyclerView.Adapter {
    private ArrayList<ViewItemData> mData;

    //===================무한 스크롤======================
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_loading, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof ViewHolder) {
//            populateItemRows((ViewHolder) holder, position);
//        } else if (holder instanceof LoadingViewHolder) {
//            showLoadingView((LoadingViewHolder) holder, position);
//        }

        if (holder instanceof ViewHolder) {
            final ViewItemData data = mData.get(position);
            if (data == null) {
                return;
            }

            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setPlaceName_textView(data.getPlaceName());
            viewHolder.setPlaceAddr1_textView(data.getPlaceAddr1());
            viewHolder.setPlaceAddr2_textView(data.getPlaceAddr2());
            viewHolder.setPlaceInfo_textView(data.getPlaceInfo());
            viewHolder.setPlaceOtherInfo_textView(data.getPlaceOtherInfo());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        // 클릭 시 새로운 화면으로 전환하는 코드
                        Intent intent = new Intent(view.getContext(), RecyclerviewItemActivity.class);
                        intent.putExtra("facility", data.getPlaceName());
                        intent.putExtra("address",data.getAddress());
                        intent.putExtra("phoneNumber", data.getPhoneNumber());
                        intent.putExtra("parking",data.getParking());
                        intent.putExtra("operatingTime", data.getOperatingTime());
                        intent.putExtra("homePage",data.getWebsite());
                        intent.putExtra("closedDays", data.getClosedDays());

                        // 추가된 부분// 필요한 데이터를 인텐트로 전달
                        view.getContext().startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
//            showLoadingView((LoadingViewHolder) holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    private void showLoadingView(LoadingViewHolder holder, int position) {

    }

    private void populateItemRows(ViewHolder holder, int position) {
        if (mData.size() <= 0) {
            return;
        }
        final ViewItemData data = mData.get(position);
        if (data == null) {
            return;
        }

        String name = data.getPlaceName();
        String addr1 = data.getPlaceAddr1();
        String addr2 = data.getPlaceAddr2();
        String info = data.getPlaceInfo();
        String otherInfo = data.getPlaceOtherInfo();

        holder.setPlaceName_textView(name);
        holder.setPlaceAddr1_textView(addr1);
        holder.setPlaceAddr2_textView(addr2);
        holder.setPlaceInfo_textView(info);
        holder.setPlaceOtherInfo_textView(otherInfo);
    }


    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    //====================뷰홀더 클래스====================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView placeName_textView;
        private TextView placeAddr1_textView;
        private TextView placeAddr2_textView;
        private TextView placeInfo_textView;
        private TextView placeOtherInfo_textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName_textView = itemView.findViewById(R.id.place_name);
            placeAddr1_textView = itemView.findViewById(R.id.place_addr1);
            placeAddr2_textView = itemView.findViewById(R.id.place_addr2);
            placeInfo_textView = itemView.findViewById(R.id.place_info);
            placeOtherInfo_textView = itemView.findViewById(R.id.place_otherInfo);
        }

        public void setPlaceName_textView(String placeName) {
            this.placeName_textView.setText(placeName);
        }

        public void setPlaceAddr1_textView(String placeAddr1) {
            this.placeAddr1_textView.setText(placeAddr1);
        }

        public void setPlaceAddr2_textView(String placeAddr2) {
            this.placeAddr2_textView.setText(placeAddr2);
        }

        public void setPlaceInfo_textView(String placeInfo) {
            this.placeInfo_textView.setText(placeInfo);
        }

        public void setPlaceOtherInfo_textView(String placeOtherInfo) {
            this.placeOtherInfo_textView.setText(placeOtherInfo);
        }

        public TextView getPlaceName_textView() {
            return placeName_textView;
        }

        public TextView getPlaceAddr1_textView() {
            return placeAddr1_textView;
        }

        public TextView getPlaceAddr2_textView() {
            return placeAddr2_textView;
        }

        public TextView getPlaceOtherInfo_textView() {
            return placeOtherInfo_textView;
        }

        public TextView getPlaceInfo_textView() { return placeInfo_textView; }
    }
    //==================================================

    //----------생성자--------------------
    // 생성자를 통해서 데이터를 전달받도록 함
    public CustomAdapter () {
        mData = new ArrayList<>();
    }
    //------------------------------

//    @NonNull
//    @Override // ViewHolder 객체를 생성하여 리턴한다.
//    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.recycler_view_item, parent, false);
//        CustomAdapter.ViewHolder viewHolder = new CustomAdapter.ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override // ViewHolder 안의 내용을 position 에 해당되는 데이터로 교체한다.
//    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
//        ViewItemData item = mData.get(position);
//        holder.placeAddr1_textView.setText(item.getPlaceAddr1());
//        holder.placeAddr2_textView.setText(item.getPlaceAddr2());
//        holder.placeOtherInfo_textView.setText(item.getPlaceOtherInfo());
//        holder.placeName_textView.setText(item.getPlaceName());
//    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void add(ViewItemData data) {
        mData.add(data);
        notifyItemInserted(mData.size() - 1);
    }

    // add
    public boolean containsItem(ViewItemData viewItemData) {
        String keyword = viewItemData.getPlaceName().toLowerCase(); // 키워드를 소문자로 변환하여 대소문자 구분 없이 비교합니다.

        for (ViewItemData item : mData) {
            String placeName = item.getPlaceName().toLowerCase(); // placeName을 소문자로 변환하여 대소문자 구분 없이 비교합니다.

            if (placeName.equals(keyword)) {
                return true;
            }
        }
        return false;
    }
    //

    public void clear() {
        mData.clear();
        notifyDataSetChanged(); // 어댑터에 데이터가 변경되었음을 알려줍니다.
    }
}
