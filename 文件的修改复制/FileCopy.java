package dddd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author zhangyangyang
 * @version 1.0
 * @date 2020/10/29 10:16
 */
public class FileCopy {
    /**
     * 将 e:\java 目录下的所有.java 文件复制到 e:\java_new 目录下，并 将原来文件的扩展名从.java 改为.jad。
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //源文件
        File fileSource=new File("E:\\java");
        String[] str = fileSource.list();
        FileInputStream fileInputStream=null;
        //目标文件
        File fileTo=new File("E:\\java_new");
        FileOutputStream fileOutputStream=null;
        String source;
        String to;
        //遍历source中所有.java的文件，读取内容写入到to目录下，设计到遍历和文件名的修改操作
        try {
            for(int i=0;i<str.length;i++){
                if(str[i].endsWith(".java")){
                    source = fileSource+ File.separator+str[i];
                    fileInputStream = new FileInputStream(source);
                    to=fileTo+File.separator+str[i].split("\\.")[0] + ".jad";
                    fileOutputStream=new FileOutputStream(to);
                    byte[] bytes=new byte[1024];
                    int byteRead;
                    while ((byteRead=fileInputStream.read())!=-1){
                        fileOutputStream.write(bytes,0,byteRead);
                    }
                }
            }
            System.out.println("执行成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            fileInputStream.close();
            fileOutputStream.close();
        }
    }
}
