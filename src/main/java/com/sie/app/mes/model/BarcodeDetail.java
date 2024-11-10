package com.sie.app.mes.model;

import com.sie.snest.sdk.BaseModel;
import com.sie.snest.sdk.annotation.meta.Model;
import com.sie.snest.sdk.annotation.meta.Property;

@Model(name = "barcode_detail",tableName = "barcode_detail",displayName = "条码明细")
public class BarcodeDetail extends BaseModel<BarcodeDetail> {
    @Property(displayName = "编码")
    private String code;
    @Property(displayName = "日期")
    private String date;
    @Property(displayName = "当前流水号")
    private Integer curSeq;

    public BarcodeDetail setCode(String code) {
        this.set("code", code);
        return this;
    }

    public String getCode() {
        return getStr("code");
    }

    public BarcodeDetail setDate(String date) {
        this.set("date", date);
        return this;
    }

    public String getDate() {
        return getStr("date");
    }

    public BarcodeDetail setCurSeq(Integer curSeq) {
        this.set("curSeq", curSeq);
        return this;
    }

    public Integer getCurSeq() {
        return getInt("curSeq");
    }
}
