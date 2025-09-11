package com.jhp.foryouth.board.entity;

public enum QuestionStatus {
    NO("답변 대기 중"),
    YES("답변 완료");

    private final String displayName;

    QuestionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
