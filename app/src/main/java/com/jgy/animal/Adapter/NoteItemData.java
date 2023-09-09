package com.jgy.animal.Adapter;

public class NoteItemData {
    private String noteID;
    private String notePlace1;

    private String noteplace2;

    private String notetitle;
    private String noteScope;
    private String noteComment;

    public NoteItemData(String noteID, String notePlace1, String noteplace2, String notetitle, String noteComment) {
        init(noteID, notePlace1, noteplace2, notetitle, noteComment, null);
    }

    public NoteItemData(String noteComment) {
        init(null, null, null, null, null, noteComment);
    }

    public NoteItemData(String noteID, String notePlace1, String noteplace2, String notetitle, String noteScope, String noteComment) {
       init(noteID, notePlace1, noteplace2, notetitle, noteScope, noteComment);
    }

    private void init(String noteID, String notePlace1, String noteplace2, String notetitle, String noteScope, String noteComment) {
        this.noteID = noteID;
        this.notePlace1 = notePlace1;
        this.noteplace2 = noteplace2;
        this.notetitle = notetitle;
        this.noteScope = noteScope;
        this.noteComment = noteComment;
    }
}
