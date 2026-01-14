package hibi.blahaj;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(Common.MOD_ID)
public class Common {
    public static final String MOD_ID = "blahaj";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MOD_ID);
    public static final DeferredHolder<Item, Item> BLUE_SHARK_ITEM   = ITEMS.register("blue_shark",
        () -> new CuddlyItem(new Item.Properties().stacksTo(1), "item.blahaj.blue_shark.tooltip"));
    public static final DeferredHolder<Item, Item> BREAD_ITEM        = ITEMS.register("bread",
        () -> new CuddlyItem(new Item.Properties().stacksTo(1), null));
    public static final DeferredHolder<Item, Item> GRAY_SHARK_ITEM   = ITEMS.register("gray_shark",
        () -> new CuddlyItem(new Item.Properties().stacksTo(1), "item.blahaj.gray_shark.tooltip"));
    public static final DeferredHolder<Item, Item> BLUE_WHALE_ITEM   = ITEMS.register("blue_whale",
        () -> new CuddlyItem(new Item.Properties().stacksTo(1), "item.blahaj.blue_whale.tooltip"));
    public static final DeferredHolder<Item, Item> PINK_SHARK_ITEM   = ITEMS.register("pink_shark",
        () -> new CuddlyItem(new Item.Properties().stacksTo(1), "item.blahaj.pink_shark.tooltip"));
    public static final DeferredHolder<Item, Item> GREEN_DRAGON_ITEM = ITEMS.register("green_dragon",
        () -> new CuddlyItem(new Item.Properties().stacksTo(1), "item.blahaj.green_dragon.tooltip"));

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLAHAJ_TAB = TABS.register("blahaj_tab", () -> CreativeModeTab.builder()
        .title(Component.translatable("itemGroup.blahaj"))
        .icon(() -> new ItemStack(BLUE_SHARK_ITEM.get()))
        .displayItems((parameters, output) -> {
            output.accept(BLUE_SHARK_ITEM.get());
            output.accept(BREAD_ITEM.get());
            output.accept(GRAY_SHARK_ITEM.get());
            output.accept(BLUE_WHALE_ITEM.get());
            output.accept(PINK_SHARK_ITEM.get());
            output.accept(GREEN_DRAGON_ITEM.get());
        })
        .build()
    );

    public Common(IEventBus modEventBus, ModContainer modContainer) {
        ITEMS.register(modEventBus);
        TABS.register(modEventBus);
    }
}
