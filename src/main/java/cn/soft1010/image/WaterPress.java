package cn.soft1010.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class WaterPress {


    public static void main(String[] args) throws IOException {

        pressImage("image/logo.png", "image/pic.jpg", 0, 0, file, "jpg");
    }

    /**
     * @param pressImg
     * @param targetImg
     * @param x
     * @param y
     * @param out
     * @param format
     */
    public final static void pressImage(String pressImg, String targetImg,
                                        int x, int y, OutputStream out, String format) {
        try {
            // 目标文件
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            // 水印文件
            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, (wideth - wideth_biao) / 2,
                    (height - height_biao) / 2, wideth_biao, height_biao, null);
            // 水印文件结束
            g.dispose();

            if (!ImageIO.write(image, format, out)) {
                throw new IOException("Could not write an image of format " + format);
            }

            out.close();

        } catch (Exception e) {
        }
    }

    public final static void pressImage(String pressImg, String targetImg,
                                        int x, int y, File out, String format) {
        try {
            // 目标文件
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            // 水印文件
            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, (wideth - wideth_biao) / 2,
                    (height - height_biao) / 2, wideth_biao, height_biao, null);
            // 水印文件结束
            g.dispose();

            if (!ImageIO.write(image, format, out)) {
                throw new IOException("Could not write an image of format " + format);
            }

        } catch (Exception e) {
        }
    }


    /**
     * @param inviter
     * @param pressImg
     * @param targetImg
     * @param x
     * @param y
     * @param out
     * @param format
     * @param imageType
     */
    public final static void pressImageByStream(String inviter, byte[] pressImg, byte[] targetImg,
                                                int x, int y, OutputStream out, String format, int imageType) {
        try {
            // 目标文件
            ByteArrayInputStream targetImgIn = new ByteArrayInputStream(targetImg);
            Image src = ImageIO.read(targetImgIn);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height, imageType);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            // 水印文件
            ByteArrayInputStream pressImgIn = new ByteArrayInputStream(pressImg);
            Image src_biao = ImageIO.read(pressImgIn);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, x, y, wideth_biao, height_biao, null);

            // 邀请人信息
//            g.rotate(-0.7);
            g.setColor(Color.WHITE); // 白色字体
            g.setFont(new Font("宋体", Font.BOLD, 26));// 设置画笔字体
            g.drawString(new String(inviter.getBytes(), "UTF-8"), 25, 58);

            // 水印文件结束
            g.dispose();

            if (!ImageIO.write(image, format, out)) {
                throw new IOException("Could not write an image of format " + format);
            }
            out.close();

        } catch (Exception e) {
        }
    }

    public final static void groupPhotoByStream(byte[] photoImg, byte[] background,
                                                int totalWidth, int totalHeight,
                                                int x, int y, int width, int height,
                                                OutputStream out, String format, int imageType) {
        try {

            BufferedImage image = new BufferedImage(totalWidth, totalHeight, imageType);
            Graphics g = image.createGraphics();

            // 头像文件
            ByteArrayInputStream photoImgIn = new ByteArrayInputStream(photoImg);
            Image photoSource = ImageIO.read(photoImgIn);
            //int wideth_photo_source = photoSource.getWidth(null);
            //int height_photo_source = photoSource.getHeight(null);
            g.drawImage(photoSource, x, y, width, height, null);
            // 头像文件结束

            // 背景文件
            ByteArrayInputStream backgroundIn = new ByteArrayInputStream(background);
            Image backgroundSource = ImageIO.read(backgroundIn);
            g.drawImage(backgroundSource, 0, 0, totalWidth, totalHeight, null);
            //背景文件结束

            g.dispose();

            if (!ImageIO.write(image, format, out)) {
                throw new IOException("Could not write an image of format " + format);
            }

            out.close();

        } catch (Exception e) {
        }
    }

    /**
     * @param pressText
     * @param targetImg
     * @param fontName
     * @param fontStyle
     * @param color
     * @param fontSize
     * @param x
     * @param y
     * @param out
     * @param format
     */
    public static void pressText(String pressText, String targetImg,
                                 String fontName, int fontStyle, int color, int fontSize, int x,
                                 int y, OutputStream out, String format) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            //只有图片的宽或高大于200的时候才添加水印（小图片不添加）
            if (wideth > 200 || height > 200) {
                BufferedImage image = new BufferedImage(wideth, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = image.createGraphics();
                g.drawImage(src, 0, 0, wideth, height, null);
                g.setColor(new Color(color));
                g.setFont(new Font(fontName, fontStyle, fontSize));
                g.drawString(pressText, wideth - fontSize - x, height
                        - fontSize / 2 - y);
                g.dispose();

                if (!ImageIO.write(image, format, out)) {
                    throw new IOException("Could not write an image of format " + format);
                }
                out.close();
            }
        } catch (Exception e) {

        }
    }
}
