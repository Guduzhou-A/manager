package com.baicells.manager.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class FileUtil {

    /**
     * upload:(上传本机)
     *
     * @return String
     * @throws
     * @author meishen
     * @Date 2017    2017年7月21日		下午3:50:43
     * @since CodingExample　Ver 1.0.0
     */
    public static String upload(MultipartFile file, String rooPath, String tag) {
        String filePath = "";
        if (file.getSize() > 0) {
            try {
                String tmpName = file.getOriginalFilename();
                String fileName = UUID.randomUUID().toString().replace("-", "") + tmpName.substring(tmpName.lastIndexOf("."), tmpName.length());
                buildFile(file.getBytes(), rooPath + tag, fileName);
                filePath = tag + "/" + fileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void buildFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && !dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "/" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        FileInputStream fis=new FileInputStream("C:\\Users\\hasee\\Desktop\\lang");
//        InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
//        BufferedReader br = new BufferedReader(isr);
//        //简写如下
//        //BufferedReader br = new BufferedReader(new InputStreamReader(
//        //        new FileInputStream("E:/phsftp/evdokey/evdokey_201103221556.txt"), "UTF-8"));
//        String line="";
//        String[] arrs=null;
//        while ((line=br.readLine())!=null) {
////            System.out.println(line);
//            arrs=line.split(",");
//            System.out.println("map.put(\""+arrs[0] + "\",\""+arrs[1]+"\"); ");
//        }
//        br.close();
//        isr.close();
//        fis.close();
    }
}
