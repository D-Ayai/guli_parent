package excel.write;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author testjava
 * @since 2021-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_subject")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;


    @ExcelProperty("课程类别ID")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ExcelProperty("类别名称")
    private String title;

    @ExcelProperty("父ID")
    private String parentId;

    @ExcelProperty("排序字段")
    private Integer sort;

    @ExcelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ExcelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
