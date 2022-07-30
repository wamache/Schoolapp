package com.schoolfeespayment.student.entity;

import com.schoolfeespayment.POJO.Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="studentname", nullable=false)
	private String studentName;
	@Column(name="regnumber", nullable=false)
	private String regNumber;

	@ManyToOne
	@JoinColumn(
			nullable = false,
			name = "parent_id"
	)

	private Parent parent;
	







	
	

}
