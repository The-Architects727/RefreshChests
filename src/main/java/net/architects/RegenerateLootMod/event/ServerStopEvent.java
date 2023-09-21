package net.architects.RegenerateLootMod.event;

import net.architects.RegenerateLootMod.RegenerateLootMod;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class ServerStopEvent implements ServerLifecycleEvents.ServerStopping {

    @Override
    public void onServerStopping(MinecraftServer server) {
        for(int i = 0; i < RegenerateLootMod.worldChestsPositions.size() ; i++) {
            Random rand = new Random(server.getWorld(RegenerateLootMod.chestWorlds.get(i)).getSeed() + i);
            BlockPos position = (RegenerateLootMod.worldChestsPositions.get(i));
            if(server.getWorld(RegenerateLootMod.chestWorlds.get(i)).getBlockEntity(position) != null) {
                BlockEntity chest = server.getWorld(RegenerateLootMod.chestWorlds.get(i)).getBlockEntity(position);
                assert chest != null;
                if(chest instanceof ChestBlockEntity chestEntity) {
                    chestEntity.setLootTable(RegenerateLootMod.worldChestsLootTableIDs.get(i), rand.nextLong(100000000) + rand.nextLong(100000000));
                }
                if(chest instanceof BarrelBlockEntity chestEntity) {
                    chestEntity.setLootTable(RegenerateLootMod.worldChestsLootTableIDs.get(i), rand.nextLong(100000000) + rand.nextLong(100000000));
                }
            }
        }

        RegenerateLootMod.worldChestsPositions.clear();
        RegenerateLootMod.worldChestsLootTableIDs.clear();
        RegenerateLootMod.chestWorlds.clear();
    }
}
