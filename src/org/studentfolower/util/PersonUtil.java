/*
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.studentfolower.util;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

public class PersonUtil {

	public static boolean offline = false;
	public static boolean isProxyOn = false;

	public static BufferedImage getImage(String name, int size) {
		BufferedImage img = null;
		System.out.println("Retriving picture for " + name.split("@")[0] + " ("
				+ size + "*" + size + ") ...");
		if (!offline) {
			img = getOnline(name, size);
		}
		if (img == null) {
			img = getOfline(name, size);
		}

		System.out.println("Done!");
		return img;

	}

	private static BufferedImage getOfline(String name, int size) {
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

	private static BufferedImage getOnline(String name, int size) {
		try {
			URL imgUrl = new URL("https://sigil.cupcake.io/" + name + "?w="
					+ size);
			BufferedImage img = ImageIO.read(imgUrl);
			return img;
		} catch (Exception e) {
			try {
				if (!isProxyOn) {
					System.out.println("Proxy detected !");
					System.setProperty("http.proxyHost", "cache.univ-lille1.fr");
					System.setProperty("http.proxyPort", "3128");
					System.setProperty("https.proxyHost",
							"cache.univ-lille1.fr");
					System.setProperty("https.proxyPort", "3128");
					Thread.sleep(300);
					isProxyOn = true;
					return getOnline(name, size);
				}

			} catch (Exception e2) {

				e.printStackTrace(System.err);
				System.out
						.println("HEY ! IL Y A INTERNET SUR UN TELEPHONE PORTABLE ! PAS DE FICHU PROXY !!!!!");
			}

		}
		offline = true;
		System.out
				.println("HEY ! IL Y A INTERNET SUR UN TELEPHONE PORTABLE !!! ET PAS DE FICHU PROXY !!!!!");
		return null;
	}

	private PersonUtil() {
	}

}
