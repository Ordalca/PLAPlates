package me.ordalca.plaplates.init;
import com.pixelmonmod.pixelmon.api.registries.PixelmonItems;
import com.pixelmonmod.pixelmon.api.util.helpers.ResourceLocationHelper;
import me.ordalca.plaplates.ModFile;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemInit extends PixelmonItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModFile.MOD_ID);

    public static final BlankPlateItem blank_plate = null;
    public static final LegendPlateItem legend_plate = null;
    static {
        ITEMS.register("blank_plate", BlankPlateItem::new);
        ITEMS.register("legend_plate", LegendPlateItem::new);
    }

    public static ItemStack getItem(String id) {
        return new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocationHelper.of(id)), 1);
    }
    public static void setup(IEventBus eventBus) {
        ModFile.LOGGER.debug("Registered new plates");
        ITEMS.register(eventBus);
    }
}