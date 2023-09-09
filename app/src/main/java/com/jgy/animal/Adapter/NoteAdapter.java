package com.jgy.animal.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jgy.animal.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter {

    private ArrayList<NoteItemData> noteItemData;

    //===================무한 스크롤======================
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view_item, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_loading, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            noteItemRows((ViewHolder) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return noteItemData.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    private void showLoadingView(LoadingViewHolder holder, int position) {

    }

    private void noteItemRows(ViewHolder viewHolder, int position) {
        if (noteItemData.size() <= 0) {
            return;
        }

        final NoteItemData data = noteItemData.get(position);

        if (data == null) {
            return;
        }

        // 방명록에 들어갈 정보들 넣기 (xml 에 설정한것)

        /////////////////////////////////////////
    }

    // 로딩뷰홀더 /////////////////////////////////////////////////////////

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    // 뷰홀더 클래스 //////////////////////////////////////////////////////
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView noteid_textView;
        private TextView noteplace_textView;

        private TextView noteplace_textView2;
        private TextView notetitle_textView;
        private TextView notescope_textView;
        private TextView notecomment_textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteid_textView = itemView.findViewById(R.id.note_id);
            noteplace_textView = itemView.findViewById(R.id.note_place);
            noteplace_textView2 = itemView.findViewById(R.id.note_place2);
            notetitle_textView = itemView.findViewById(R.id.note_title);
            notescope_textView = itemView.findViewById(R.id.note_scope);
            notecomment_textView = itemView.findViewById(R.id.note_comment);
        }

        // id
        public TextView getNoteid_textView() {
            return noteid_textView;
        }

        public void setNoteid_textView(TextView noteid_textView) {
            this.noteid_textView = noteid_textView;
        }

        // 장소1
        public TextView getNoteplace_textView() {
            return noteplace_textView;
        }

        public void setNoteplace_textView(TextView noteplace_textView) {
            this.noteplace_textView = noteplace_textView;
        }

        // 장소2
        public TextView getNoteplace_textView2() {
            return noteplace_textView2;
        }

        public void setNoteplace_textView2(TextView noteplace_textView2) {
            this.noteplace_textView2 = noteplace_textView2;
        }

        // 제목
        public TextView getNotetitle_textView() {
            return notetitle_textView;
        }

        public void setNotetitle_textView(TextView notetitle_textView) {
            this.notetitle_textView = notetitle_textView;
        }

        // 평점
        public TextView getNotescope_textView() {
            return notescope_textView;
        }

        public void setNotescope_textView(TextView notescope_textView) {
            this.notescope_textView = notescope_textView;
        }

        // 후기
        public TextView getNotecomment_textView() {
            return notecomment_textView;
        }

        public void setNotecomment_textView(TextView notecomment_textView) {
            this.notecomment_textView = notecomment_textView;
        }
    }


    // 생성자 ------------------------------------------------------------
    // : 생성자를 통해서 데이터를 전달받음

    public NoteAdapter() {
        noteItemData = new ArrayList<>();
    }


    @Override
    public int getItemCount() {
        return noteItemData == null ? 0 : noteItemData.size();
    }

    public void add(NoteItemData data) {
        noteItemData.add(data);
        notifyItemInserted(noteItemData.size() - 1);
    }

//    // 키워드를 대문자 소문자로 바꾸는 부분 데이터넣을거 정해지면 작성
//    public boolean containsItem(NoteItemData noteItemData) {
//        String keyword = noteItemData.getNoteID().toLowerCase();
//
//        for (NoteItemData itemData : noteItemData) {
//            String
//        }
//
//
//        return false;
//    }

    public void clear() {
        noteItemData.clear();
    }
}
