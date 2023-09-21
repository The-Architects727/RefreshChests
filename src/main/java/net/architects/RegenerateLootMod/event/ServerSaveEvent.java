package net.architects.RegenerateLootMod.event;

import net.architects.RegenerateLootMod.RegenerateLootMod;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class ServerSaveEvent implements ServerWorldEvents.Unload {

    @Override
    public void onWorldUnload(MinecraftServer server, ServerWorld world) {

        for(int i = 0; i < RegenerateLootMod.worldChestsPositions.size() ; i++) {
            if(RegenerateLootMod.chestWorlds.get(i) == world.getRegistryKey()) {
                Random rand = new Random(world.getSeed() + i);
                BlockPos position = (RegenerateLootMod.worldChestsPositions.get(i));
                if (world.getBlockEntity(position) != null) {
                    BlockEntity chest = world.getBlockEntity(position);
                    assert chest != null;
                    if (chest instanceof ChestBlockEntity chestEntity) {
                        world.setBlockState(position, world.getBlockState(position));
                        chestEntity.setLootTable(RegenerateLootMod.worldChestsLootTableIDs.get(i), rand.nextLong(100000000) + rand.nextLong(100000000));
                    }
                    if (chest instanceof BarrelBlockEntity chestEntity) {
                        world.setBlockState(position, world.getBlockState(position));
                        chestEntity.setLootTable(RegenerateLootMod.worldChestsLootTableIDs.get(i), rand.nextLong(100000000) + rand.nextLong(100000000));
                    }
                }
            }
        }

        RegenerateLootMod.worldChestsPositions.clear();
        RegenerateLootMod.worldChestsLootTableIDs.clear();
        RegenerateLootMod.chestWorlds.remove(world.getRegistryKey());
    }
}
