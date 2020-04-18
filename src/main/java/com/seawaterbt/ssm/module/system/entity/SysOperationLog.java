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
 * 系统操作日志
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@TableName("t_sys_operation_log")
@ApiModel(value="SysOperationLog对象", description="系统操作日志")
public class SysOperationLog extends Model<SysOperationLog> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "日志名称")
      private String logName;

      @ApiModelProperty(value = "用户Id")
      private Integer userId;

      @ApiModelProperty(value = "用户名")
      private String username;

      @ApiModelProperty(value = "类名")
      private String className;

      @ApiModelProperty(value = "方法名")
      private String method;

      @ApiModelProperty(value = "创建名称")
      private Integer createTime;

      @ApiModelProperty(value = "描述信息")
      private String message;

      @ApiModelProperty(value = "操作IP")
      private String ip;

      @ApiModelProperty(value = "操作GPS")
      private String gps;

      @ApiModelProperty(value = "操作地理位置")
      private String address;

      @ApiModelProperty(value = "操作结果")
      private String result;


      public static final String ID = "id";

      public static final String LOG_NAME = "log_name";

      public static final String USER_ID = "user_id";

      public static final String USERNAME = "username";

      public static final String CLASS_NAME = "class_name";

      public static final String METHOD = "method";

      public static final String CREATE_TIME = "create_time";

      public static final String MESSAGE = "message";

      public static final String IP = "ip";

      public static final String GPS = "gps";

      public static final String ADDRESS = "address";

      public static final String RESULT = "result";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

}
