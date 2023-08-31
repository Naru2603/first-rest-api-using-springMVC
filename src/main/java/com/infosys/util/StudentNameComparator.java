package com.infosys.util;

import java.util.Comparator;

import com.infosys.entity.Student;

public class StudentNameComparator implements Comparator<Student>{

	@Override
	public int compare(Student s1, Student s2) {
	
		return s1.getName().compareTo(s2.getName());
	}

	 
}
