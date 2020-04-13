package ru.omsu.imit.course3.second.lab5.server.selectioncommittee.rest.response;

public class TodoItemInfoResponse extends BaseResponseObject {

    private String text;
    private int id;

    public TodoItemInfoResponse(int id, String text) {
        super();
        this.id = id;
        this.text = text;
    }

    protected TodoItemInfoResponse() {
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

}