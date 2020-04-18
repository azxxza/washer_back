package generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import generator.base.Db;
import generator.base.MyBatisPlusUtils;

/**
 * @author azx
 */
public class AdminGenerator {

    private static final String BASE_PACKAGE = "com.seawaterbt.ssm.module.business";

//    private static final String BASE_PACKAGE = "TTT";

    public static void main(String[] args) {

        StrategyConfig strategy = new StrategyConfig();

        strategy.setInclude("care_app_users_washerbind");

        PackageConfig pc = new PackageConfig();
        // 包配置
        pc.setParent(null);
        pc.setParent(BASE_PACKAGE);
        pc.setMapper("dao");
        pc.setXml("dao.mapping");
        //本项目没用，生成之后删掉
        pc.setService("service");
        //本项目没用，生成之后删掉
        pc.setServiceImpl("service.impl");
        //本项目没用，生成之后删掉
        pc.setEntity("entity");

        TemplateConfig tc = new TemplateConfig();

        MyBatisPlusUtils.generator(getSqlServer(), strategy, pc, tc);

    }

    private static Db getSqlServer() {
        String dbName = "poker_admin";
        String username = "SA";
        String password = "Qwe!@#123";
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=" + dbName;
        return new Db(url, username, password, driverName, DbType.SQL_SERVER, new SqlServerTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
    }

    private static Db getMysql() {
        String dbName = "poker_admin";
        String username = "root";
        String password = "";
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/" + dbName;
        return new Db(url, username, password, driverName, DbType.MYSQL, new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
    }
}
