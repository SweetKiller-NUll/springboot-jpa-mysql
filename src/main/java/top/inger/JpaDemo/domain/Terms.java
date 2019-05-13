package top.inger.JpaDemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@EntityListeners(AuditingEntityListener.class)//registeredTime,modifyTime的Date值自动获取
@JsonIgnoreProperties(value = {"createdTime","lastModifyTime"},allowGetters = true)//指定的字段不会被序列化和反序列化
@Table(name = "WINES_TERMS")//对应数据库中的表名(wines_terms)
public class Terms {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增长,同一张表中自增列最多只能有一列
    @Column(name = "id")//对应数据表的列名(id)
    private int termsId;

    @Column(name = "name")
    @NotBlank(message = "条款名称不能为空!")
    private String termsName;

    @Column(name = "company")
    @NotBlank(message = "条款公司/组织不能为空!")
    private String termsCompy;

    @Column(name = "brief")
    @NotBlank(message = "条款简述不能为空!")
    @Lob
    private String termsBrief;
    @NotBlank
    private String partOne;
    @NotBlank
    private String partOneTitle;
    @NotBlank
    @Lob
    private String partOneContent;

    private String partTwo;
    private String partTwoTitle;
    @Lob
    private String partTwoContent;

    private String partThree;
    private String partThreeTitle;
    @Lob
    private String partThreeContent;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)//封装成完整的时间“yyyy-MM-dd hh:MM:ss”的 Date类型
    @Column(nullable = false, updatable = false)
    private Date createdTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastModifyTime;
}
