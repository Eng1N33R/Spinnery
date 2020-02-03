package spinnery.widget.api;

import net.minecraft.util.math.MathHelper;

public class WColor {
	public float A = 1.0f, R = 1.0f, G = 1.0f, B = 1.0f;

	public int ARGB = 0xffffffff;
	public int RGB = 0xffffff;

	public WColor(int r, int g, int b, int a) {
		this(r / 255f, g / 255f, b / 255f, a / 255f);
	}

	public WColor(float r, float g, float b, float a) {
		R = r;
		G = g;
		B = b;
		A = a;
		RGB = MathHelper.packRgb(r, g, b);
		ARGB = RGB + ((int) (a * 255) << 24);
	}

	public WColor(String ARGB) {
		if (ARGB.length() == 8) {
			R = Integer.decode("0x" + ARGB.substring(2, 4)) / 255f;
			G = Integer.decode("0x" + ARGB.substring(4, 6)) / 255f;
			B = Integer.decode("0x" + ARGB.substring(6, 8)) / 255f;
			this.RGB = Integer.decode(ARGB);
			this.ARGB = RGB + (0xFF << 24);
		} else if (ARGB.length() == 10) {
			int alpha = Integer.decode("0x" + ARGB.substring(2, 4));
			A = alpha / 255f;
			R = Integer.decode("0x" + ARGB.substring(4, 6)) / 255f;
			G = Integer.decode("0x" + ARGB.substring(6, 8)) / 255f;
			B = Integer.decode("0x" + ARGB.substring(8, 10)) / 255f;
			this.RGB = Integer.decode("0x" + ARGB.substring(4));
			this.ARGB = RGB + (alpha << 24);
		}
	}

	public static WColor of(String ARGB) {
		return new WColor(ARGB);
	}

	public static WColor of(Number color) {
		int intColor = color.intValue();
		int a = (intColor >> 24) & 0xFF;
		int r = (intColor >> 16) & 0xFF;
		int g = (intColor >> 8) & 0xFF;
		int b = (intColor & 0xFF);
		return new WColor(r, g, b, a);
	}
}
