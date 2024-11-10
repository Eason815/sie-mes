package com.sie.app.mes.model;

import com.sie.snest.engine.model.Bool;
import com.sie.snest.engine.rule.Filter;
import com.sie.snest.sdk.BaseModel;
import com.sie.snest.sdk.annotation.meta.MethodService;
import com.sie.snest.sdk.annotation.meta.Model;
import com.sie.snest.sdk.annotation.meta.Property;
import com.sie.snest.sdk.annotation.orm.Option;
import com.sie.snest.sdk.annotation.orm.Selection;
import com.sie.snest.sdk.annotation.validate.Validate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Primary;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Model(name = "barcode_rule",tableName = "barcode_rule",displayName = "编码规则",isAutoLog = Bool.True)
public class BarcodeRule extends BaseModel<BarcodeRule> {
    @Property(displayName = "编码")
    @Validate.Unique
    private String code;

    @Property(displayName = "日期格式")
    @Selection(values = {
            @Option(label = "年月日",value = "1"),
            @Option(label = "年月",value = "2"),
            @Option(label = "年",value = "3")
    })
    private String date_type;

    @Property(displayName = "前缀")
    private String prefix;

    @Property(displayName = "流水号长度")
    private Integer seqLength;

    public BarcodeRule setCode(String code) {
        this.set("code", code);
        return this;
    }

    public String getCode() {
        return getStr("code");
    }

    public BarcodeRule setPrefix(String prefix) {
        this.set("prefix", prefix);
        return this;
    }

    public String getPrefix() {
        return getStr("prefix");
    }

    public BarcodeRule setSeqLength(Integer seqLength) {
        this.set("seqLength", seqLength);
        return this;
    }

    public Integer getSeqLength() {
        return getInt("seqLength");
    }

    /**
     * 查询当前流水号
     * @param code 编码
     * @param date 日期
     * @return 当前流水号信息
     */
    private static BarcodeDetail getBarcodeDetail(String code,String date){
        BarcodeDetail barcodeDetail;
        // 查询当前的流水号
        List<BarcodeDetail> barcodeDetails = new BarcodeDetail().
                search(Filter.AND(Filter.equal("code",code),
                                Filter.equal("date",date)),
                        Collections.singletonList("*"),1,0,null);
        if(CollectionUtils.isNotEmpty(barcodeDetails)) {
            // 如果查询到，则获取流水号
            barcodeDetail = barcodeDetails.get(0);
        } else {
            // 如果查询不到，新增一条流水号
            barcodeDetail = new BarcodeDetail();
            barcodeDetail.setCode(code);
            barcodeDetail.setDate(date);
            barcodeDetail.setCurSeq(1);
            barcodeDetail.create();
        }
        return barcodeDetail;
    }

    /**
     * 生成条码
     * @param code 编码
     * @return 条码
     */
    @MethodService(name = "genBarcode",description = "生成编码", auth = "genBarcode")
    public String genBarcode(String code){
        List<BarcodeRule> barcodeRules = search(Filter.equal("code",code),
                Collections.singletonList("*"),1,0,null);
        if(CollectionUtils.isEmpty(barcodeRules)){
            throw new IllegalArgumentException("找不到编码规则");
        }
        BarcodeRule rule = barcodeRules.get(0);
        Date today = new Date();
        String dateString = new SimpleDateFormat("yyyyMMdd").format(today);

        if(rule.getDate_type().equals("1")){
            dateString = new SimpleDateFormat("yyyyMMdd").format(today);
        } else if (rule.getDate_type().equals("2")) {
            dateString = new SimpleDateFormat("yyyyMM").format(today);
        } else if (rule.getDate_type().equals("3")) {
            dateString = new SimpleDateFormat("yyyy").format(today);
        } else{
            throw new IllegalArgumentException("找不到日期类型"+rule.getDate_type());
        }

        BarcodeDetail barcodeDetail = getBarcodeDetail(code,dateString);
        Integer curSeq = barcodeDetail.getCurSeq();
        String barcodeFormat = "%s%s%0" + rule.getSeqLength() + "d";
        String result = String.format(barcodeFormat,rule.getPrefix(),dateString,curSeq);
        barcodeDetail.setCurSeq(curSeq + 1);
        barcodeDetail.update();
        return result;
    }

    public BarcodeRule setDate_type(String date_type) {
        this.set("date_type", date_type);
        return this;
    }

    public String getDate_type() {
        return getStr("date_type");
    }
}
