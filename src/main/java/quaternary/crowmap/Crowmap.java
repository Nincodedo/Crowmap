package quaternary.crowmap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Crowmap.MODID)
@Mod.EventBusSubscriber
public class Crowmap {

  public static final String MODID = "crowmap";
  public static final String NAME = "Crowmap";
  public static final String VERSION = "GRADLE:VERSION";

  @SubscribeEvent
  public static void playerTickEvent(TickEvent.PlayerTickEvent e) {
    World world = e.player.getCommandSenderWorld();
    if (world.isClientSide()) {
      return;
    }

    PlayerEntity player = e.player;

    for (int i = 0; i < player.inventory.getContainerSize(); i++) {
      if (i == player.inventory.selected) {
        continue; //Already holding this map. No need to update it again
      }

      ItemStack stack = player.inventory.getItem(i);
      if (isMap(stack)) {
        //Force the map data to update.
        FilledMapItem map = (FilledMapItem) stack.getItem();
        map.update(world, player, map.getSavedData(stack, world));
      }
    }
  }

  public static boolean isMap(ItemStack stack) {
    return stack.getItem() == Items.FILLED_MAP;
  }
}