package com.wevioo.model;

import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wevioo.model.enumeration.DayOfWeekEnum;
import com.wevioo.model.enumeration.PeriodeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "horaire")
@NoArgsConstructor
@AllArgsConstructor
public class Horaire {
@Id
@Column(name = "id", nullable = false, unique = true)
private String id;

@Column(name = "jour", nullable = false)
@Enumerated(EnumType.STRING)
private DayOfWeekEnum jour;

@Column(name = "heure_debut", nullable = false)
private Time heureDebut;

@Column(name = "heure_fin", nullable = false)
private Time heureFin;

@ManyToOne(cascade = { CascadeType.ALL })
@JoinColumn(name = "unite_id", nullable = false)
private Unite unite;

@Column(nullable = true)
private PeriodeEnum periode;

@OneToMany(mappedBy = "horaires", fetch = FetchType.EAGER)
private List<Pause> children;



	
}
