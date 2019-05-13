package top.inger.JpaDemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)//registeredTime,modifyTime的Date值自动获取
@JsonIgnoreProperties(value = {"registeredTime","lastModifyTime"},allowGetters = true)//指定的字段不会被序列化和反序列化
@Table(name = "WINES_USER")//对应数据库中的表名(wines_user)
public class User {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增长,同一张表中自增列最多只能有一列
    @Column(name = "id")//对应数据表的列名(id)
    private int userId;

    @Column(name = "username")
    @NotBlank(message = "会员用户名不能为空!")
    @Length(min = 4,max = 10)
    private String userUsName;

    @Column(name = "password")
    @NotBlank(message = "会员密码不能为空!")
    @Length(min = 6,max = 16)
    @ColumnTransformer(
            write = "HEX(AES_ENCRYPT(?, 'salt'))",
            read = "AES_DECRYPT(UNHEX(password),'salt')"
    )
    private String userPassword;

    @Column(name = "name")
    @NotBlank(message = "会员姓名不能为空!")
    @Length(min = 2,max = 20)
    private String userName;

    @Column(name = "gender")
    @NotBlank(message = "会员性别不能为空!")
    private String userGender;

    @Column(name = "email")
    @NotBlank(message = "邮箱不能为空!")
    @Email
    private String userEmail;

    private Byte status=0;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)//封装成完整的时间“yyyy-MM-dd hh:MM:ss”的 Date类型
    @Column(nullable = false, updatable = false,name = "regTime")
    private Date registeredTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false,name = "lastModTime")
    private Date lastModifyTime;
}
