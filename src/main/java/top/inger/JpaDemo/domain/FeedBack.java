package top.inger.JpaDemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@JsonIgnoreProperties(value = {"feedCreatedTime","processingTime"}, allowGetters = true)//指定的字段不会被序列化和反序列化
@Table(name = "WINES_FEEDBACK")//对应数据库中的表名(wines_feedback)
public class FeedBack {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增长,同一张表中自增列最多只能有一列
    @Column(name = "id")//对应数据表的列名(id)
    private int feedBackId;

    private Byte feedStatus;

    @Column(name = "title")
    @NotBlank(message = "反馈标题不能为空!")
    private String feedTitle;

    @Column(name = "content")
    @NotBlank(message = "反馈内容不能为空!")
    @Lob
    private String feedContent;

    @Column(name = "email")
    @NotBlank(message = "反馈人邮箱不能为空!")
    @Email
    private String feedBackEmail;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)//封装成完整的时间“yyyy-MM-dd hh:MM:ss”的 Date类型
    @Column(nullable = false, updatable = false)
    private Date feedCreatedTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date processingTime;
}
