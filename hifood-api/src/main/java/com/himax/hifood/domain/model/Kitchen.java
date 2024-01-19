package com.himax.hifood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class Kitchen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "kitchen")
	@ToString.Exclude
	private List<Restaurant> restaurants = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Kitchen kitchen = (Kitchen) o;
		return getId() != null && Objects.equals(getId(), kitchen.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
