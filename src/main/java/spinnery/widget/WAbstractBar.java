package spinnery.widget;

import net.minecraft.util.Identifier;
import org.apache.commons.lang3.mutable.Mutable;


  general progress-bar-like widget, like an {@link WVerticalBar}
 /
public abstract class WAbstractBar extends WAbstractWidget {
	protected Mutable<Number> limit;
	protected Mutable<Number> progress;


	  such that one does not need to constantly update it,


	public Mutable<Number> getLimit() {
		return limit;
	}


	  such that one does not need to constantly update it,


	public <W extends WAbstractBar> W setLimit(Mutable<Number> limit) {
		this.limit = limit;
		return (W) this;
	}


	  such that one does not need to constantly update it,


	public Mutable<Number> getProgress() {
		return progress;
	}


	  such that one does not need to constantly update it,


	public <W extends WAbstractBar> W setProgress(Mutable<Number> progress) {
		this.progress = progress;
		return (W) this;
	}




	public Identifier getBackgroundTexture() {
		return getStyle().asIdentifier("background");
	}




	public <W extends WAbstractBar> W setBackgroundTexture(Identifier backgroundTexture) {
		overrideStyle("background", backgroundTexture);
		return (W) this;
	}




	public Identifier getForegroundTexture() {
		return getStyle().asIdentifier("foreground");
	}




	public <W extends WAbstractBar> W setForegroundTexture(Identifier foregroundTexture) {
		overrideStyle("foreground", foregroundTexture);
		return (W) this;
	}
}
