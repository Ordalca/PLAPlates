package me.ordalca.plaplates;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.battles.attacks.EffectTypeAdapter;
import me.ordalca.plaplates.init.AdvancedJudgment;
import me.ordalca.plaplates.init.ItemInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ModFile.MOD_ID)
@Mod.EventBusSubscriber(modid = ModFile.MOD_ID)
public class ModFile {

    public static final String MOD_ID = "plaplates";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static ModFile instance;

    public ModFile() {
        instance = this;

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        ItemInit.setup(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        Pixelmon.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Loaded PLAPlate mod");
        EffectTypeAdapter.EFFECTS.put("Judgment", AdvancedJudgment.class);
    }

    public static ModFile getInstance() {
        return instance;
    }
    public static Logger getLogger() {
        return LOGGER;
    }
}
