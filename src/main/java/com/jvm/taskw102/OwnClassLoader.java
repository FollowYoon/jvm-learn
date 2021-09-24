package com.jvm.taskw102;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zyj
 * @date 2021/9/18 16:48
 */
public class OwnClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        String className = "Hello";
        String methodName = "hello";

        ClassLoader classLoader = new OwnClassLoader();
        Class<?> clazz = classLoader.loadClass(className);
        Object classInst = clazz.newInstance();
        clazz.getMethod(methodName).invoke(classInst);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String suffix = ".xlass";
        // 增加包名路径转换
        name = name.replace(".", "/");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name + suffix);
        try{
            int length = inputStream.available();
            byte[] bytes = new byte[length];
            inputStream.read(bytes);

            byte[] decodeBytes = new byte[length];
            for (int i = 0; i < length; i++) {
                decodeBytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass(name, decodeBytes,0, length);
        }catch (IOException e){
            throw new ClassNotFoundException(name, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
