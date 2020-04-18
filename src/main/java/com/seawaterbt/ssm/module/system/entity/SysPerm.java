package com.seawaterbt.ssm.module.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@TableName("t_sys_perm")
@ApiModel(value="SysPerm对象", description="权限")
public class SysPerm extends Model<SysPerm> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "权限标记")
      private String name;

      @ApiModelProperty(value = "权限名称")
      private String title;

      @ApiModelProperty(value = "是否菜单")
      private Integer isMenu;

      @ApiModelProperty(value = "父Id")
      private Integer parentId;

      @ApiModelProperty(value = "图标")
      private String icon;

      @ApiModelProperty(value = "访问路径")
      private String url;

      @ApiModelProperty(value = "排序")
      private Integer num;


      public static final String ID = "id";

      public static final String NAME = "name";

      public static final String TITLE = "title";

      public static final String IS_MENU = "is_menu";

      public static final String PARENT_ID = "parent_id";

      public static final String ICON = "icon";

      public static final String URL = "url";

      public static final String NUM = "num";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

}
