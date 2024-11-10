package com.sie.app.mes.model;

import com.sie.snest.engine.data.RecordSet;
import com.sie.snest.engine.model.Bool;
import com.sie.snest.engine.rule.Filter;
import com.sie.snest.sdk.BaseModel;
import com.sie.snest.sdk.DataType;
import com.sie.snest.sdk.annotation.meta.MethodService;
import com.sie.snest.sdk.annotation.meta.Model;
import com.sie.snest.sdk.annotation.meta.Property;
import com.sie.snest.sdk.annotation.orm.OneToMany;
import com.sie.snest.sdk.annotation.validate.Validate;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Model(name = "supplier",displayName = "供应类",isAutoLog = Bool.True)
public class Supplier extends BaseModel<Supplier> {
    @Validate.NotBlank(message = "名称不能为空！")
    @Validate.Size(min = 2,max = 20)
    @Property(displayName = "名称")
    private String name;

    @Property(displayName = "地址")
    private String address;

    @Property(columnName = "phone", displayName = "联系方式")
    @Validate.Phone(message = "联系方式不正确")
    private String phone;

    @Validate.Email(message = "邮箱格式不正确")
    @Property(displayName = "Email")
    private String email;

    @Property(displayName = "供应商资质文件",dataType = DataType.FILE,contentType = "pdf",toolTips = "上传供应商资质文件")
    private String certificationDocs;

    @OneToMany
    private List<Material> materialList;


}
