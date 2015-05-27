package org.studentfolower.util;

import java.awt.image.BufferedImage;
import java.util.Random;

public class PersonUtil {

	public static BufferedImage getImage(String name, int size) {
		BufferedImage img = new BufferedImage(size, size,
				BufferedImage.TYPE_INT_RGB);
		Random ran = new Random(name.hashCode());
		int color = (name.hashCode() * ran.nextInt(1000));
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if ((name.hashCode() * ran.nextInt(1000)) % 5 == 0) {
					img.setRGB(x, y, color);
				} else {
					img.setRGB(x, y, 0);
				}
				// img.setRGB(x, y, (name.hashCode() * ran.nextInt(1000)));
			}
		}
		return img;

	}

	private PersonUtil() {
	}

}
