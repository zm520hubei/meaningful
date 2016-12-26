package com.z.mif.util;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImgUtil {
	
	/**
	 * 重新设置图片大小
	 * @param source 原图片
	 * @param targetW 目标宽
	 * @param targetH 目标高
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage resize(BufferedImage source, int targetW,
			int targetH) throws IOException {
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		// if (sx > sy) {
		// sx = sy;
		// targetW = (int) (sx * source.getWidth());
		// } else {
		// sy = sx;
		// targetH = (int) (sy * source.getHeight());
		// }
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
					targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			// 固定宽高，宽高一定要比原图片大
			target = new BufferedImage(targetW, targetH, type);
			// target = new BufferedImage(800, 600, type);
		}

		Graphics2D g = target.createGraphics();

		// 写入背景
		// g.drawImage(ImageIO.read(new
		// File("C:\\Users\\meng\\Desktop\\ps demo\\blank.png")), 0, 0, null);

		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}
	
	/**
	 * 裁剪并保存图片
	 * @param srcPath 原图片路径
	 * @param toPath 裁剪后图片保存路径
	 * @param x 
	 * @param y
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
    public static void cutImg(String srcPath, String toPath, int x, int y,
            int width, int height) throws IOException{
    	FileInputStream fis = null;

        ImageInputStream iis = null;
        try {
            // 读取图片文件
            fis = new FileInputStream(srcPath);

            Iterator it = ImageIO.getImageReadersByFormatName("png");

            ImageReader reader = (ImageReader) it.next();
            // 获取图片流

            iis = ImageIO.createImageInputStream(fis);

            reader.setInput(iis, true);

            ImageReadParam param = reader.getDefaultReadParam();
            // 定义一个矩形

            Rectangle rect = new Rectangle(x, y, width, height);

            // 提供一个 BufferedImage，将其用作解码像素数据的目标。

            param.setSourceRegion(rect);

            BufferedImage bi = reader.read(0, param);
            // 保存新图片
            ImageIO.write(bi, "png", new File(toPath));

	   } finally {
	        if (fis != null)
	             fis.close();
	
	        if (iis != null)
	             iis.close();
	   }
    }
}
