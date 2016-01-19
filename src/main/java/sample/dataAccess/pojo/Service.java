package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique=true)
	private String name;
	@Column(unique=true)
	private String code;
	private String description;

	@NotNull
	private boolean isAvailable = true;

	private double price;
}
