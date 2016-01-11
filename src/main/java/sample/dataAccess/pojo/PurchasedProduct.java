package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class PurchasedProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private double amount;
	private double cost;
	private Date purchaseDate;

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="productId")
	private Product product;

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="stayId")
	private Stay stay;
}
