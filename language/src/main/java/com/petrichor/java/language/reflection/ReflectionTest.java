//package com.yufei.languagebasic.reflection;
//
//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//public class ReflectionTest {
//    public static void main(String[] args) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
//        Student student = new Student();
//        student.setAge(10);
//        student.setGender("nan");
//        student.setGraduate(true);
//        MiniStudent miniStudent = new MiniStudent();
//        ReflectionTest reflectionTest = new ReflectionTest();
//        reflectionTest.convert(student, miniStudent);
//        System.out.println(miniStudent);
//    }
//
//    public <F, T> void convert(F from, T to) throws IntrospectionException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
//        Class tClass = to.getClass();
//        Class<?> fClass = from.getClass();
//        Field[] fields = tClass.getDeclaredFields();
////                        Field[] fields = fClass.getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            Field declaredField = null;
//            try {
//                declaredField = fClass.getDeclaredField(field.getName());
//                fClass.getField();
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//            if(declaredField != null) {
//                declaredField.setAccessible(true);
//                field.set(to, declaredField.get(from));
//            }
//                            /*PropertyDescriptor tpd = new PropertyDescriptor(field.getName(), tClass);
//                            PropertyDescriptor fpd = new PropertyDescriptor(field.getName(), fClass);
//                            //获得get方法
//                            Method get = fpd.getReadMethod();
//                            Object getValue = get.invoke(from);
//
//                            //获得set方法
//                            Method method = tpd.getWriteMethod();
//                            method.invoke(to, getValue);*/
//        }
//    }
//}
