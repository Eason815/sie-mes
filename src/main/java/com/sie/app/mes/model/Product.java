package com.sie.app.mes.model;

import com.sie.snest.engine.model.Bool;
import com.sie.snest.sdk.BaseModel;
import com.sie.snest.sdk.annotation.meta.Model;
import com.sie.snest.sdk.annotation.meta.Property;
import com.sie.snest.sdk.annotation.orm.OneToMany;
import com.sie.snest.sdk.annotation.orm.Option;
import com.sie.snest.sdk.annotation.orm.Selection;
import com.sie.snest.sdk.annotation.validate.Validate;

import java.util.List;

/**
 * @author Eason
 */
@Model(name = "product", description = "产品", displayName = "产品",isAutoLog = Bool.True)
public class Product extends BaseModel<Product> {

    @Validate.NotBlank(message = "产品名称不能为空！")
    @Validate.Size(min = 2,max = 20)
    @Property(displayName = "名称")
    private String name;

    @Validate.NotBlank(message = "产品规格不能为空！")
    @Property(displayName = "规格")
    private String form;

    @Property(displayName = "产品状态",defaultValue = "1",widget = "select",length = 256)
    @Selection(values = {@Option(label = "设计中",value = "1"),
            @Option(label = "生产中",value = "2"),
            @Option(label = "已完成",value = "3")})
    private String status;

    @OneToMany
    private List<ProductBom> productBomList;
}
