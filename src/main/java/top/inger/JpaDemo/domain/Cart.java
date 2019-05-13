package top.inger.JpaDemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WINES_CART")//对应数据库中的表名(wines_alcohol)
public class Cart {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增长,同一张表中自增列最多只能有一列
    @Column(name = "id")//对应数据表的列名(id)
    private int cartId;

    @Column(name = "usid")
    private int usId ;

    @Column(name = "alcname")
    private String alcName;

    @Column(name = "alcprice")
    private Double alcPrice;

    @Column(name = "brandName")
    private String brandName;

    private int alcNumber;

    @Column(name = "status")
    private Byte cartStatus=0;
}
