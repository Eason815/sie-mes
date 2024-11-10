package com.sie.app.mes.model;

import com.sie.app.mes.util.CodeGenerator;
import com.sie.snest.engine.data.RecordSet;
import com.sie.snest.engine.model.Bool;
import com.sie.snest.engine.rule.Filter;
import com.sie.snest.engine.security.User;
import com.sie.snest.sdk.BaseModel;
import com.sie.snest.sdk.annotation.Dict;
import com.sie.snest.sdk.annotation.meta.MethodService;
import com.sie.snest.sdk.annotation.meta.Model;
import com.sie.snest.sdk.annotation.meta.Property;
import com.sie.snest.sdk.annotation.orm.JoinColumn;
import com.sie.snest.sdk.annotation.orm.ManyToOne;
import com.sie.snest.sdk.annotation.orm.Option;
import com.sie.snest.sdk.annotation.orm.Selection;
import com.sie.snest.sdk.annotation.validate.Validate;
import jnr.ffi.annotations.In;

import java.util.List;
import java.util.Map;

/**
 * @author Eason
 */
@Model(name = "material",
        displayName = "物料",
        description = "物料信息",
        isAutoLog = Bool.True
)
public class Material extends BaseModel<Material> {

    @Validate.NotBlank(message = "物料名称不能为空！")
    @Validate.Size(min = 2,max = 20)
    @Property(displayName = "名称",displayForModel = true)
    private String name;

    @Validate.NotBlank(message = "编码规则不能为空！")
    @Property(displayName = "编码规则")
    private String code;

    @Dict(typeCode = "material_type")
    @Property(displayName = "类型")
    private String type;

    @Dict(typeCode = "material_unit")
    @Property(displayName = "单位")
    private String unit;

    @Property(displayName = "描述")
    private String description;

    @Property(displayName = "库存量")
    @Validate.Min(value = 10,message = "库存不能小于10件")
    private Integer stockQuantity;

    @Property(displayName = "单价")
    private Integer price;

    @ManyToOne(displayName = "供应类",displayFormat = "${name}")
    @JoinColumn(name = "supplier",referencedProperty = "name")
    private Supplier supplier;

    public Material setDateFormat(String dateFormat) {
        this.set("dateFormat", dateFormat);
        return this;
    }

    public String getDateFormat() {
        return getStr("dateFormat");
    }

    public Material setName(String name) {
        this.set("name", name);
        return this;
    }

    public String getName() {
        return getStr("name");
    }

    public Material setCode(String code) {
        this.set("code", code);
        return this;
    }

    public String getCode() {
        return getStr("code");
    }

    public Material setType(String type) {
        this.set("type", type);
        return this;
    }

    public String getType() {
        return getStr("type");
    }

    public Material setUnit(String unit) {
        this.set("unit", unit);
        return this;
    }

    public String getUnit() {
        return getStr("unit");
    }

    public Material setDescription(String description) {
        this.set("description", description);
        return this;
    }

    public String getDescription() {
        return getStr("description");
    }

    public Material setStockQuantity(Integer stockQuantity) {
        this.set("stockQuantity", stockQuantity);
        return this;
    }

    public Integer getStockQuantity() {
        return getInt("stockQuantity");
    }

    public Material setPrice(Integer price) {
        this.set("price", price);
        return this;
    }

    public Integer getPrice() {
        return getInt("price");
    }

    public Material setSupplier(Supplier supplier) {
        this.set("supplier", supplier);
        return this;
    }

    public Supplier getSupplier() {
        return (Supplier) this.get("supplier");
    }

    @MethodService(name = "create",auth = "create",description = "创建")
    public RecordSet create(RecordSet rs, List<Material> valueList){
        valueList.forEach(m->{
            String materialCode=new BarcodeRule().genBarcode(m.getCode());
            m.setCode(materialCode);
        });
        return (RecordSet) getMeta().get("material").callSuper(Material.class,"create",valueList);
    }

    @MethodService(name = "update",auth = "update", description = "更新")
    public RecordSet update(RecordSet rs, Map<String,Object> values){
        System.out.println("更新方法入参"+values);
        RecordSet rec = (RecordSet) rs.callSuper(Material.class,"update",values);
        return rec;
    }


    @MethodService(name = "search", auth = "search",description = "分页查询方法")
    public List<Map<String,Object>> search(RecordSet rs, Filter filter,
                                           List<String> properties,
                                           Integer limit,Integer offset,String order){
        List<Map<String,Object>> result =
                (List<Map<String, Object>>) rs.callSuper(Material.class,"search",filter,properties,limit,offset,order);
        System.out.println("分页查询方法返回参数"+result);
        return result;
    }

    @MethodService(name = "delete",auth = "delete",description = "删除")
    public boolean delete(RecordSet rs){
        System.out.println("删除方法入参"+rs.getId());
        rs.callSuper(Material.class,"delete");
        return Boolean.TRUE;
    }

}
