package me.ordalca.plaplates.init;
import com.pixelmonmod.pixelmon.api.registries.PixelmonItems;
import me.ordalca.plaplates.ModFile;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemInit extends PixelmonItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModFile.MOD_ID);
    static {
        ITEMS.register("blank_plate", BlankPlateItem::new);
        ITEMS.register("legend_plate", LegendPlateItem::new);
    }

    public static void setup(IEventBus eventBus) {
        ModFile.LOGGER.debug("Registered new plates");
        ITEMS.register(eventBus);
    }
}