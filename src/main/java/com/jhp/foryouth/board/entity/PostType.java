package com.jhp.foryouth.board.entity;

public enum PostType {
    EDUCATION("교육/자격증"),
    EMPLOYMENT_SUPPORT("취업지원"),
    FINANCIAL_SUPPORT("금융지원"),
    HOUSING_SUPPORT("주거지원"),
    LOCAL_NEWS("지역소식"),
    WELFARE_BENEFIT("복지혜택");

    private final String displayName;

    PostType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
