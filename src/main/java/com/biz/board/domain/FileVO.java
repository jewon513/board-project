package com.biz.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {

	private long f_id;
	private long f_b_id;
	private String f_original_filename;
	private String f_uuid_filename;
	private String f_path;
	
	
	
}
