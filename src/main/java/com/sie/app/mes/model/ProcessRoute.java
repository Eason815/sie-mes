package com.sie.app.mes.model;

import com.sie.snest.engine.model.Bool;
import com.sie.snest.sdk.BaseModel;
import com.sie.snest.sdk.annotation.meta.Model;
import com.sie.snest.sdk.annotation.meta.Property;
import com.sie.snest.sdk.annotation.orm.JoinColumn;
import com.sie.snest.sdk.annotation.orm.ManyToOne;
import com.sie.snest.sdk.annotation.validate.Validate;

@Model(name = "process_route",displayName = "工艺路线", isAutoLog = Bool.True)
public class ProcessRoute extends BaseModel<ProcessRoute> {
    @Validate.NotBlank(message = "产品名称不能为空！")
    @Validate.Size(min = 2,max = 20)
    @Property(displayName = "名称")
    private String name;

    @Validate.NotBlank(message = "产品编码不能为空！")
    @Property(displayName = "编码")
    private String code;

    @ManyToOne(displayName = "关联产品",displayFormat = "${name}")
    @JoinColumn(name = "product",referencedProperty = "name")
    private Product product;
}
