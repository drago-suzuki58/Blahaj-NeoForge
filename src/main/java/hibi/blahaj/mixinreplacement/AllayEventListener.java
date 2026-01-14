package hibi.blahaj.mixinreplacement;

import hibi.blahaj.CuddlyItem;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.allay.Allay;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = "blahaj", value = Dist.CLIENT)
public class AllayEventListener {

	@SubscribeEvent
	public static void onEntityInteract(
		PlayerInteractEvent.EntityInteractSpecific event
	) {
		if (
			event.getTarget() instanceof Allay &&
			event.getItemStack().getItem() instanceof CuddlyItem
		) {
			event.setCancellationResult(InteractionResult.SUCCESS);
			if (event.getEntity() instanceof LocalPlayer player) {
				player.sendSystemMessage(
					Component.translatable("response.blahaj.give_away")
				);
			}
			event.setCanceled(true);
		}
	}
}
