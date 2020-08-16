package spinnery.client.utilities;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import spinnery.widget.api.Color;


  {@link spinnery.widget.WSprite} and {@link spinnery.widget.WStatusBar.BarConfig}
 /
public class Sprites {

	  rendering things like health, hunger, oxygen.

	public static final Sprites MINECRAFT_ICONS = new Sprites(new Identifier("minecraft:textures/gui/icons.png"), 256, 256).crop(16, 0, 162, 54).setDimensions(9, 9);

	public static class Sprite {
		public final Identifier atlas;
		public final float x;
		public final float y;
		public final float w;
		public final float h;

		public Sprite(Identifier atlas, float x, float y, float w, float h) {
			this.atlas = atlas;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}

		public void draw(MatrixStack matrices, VertexConsumerProvider provider, float x, float y, float z, float sX, float sY, boolean mirror) {
			float u1 = this.x;
			float v1 = this.y;
			float u2 = u1 + this.w;
			float v2 = v1 + this.h;

			if (mirror) {
				// Swap UVs.
				float temp = u2;
				u2 = u1;
				u1 = temp;
			}

			Drawings.drawTexturedQuad(matrices, provider, x, y, z, sX, sY, u1, v1, u2, v2, 0x00f000f0, Color.DEFAULT, atlas);
		}
	}

	private final Identifier atlasId;
	private int startX = 0;
	private int startY = 0;
	private final int sizeX;
	private final int sizeY;
	private int endX;
	private int endY;
	private int spriteWidth = 16;
	private int spriteHeight = 16;

	public Sprites(Identifier atlasId, int w, int h) {
		this.atlasId = atlasId;
		this.sizeX = w;
		this.sizeY = h;
		this.endX = w;
		this.endY = h;
	}

	public Sprites crop(int x, int y) {
		startX = x;
		startY = y;
		return this;
	}

	public Sprites crop(int x, int y, int w, int h) {
		startX = x;
		startY = y;
		endX = x + w;
		endY = y + h;
		return this;
	}

	public Sprites setDimensions(int w, int h) {
		spriteWidth = w;
		spriteHeight = h;
		return this;
	}

	public Sprite getSprite(int index) {
		int spritesPerRow = (endX - startX) / spriteWidth;
		int row = index / spritesPerRow;
		int column = index % spritesPerRow;

		int x = column * spriteWidth + startX;
		int y = row * spriteHeight + startY;

		return new Sprite(atlasId, x / (float) sizeX, y / (float) sizeY, spriteWidth / (float) sizeX, spriteHeight / (float) sizeY);
	}
}
