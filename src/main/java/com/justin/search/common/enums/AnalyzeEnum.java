package com.justin.search.common.enums;

public enum AnalyzeEnum {
    /**
     * 标准分词器
     */
    STANDARD("standard"),
    /**
     * IK 最细粒度划分
     */
    IK_MAX_WORD("ik_max_word"),
    /**
     * IK 最少切分
     */
    IK_SMART("ik_smart");

    AnalyzeEnum(String analyze) {
        this.analyze = analyze;
    }

    private String analyze;

    public String getAnalyze() {
        return analyze;
    }

    public void setAnalyze(String analyze) {
        this.analyze = analyze;
    }
}
