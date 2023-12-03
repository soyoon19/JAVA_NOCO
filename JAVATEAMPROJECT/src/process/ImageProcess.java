package process;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageProcess {
    public ImageProcess(){}

    public static Image bytesToImage(byte[] source) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(source);
        return ImageIO.read(bais);
    }

    public static ImageIcon bytesToImageIcon(byte[] source) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(source);
        return  new ImageIcon(ImageIO.read(bais));
    }


    public static byte[] ImageToBytes(String path) throws IOException {
        File file = new File(path);

        //이미지 읽어오기
        BufferedImage bufferedImage = ImageIO.read(file);
        //이미지 데이터 통로
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //이지미 확장자 결정
        ImageIO.write(bufferedImage, "png", baos);
        bufferedImage.flush();


        //byte로 변환하여 출력한다.
        return baos.toByteArray();
    }
}
