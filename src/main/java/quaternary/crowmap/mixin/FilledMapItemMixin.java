package quaternary.crowmap.mixin;

import net.minecraft.item.FilledMapItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FilledMapItem.class)
public class FilledMapItemMixin {
	@ModifyVariable(
		at = @At("HEAD"),
		method = "inventoryTick",
		argsOnly = true
	)
	public boolean onEntityTickPre(boolean isHeld) {
		//this bool controls whether the map is held in the player's hand or not
		//so... to make the map always update, time to always return true :P
		return true;
	}
}
