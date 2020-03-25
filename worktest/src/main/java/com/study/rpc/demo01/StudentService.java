package com.study.rpc.demo01;
/**
 * StudentService
 * 
 */
public interface StudentService {
	/**
	   *   获取信息
	 * @return
	 */
	public Student getInfo();
	
	public boolean printInfo(Student student);
}
