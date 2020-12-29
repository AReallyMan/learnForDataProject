package dddd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyangyang
 * @version 1.0
 * @date 2020/10/28 17:18
 */
public class FileDemo {
    /**
     * 编写一个程序，将 a.txt文件中的单词与 b.txt 文件中的单词交替合并到 c.txt 文件中，
     * a.txt,b.txt 文件中的单词用空格分隔
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        File fileA = new File("C:\\Users\\asus\\Desktop\\a.txt");
        FileInputStream fileInputStream = new FileInputStream(fileA);
        File fileB = new File("C:\\Users\\asus\\Desktop\\b.txt");
        FileInputStream fileInputStream2 = new FileInputStream(fileB);
        try {
            //文件a.txt
            int b;
            StringBuilder sb = new StringBuilder();
            List<String> l = new ArrayList();
            while ((b = fileInputStream.read()) != -1) {
                sb.append((char) b);
            }
            String[] s1 = String.valueOf(sb).split(" ");
            //文件b.txt
            int b2;
            StringBuilder sb2 = new StringBuilder();
            while ((b2 = fileInputStream2.read()) != -1) {
                sb2.append((char) b2);
            }
            String[] s2 = String.valueOf(sb2).split(" ");
            //交替存入c.txt
            int minLen = Math.min(s1.length, s2.length);
            for (int i = 0; i < minLen; i++) {
                for (int j = 0; j < minLen; j++) {
                    l.add(s1[i]);
                    l.add(s2[j]);
                    break;
                }
            }
            if (s1.length > s2.length) {
                for (int i = s2.length; i < s1.length; i++) {
                    l.add(s1[i]);
                }
            } else if (s1.length < s2.length) {
                for (int i = s1.length; i < s2.length; i++) {
                    l.add(s2[i]);
                }
            }
            //开始写入c.txt文件
            File file3 = new File("C:\\Users\\asus\\Desktop\\c.txt");
            FileWriter f = new FileWriter(file3);
            l.forEach((s) -> {
                try {
                    f.write(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("存儲成功");
            f.close();
        } catch (Exception e) {
        } finally {
            fileInputStream.close();
            fileInputStream2.close();
        }
    }
}
