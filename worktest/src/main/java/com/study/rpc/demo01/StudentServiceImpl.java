package com.study.rpc.demo01;

/**
 * StudentServiceImpl
 * 
 */
@Service(StudentService.class)
public class StudentServiceImpl implements StudentService {

	@Override
	public Student getInfo() {
		Student person = new Student();
		person.setAge(18);
		person.setName("arrylist");
		person.setSex("女");
		return person;
	}

	@Override
	public boolean printInfo(Student person) {
		if (person != null) {
			System.out.println(person);
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		new Thread(()->{
			System.out.println("111");
		}).start();;
	}
}

