package hibi.blahaj.mixinreplacement;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class HumanoidModelReplacement {
    public static void consume(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new CuddlyItemExtension());
    }

    private static class CuddlyItemExtension implements IClientItemExtensions {
        @Override
        public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
            return HumanoidModel.ArmPose.valueOf("BLAHAJ_BLAHAJ_CUDDLE");
        }
    }
}
