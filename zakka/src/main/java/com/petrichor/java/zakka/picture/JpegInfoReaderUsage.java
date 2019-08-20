package com.petrichor.java.zakka.picture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author yang3wei
 */
public class JpegInfoReaderUsage {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File picture = new File("J:\\BaiduNetdiskDownload\\WD-jiemi-shenshicy.fun\\WANIMAL\\[WANIMAL王动] 2016年4月VIP套图 官方出品 [46P 865M]\\02.jpg");
        long start = System.currentTimeMillis();


        FileInputStream picInputStream = new FileInputStream(picture);
        BufferedImage sourceImg = ImageIO.read(picInputStream);

        System.out.println(sourceImg.getWidth() + sourceImg.getHeight());


        InputStream in = null;
        try {
            in = new FileInputStream("J:\\BaiduNetdiskDownload\\WD-jiemi-shenshicy.fun\\WANIMAL\\[WANIMAL王动] 2016年4月VIP套图 官方出品 [46P 865M]\\02.jpg");
            ImageInfo info = JpegInfoReader.getImageInfo(in);
            System.out.println(info);
        } finally {
            if (in != null) {
                in.close();
            }
        }
        long l = System.currentTimeMillis() - start;
        System.out.println("total: " + l);
    }
}
