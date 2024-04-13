package de.sindeev.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="update")
@TypeDef(name="jsonb", typeClass=JsonBinaryType.class)
public class IncomingUpdate {
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Long id;
	@Type(type="jsonb")
	@Column(columnDefinition="jsonb")
	private Update event;
}
