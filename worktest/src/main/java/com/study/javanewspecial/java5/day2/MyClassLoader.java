package com.study.javanewspecial.java5.day2;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String srcpath = "E:\\MyEclipse 8.5WorkSpace\\javaMail1\\bin\\cn\\itcast\\day2\\ClassLoaderAttachment.class";
		String destDir = args[0];
		System.out.println(srcpath);
		InputStream is = new FileInputStream(srcpath);
		String destFileName = srcpath.substring(srcpath.lastIndexOf("\\")+1);
		String destFilePath = destDir+"\\"+destFileName;
		OutputStream os = new FileOutputStream(destFilePath);
		encrypt(is, os);
		os.close();  
		is.close();
	}
	
	public static void encrypt(InputStream is,OutputStream os)throws Exception{
		int b =-1;
		
		while((b= is.read())!=-1){
			os.write(b ^ 0xff);
		}
		
	}

	private String classDir;
	@Override
	protected Class<?> findClass(String name)  throws ClassNotFoundException{
		
		String classFileName = classDir + "\\"+name.substring(name.lastIndexOf(".")+1)+".class";
		try {
			InputStream is = new FileInputStream(classFileName);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			//encrypt(is, bos);//����
			System.out.println("aaa");
			is.close();
			byte[] b = bos.toByteArray();
			bos.close();
			return defineClass(b, 0, b.length);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.findClass(name);
	}
	public MyClassLoader(){
		
	}
	public MyClassLoader(String classDir){
		this.classDir = classDir;
	}
}
