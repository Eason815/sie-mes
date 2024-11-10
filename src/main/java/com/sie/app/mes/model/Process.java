package com.sie.app.mes.model;

import com.sie.snest.engine.model.Bool;
import com.sie.snest.sdk.BaseModel;
import com.sie.snest.sdk.annotation.meta.Model;
import com.sie.snest.sdk.annotation.meta.Property;
import com.sie.snest.sdk.annotation.orm.JoinColumn;
import com.sie.snest.sdk.annotation.orm.ManyToOne;
import com.sie.snest.sdk.annotation.orm.OneToMany;

import java.util.List;

@Model(name = "process",displayName = "工序",isAutoLog = Bool.True)
public class Process extends BaseModel<Process> {
    @Property(displayName = "名称")
    private String name;
    @Property(displayName = "编码")
    private String code;

    @ManyToOne(displayName = "工艺路线",displayFormat = "${name}")
    @JoinColumn(name = "processRoute",referencedProperty = "name")
    private ProcessRoute processRoute;

    @OneToMany
    private List<ProcessBom> processBomList;

}
