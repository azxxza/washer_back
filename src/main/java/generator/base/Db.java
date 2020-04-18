package generator.base;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import lombok.Data;

@Data
public class Db {
    private String url;
    private String username;
    private String password;
    private String driverName;
    private DbType dbType;
    private ITypeConvert typeConvert;

    public Db(String url, String username, String password, String driverName, DbType dbType,ITypeConvert typeConvert) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driverName = driverName;
        this.dbType = dbType;
        this.typeConvert = typeConvert;
    }
}
