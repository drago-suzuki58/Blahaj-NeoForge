package hibi.blahaj;

import java.util.List;
import java.util.function.Consumer;

import hibi.blahaj.mixinreplacement.HumanoidModelReplacement;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.common.util.FakePlayer;
import org.jetbrains.annotations.Nullable;

public class CuddlyItem extends Item {

    public static final String OWNER_KEY = "Owner";

    private final Component subtitle;

    public CuddlyItem(Properties properties, String subtitle) {
        super(properties);
        this.subtitle = subtitle == null? null: Component.translatable(subtitle).withStyle(ChatFormatting.GRAY);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        if (this.subtitle != null) {
            tooltip.add(this.subtitle);
        }

        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null && customData.contains(OWNER_KEY)) {
            String owner = customData.copyTag().getString(OWNER_KEY);
            if (!owner.isEmpty()) {
                if (stack.has(DataComponents.CUSTOM_NAME)) {
                    tooltip.add(Component.translatable("tooltip.blahaj.owner.rename", this.getDescription(), Component.literal(owner)).withStyle(ChatFormatting.GRAY));
                } else {
                    tooltip.add(Component.translatable("tooltip.blahaj.owner.craft", Component.literal(owner)).withStyle(ChatFormatting.GRAY));
                }
            }
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        if (player != null && !(player instanceof FakePlayer)) { // compensate for auto-crafter mods
            stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, customData -> 
                customData.update(tag -> tag.putString(OWNER_KEY, player.getName().getString())));
        }
        super.onCraftedBy(stack, level, player);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        HumanoidModelReplacement.consume(consumer);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return super.getDestroySpeed(stack, state);
    }
}
