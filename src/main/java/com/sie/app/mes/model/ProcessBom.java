package com.sie.app.mes.model;

import com.sie.snest.engine.model.Bool;
import com.sie.snest.sdk.BaseModel;
import com.sie.snest.sdk.annotation.meta.Model;
import com.sie.snest.sdk.annotation.meta.Property;
import com.sie.snest.sdk.annotation.orm.JoinColumn;
import com.sie.snest.sdk.annotation.orm.ManyToOne;

@Model(name = "process_bom",displayName = "工序BOM",isAutoLog = Bool.True)
public class ProcessBom extends BaseModel<ProcessBom> {
    @ManyToOne(displayName = "工序")
    @JoinColumn
    private Process process;

    @ManyToOne(displayName = "物料")
    @JoinColumn
    private Material material;

    @Property(displayName = "数量")
    private Double amount;


}
