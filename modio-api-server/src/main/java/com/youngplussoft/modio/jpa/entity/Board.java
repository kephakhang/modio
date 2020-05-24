package com.youngplussoft.modio.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TB_BOARD")
public class Board implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int sn = 0;
	int mb_id = 0;
	String title = null;
	String rmk = null;
}
