package hibi.blahaj.mixinreplacement;

import hibi.blahaj.CuddlyItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

@EventBusSubscriber(modid = "blahaj")
public class AnvilEventListener {

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        if(left.getItem() instanceof CuddlyItem) {
            ItemStack output = left.copy();
            output.setCount(1);

            output.update(
                DataComponents.CUSTOM_DATA,
                CustomData.EMPTY,
                customData -> customData.update(tag -> tag.putString(CuddlyItem.OWNER_KEY, event.getPlayer().getName().getString()))
            );

            event.setOutput(output);
            event.setCost(1);
            event.setMaterialCost(0);
        }
    }
}
