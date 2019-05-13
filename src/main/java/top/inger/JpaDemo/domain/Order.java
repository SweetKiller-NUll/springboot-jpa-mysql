package top.inger.JpaDemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)//registeredTime,modifyTime的Date值自动获取
@JsonIgnoreProperties(value = {"createdTime", "lastModTime"}, allowGetters = true)//指定的字段不会被序列化和反序列化
@Table(name = "WINES_ORDER")//对应数据库中的表名(wines_order)
public class Order {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增长,同一张表中自增列最多只能有一列
    @Column(name = "id")//对应数据表的列名(id)
    private int orderId;

    @Column(name = "total")
    private Double totalPrice;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)//封装成完整的时间“yyyy-MM-dd hh:MM:ss”的 Date类型
    @Column(nullable = false, updatable = false)
    private Date createdTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastModifyTime;

    @Column(name = "usid")
    private int usId ;

    @Column(name = "remarks")
    private String orderRemarks;

    private Byte status=0;
}
