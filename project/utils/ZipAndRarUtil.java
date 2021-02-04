package com.ld.core.utils;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.NativeStorage;
import de.innosystec.unrar.rarfile.FileHeader;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;

/**
 * zip和rar解压缩工具类
 *
 * @author Tian
 */
public class ZipAndRarUtil {

    private static Logger logger = LoggerFactory.getLogger(ZipAndRarUtil.class);

    /**
     * 解压rar
     *
     * @param sourceRarPath 需要解压的rar文件全路径
     * @param destDirPath   需要解压到的文件目录
     * @throws Exception
     */
    public static void unrar(String sourceRarPath, String destDirPath)  {
        NativeStorage sourceRar = new NativeStorage(new File(sourceRarPath));
        File destDir = new File(destDirPath);
        Archive archive = null;
        FileOutputStream fos = null;
        logger.info(sourceRarPath+"---Starting 文件解压");
        try {
            archive = new Archive(sourceRar);
            FileHeader fh = archive.nextFileHeader();
            File destFileName = null;
            while (fh != null) {
                String compressFileName = fh.getFileNameW().trim();
                destFileName = new File(destDir.getAbsolutePath() + "/" + compressFileName);
                if (fh.isDirectory()) {
                    if (!destFileName.exists()) {
                        destFileName.mkdirs();
                    }
                    fh = archive.nextFileHeader();
                    continue;
                }
                if (!destFileName.getParentFile().exists()) {
                    destFileName.getParentFile().mkdirs();
                }


                fos = new FileOutputStream(destFileName);
                archive.extractFile(fh, fos);
                fos.close();
                fos = null;
                fh = archive.nextFileHeader();
            }
            logger.info("解压成功！"+destDirPath);
        } catch (Exception e) {
            logger.error("解压失败！"+sourceRarPath,e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                    fos = null;
                } catch (Exception e) {
                    logger.error("关闭失败",e);
                }
            }
            if (archive != null) {
                try {
                    archive.close();
                    archive = null;
                } catch (Exception e) {
                    logger.error("关闭失败",e);
                }
            }
        }
    }


    /**
     * 解压Zip文件
     *
     * @param zipFileName  需要解压缩的文件位置
     * @param descFileName 将文件解压到某个路径
     * @throws IOException
     */
    public static void unZip(String zipFileName, String descFileName) {
        logger.info("开始文件解压："+zipFileName);
        ZipFile zipFile=null;
        String descFileNames = descFileName;
        String encoding="UTF-8";
        if(getOsName()==3)
            encoding="GBK";
        if (!descFileNames.endsWith(File.separator)) {
            descFileNames = descFileNames + File.separator;
        }
        try {
            zipFile = new ZipFile(zipFileName,encoding);
            ZipEntry entry = null;
            String entryName = null;
            String descFileDir = null;
            byte[] buf = new byte[4096];
            int readByte = 0;
            @SuppressWarnings("rawtypes")
            Enumeration enums = zipFile.getEntries();
            while (enums.hasMoreElements()) {
                entry = (ZipEntry) enums.nextElement();
                entryName = entry.getName();
                descFileDir = descFileNames + entryName;
                File file = new File(descFileDir);
                if (entry.isDirectory()) {
                    if (!file.exists())
                        file.mkdirs();
                    continue;
                }
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                OutputStream os = new FileOutputStream(file);

                InputStream is = zipFile.getInputStream(entry);
                while ((readByte = is.read(buf)) != -1) {
                    os.write(buf, 0, readByte);
                }
                os.close();
                is.close();
            }
            logger.info("解压成功："+descFileName);
        } catch (Exception e) {
            logger.error("解压失败！"+zipFileName,e);
        }finally {
            try {
                zipFile.close();
            } catch (IOException e) {
                logger.error("关闭失败！"+zipFileName,e);
            }
        }

    }

    /**
     * 判断操作系统
     */
    private static  int getOsName(){
        String osName = System.getProperty("os.name");
        int osType=0;
        osName = osName.toLowerCase();
        if (osName.startsWith("linux")) {
            osType = 1;
        } else if (osName.startsWith("solaris")) {
            osType = 2;
        } else if (osName.startsWith("windows")){
            osType = 3;
        }
        return osType;
    }

    public static void main(String[] args)  {
        ZipAndRarUtil.unZip("F:\\SQRJ\\tmp/aaaa.zip", "F:\\SQRJ\\tmp\\unzip");
        ZipAndRarUtil.unrar("F:\\SQRJ\\tmp\\aaaa.rar", "F:\\SQRJ\\tmp\\unrar");

    }

}