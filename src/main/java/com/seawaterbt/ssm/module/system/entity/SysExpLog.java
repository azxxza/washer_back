package com.seawaterbt.ssm.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统异常信息
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@TableName("t_sys_exp_log")
@ApiModel(value="SysExpLog对象", description="系统异常信息")
public class SysExpLog extends Model<SysExpLog> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "创建人")
      private Integer userId;

      @ApiModelProperty(value = "创建人名称")
      private String userName;

      @ApiModelProperty(value = "请求接口url")
      private String url;

      @ApiModelProperty(value = "异常类型")
      private String expType;

      @ApiModelProperty(value = "异常具体内容")
      private String expContent;

      @ApiModelProperty(value = "异常原因")
      private String expReason;

      @ApiModelProperty(value = "创建时间")
      private Integer createTime;


      public static final String ID = "id";

      public static final String USER_ID = "user_id";

      public static final String USER_NAME = "user_name";

      public static final String URL = "url";

      public static final String EXP_TYPE = "exp_type";

      public static final String EXP_CONTENT = "exp_content";

      public static final String EXP_REASON = "exp_reason";

      public static final String CREATE_TIME = "create_time";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

}
