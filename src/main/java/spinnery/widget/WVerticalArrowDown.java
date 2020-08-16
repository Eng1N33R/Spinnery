package spinnery.widget;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import spinnery.client.utilities.Drawings;

public class WVerticalArrowDown extends WButton {
	public static final Identifier IMAGE = new Identifier("spinnery", "textures/vertical_arrow_down.png");

	WVerticalScrollableContainer scrollable;

	public WVerticalScrollableContainer getScrollable() {
		return scrollable;
	}

	public <W extends WVerticalArrowDown> W setScrollable(WVerticalScrollableContainer scrollable) {
		this.scrollable = scrollable;
		return (W) this;
	}

	@Override
	public void draw(MatrixStack matrices, VertexConsumerProvider provider) {
		if (isHidden()) {
			return;
		}

		super.draw(matrices, provider);
	}

	@Override
	public void onMouseClicked(float mouseX, float mouseY, int mouseButton) {
		if (isWithinBounds(mouseX, mouseY)) {
			if (scrollable.hasSmoothing()) {
				scrollable.kineticScrollDelta -= 2.5;
			} else {
				scrollable.scroll(0, -25);
			}
		}

		super.onMouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void tick() {
		if (isHeld() && System.currentTimeMillis() - isHeldSince() > 500) {
			if (scrollable.hasSmoothing()) {
				scrollable.kineticScrollDelta -= 0.4;
			} else {
				scrollable.scroll(0, -0.25);
			}
		}

		super.tick();
	}
}
