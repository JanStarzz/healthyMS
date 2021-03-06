package com.yzy.util;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

/**
 * @author LuBaby
 * @date 2021/5/14 10:48
 */
public class QrCodeUtil {
    //生成二维码时的编码表
    private static final String encode = "utf-8";
    //二维码的图片格式
    private static final String formatImg = "PNG";
    //二维码的宽与高
    private static final int QUICK_MARK_SIZE = 300;

    /**
     * 生成二维码
     * @param content   二维码内容
     * @param rgbColor  二维码颜色
     * @throws Exception    主要是IO异常、溢出异常
     */
    public static BufferedImage buildQuickMarkImage(String content, Integer rgbColor) throws Exception {
        //定义集合，存放与二维码有关联的部分数据参数
        HashMap<EncodeHintType, Object> hintsMap = new HashMap<>();
        hintsMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hintsMap.put(EncodeHintType.CHARACTER_SET, encode);
        hintsMap.put(EncodeHintType.MARGIN, 1);
        //使用Google的二维码工具类来生成二维码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QUICK_MARK_SIZE, QUICK_MARK_SIZE, hintsMap);
        //把二维码画到图片缓冲区中，理解成画板
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //定义二维码颜色
        if (rgbColor == null) {
            rgbColor = 0x00000;
        }
        //开始画二维码矩阵
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? rgbColor : 0xFFFFFFFF);
            }
        }
        return bufferedImage;
    }

    /**
     * 生成灰色二维码
     */
    public static BufferedImage buildGrayQuickMarkImage(String context) throws Exception {
        return buildQuickMarkImage(context,Color.GRAY);
    }
    /**
     * 生成绿色二维码
     */
    public static BufferedImage buildBlueQuickMarkImage(String context) throws Exception {
        return buildQuickMarkImage(context,Color.BLUE);
    }
    /**
     * 生成黄色二维码
     */
    public static BufferedImage buildYellowQuickMarkImage(String context) throws Exception {
        return buildQuickMarkImage(context,Color.YELLOW);
    }
    /**
     * 生成红色二维码
     */
    public static BufferedImage buildRedQuickMarkImage(String context) throws Exception {
        return buildQuickMarkImage(context,Color.RED);
    }

    /**
     * 图片写到本地
     * @param path  保存路径
     * @param image 图片内容
     * @throws IOException  Io异常
     */
    public static void writeToLocal(String path, BufferedImage image) throws IOException {
        File file = new File(path);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        ImageIO.write(image, formatImg, new File(path));
    }

    public static InputStream getImageStream(BufferedImage bimage){
        InputStream is = null;
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut;
        try {
            imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(bimage, "png",imOut);
            is= new ByteArrayInputStream(bs.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * RGB颜色值
     */
    public static class Color{
        // 0xFF999 灰码
        // 0xFF8CC152 绿码
        // 0xFFCC3333 红码
        // 0xFFFFFF00 黄码
        public static final Integer GRAY=0xFFA9A9A9;
        public static final Integer BLUE=0xFF8CC152;
        public static final Integer YELLOW=0xFFFFFF00;
        public static final Integer RED=0xFFCC3333;
    }

}
