package top.inger.JpaDemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)//adminRegTime,adminModTime的Date值自动获取
@JsonIgnoreProperties(value = {"adminRegTime", "adminModTime"}, allowGetters = true)//指定的字段不会被序列化和反序列化
@Table(name = "WINES_ADMIN")//对应数据库中的表名(wines_admin)
public class Admin {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增长,同一张表中自增列最多只能有一列
    @Column(name = "id")//对应数据表的列名(id)
    private int adminId;

    @Column(name = "username")
    @NotBlank(message = "管理员用户名不能为空!")
    @Length(min = 4, max = 8)
    private String adminUsName;

    @Column(name = "password")
    @NotBlank(message = "管理员密码不能为空!")
    @Length(min = 3, max = 6)
    @ColumnTransformer(
            write = "HEX(AES_ENCRYPT(?, 'key'))",
            read = "AES_DECRYPT(UNHEX(password),'key')"
    )
    private String adminPassword;

    @Column(name = "name")
    @NotBlank(message = "管理员姓名不能为空!")
    @Length(min = 2, max = 20)
    private String adminName;

    @Column(name = "phone")
    @NotBlank(message = "手机号不能为空!")
    @Length(min = 11, max = 11)
    private String adminPhone;

    @Column(name = "status")
    private Byte adminStatus = 0;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)//封装成完整的时间“yyyy-MM-dd hh:MM:ss”的 Date类型
    @Column(nullable = false, updatable = false, name = "regTime")
    private Date adminRegTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "lastModTime")
    private Date adminLastModTime;

}
