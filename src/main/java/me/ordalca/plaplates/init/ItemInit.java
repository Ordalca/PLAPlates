package me.ordalca.plaplates.init;
import com.pixelmonmod.pixelmon.api.pokemon.Element;
import com.pixelmonmod.pixelmon.api.registries.PixelmonItems;
import com.pixelmonmod.pixelmon.init.registry.ItemRegistration;
import com.pixelmonmod.pixelmon.items.heldItems.PlateItem;
import me.ordalca.plaplates.ModFile;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemInit extends PixelmonItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModFile.MOD_ID);

    public static final PlateItem blank_plate = null;
    public static final LegendPlateItem legend_plate = null;
    static {
        ITEMS.register("blank_plate", () -> {
            return new PlateItem(Element.NORMAL);
        });
        ITEMS.register("legend_plate", ()-> {
            return new LegendPlateItem();
        });
    }


    public static void setup(IEventBus eventBus) {
        ModFile.LOGGER.debug("Registered new plates");
        ITEMS.register(eventBus);
    }
}