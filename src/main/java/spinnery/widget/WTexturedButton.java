package spinnery.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import spinnery.client.render.BaseRenderer;

@Environment(EnvType.CLIENT)
public class WTexturedButton extends WAbstractWidget {
	protected Identifier inactive;
	protected Identifier active;
	protected Identifier disabled;

	protected boolean isDisabled = false;

	public Identifier getInactive() {
		return inactive;
	}

	public <W extends WTexturedButton> W setInactive(Identifier inactive) {
		this.inactive = inactive;
		return (W) this;
	}

	@Override
	public void draw(MatrixStack matrices, VertexConsumerProvider.Immediate provider) {
		if (isHidden()) {
			return;
		}

		RenderSystem.translatef(0, 0, getZ() * 400f);
  		matrices.translate(0, 0, getZ() * 400f);

		BaseRenderer.drawTexturedQuad(matrices, provider, getX(), getY(), getZ(), getWidth(), getHeight(), getDrawTexture());

  		matrices.translate(0, 0, getZ() * -400f);
		RenderSystem.translatef(0, 0, getZ() * -400f);
	}

	protected Identifier getDrawTexture() {
		if (isDisabled() && getDisabled() != null) {
			return disabled;
		}
		if (isActive() && getActive() != null) {
			return active;
		}
		return inactive;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public Identifier getDisabled() {
		return disabled;
	}

	public <W extends WTexturedButton> W setDisabled(Identifier disabled) {
		this.disabled = disabled;
		return (W) this;
	}

	public <W extends WTexturedButton> W setDisabled(boolean disabled) {
		this.isDisabled = disabled;
		return (W) this;
	}

	public boolean isActive() {
		return isFocused();
	}

	public Identifier getActive() {
		return active;
	}

	public <W extends WTexturedButton> W setActive(Identifier active) {
		this.active = active;
		return (W) this;
	}

	@Override
	public void onMouseReleased(float mouseX, float mouseY, int mouseButton) {
		if (isDisabled) return;
		super.onMouseReleased(mouseX, mouseY, mouseButton);
	}

	@Override
	public void onMouseClicked(float mouseX, float mouseY, int mouseButton) {
		if (isDisabled) return;
		super.onMouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public boolean isFocusedMouseListener() {
		return true;
	}
}
