package com.study.javanewspecial.java5.day2;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MyClassLoader extends ClassLoader {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Path src = Paths.get("worktest/target/classes/com\\study" +
                "\\javanewspecial\\java5\\day2\\ClassLoaderAttachment.class");
        src = src.toAbsolutePath();
        Path dest = Paths.get("worktest/classloader/ClassLoaderAttachment.class").toAbsolutePath();
        System.out.println(src);
        System.out.println(dest);

        Files.copy(src,dest,StandardCopyOption.REPLACE_EXISTING);

    }

    public static void encrypt(InputStream is, OutputStream os) throws Exception {
        int b = -1;

        while ((b = is.read()) != -1) {
            os.write(b ^ 0xff);
        }

    }

    private String classDir;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println(name);
        String classFileName = classDir + "\\" + name.substring(name.lastIndexOf(".") + 1) + ".class";
        classFileName = Paths.get(classFileName).toAbsolutePath().toString();
        System.out.println("classFileName" + classFileName);
        try {
            InputStream is = new FileInputStream(classFileName);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            bos.write(is.readAllBytes());
            byte[] b = bos.toByteArray();
            bos.close();
            is.close();
            return defineClass(name, b, 0, b.length);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.endsWith("ClassLoaderAttachment")){
            return findClass(name);
        }
        return super.loadClass(name);

    }

    public MyClassLoader() {

    }

    public MyClassLoader(String classDir) {
        this.classDir = classDir;
    }


}
