package net.architects.RegenerateLootMod;

import net.architects.RegenerateLootMod.command.*;
import net.architects.RegenerateLootMod.event.ChestSaveEvent;
import net.architects.RegenerateLootMod.event.ServerSaveEvent;
import net.architects.RegenerateLootMod.event.ServerStopEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class RegenerateLootMod implements ModInitializer {
	public static final String MOD_ID = "regeneratelootmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static RegistryKey<World> key;
	public static ArrayList<RegistryKey<World>> chestWorlds = new ArrayList<>();

	public static ArrayList<Identifier> worldChestsLootTableIDs = new ArrayList<>();

	public static ArrayList<BlockPos> worldChestsPositions = new ArrayList<>();

	@Override
	public void onInitialize() {

		CommandRegistrationCallback.EVENT.register(ListChestCountCommand::register);
		CommandRegistrationCallback.EVENT.register(ListChestLocationsCommand::register);
		CommandRegistrationCallback.EVENT.register(RegenerateChestsCommand::register);
		CommandRegistrationCallback.EVENT.register(RegenerateChestsStatusCommand::register);
		CommandRegistrationCallback.EVENT.register(RemoveChestsCommand::register);


		UseBlockCallback.EVENT.register(new ChestSaveEvent());
		ServerWorldEvents.UNLOAD.register(new ServerSaveEvent());
		ServerLifecycleEvents.SERVER_STOPPING.register(new ServerStopEvent());



	}
}