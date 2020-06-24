package com.javaex.dao;

import java.util.List;

import com.javaex.vo.PersonVo;

public class phoneTest {

	public static void main(String[] args) {
		
		PhoneDao phoneDao = new PhoneDao();
		
		List<PersonVo> personList = phoneDao.getPersonList();
	    System.out.println(personList.toString());
		
		
		//PersonVo personvo = new PersonVo("fgdfg","fgdg","dfg");
		//phoneDao.personInsert(personvo);
		
		//PersonVo personVo = phoneDao.getPerson(1);
		//System.out.println(personVo.toString());
		

	}

}
