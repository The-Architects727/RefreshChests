package net.architects.RegenerateLootMod.event;

import net.architects.RegenerateLootMod.RegenerateLootMod;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChestSaveEvent implements UseBlockCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        RegenerateLootMod.key = world.getRegistryKey();
        BlockPos position = hitResult.getBlockPos();
        if(world.getBlockEntity(position) != null) {
            BlockEntity chest = world.getBlockEntity(position);
            assert chest != null;
            if(chest instanceof ChestBlockEntity chestEntity) {
                Identifier lootTableID = (chestEntity.lootTableId);
                if((lootTableID) != null && !RegenerateLootMod.worldChestsPositions.contains(position)) {
                    String lootTableIDString = String.valueOf(lootTableID);
                    String lootTableSead = String.valueOf(chestEntity.lootTableSeed);
//                    RegenerateLootMod.LOGGER.info(lootTableIDString);
//                    RegenerateLootMod.LOGGER.info(lootTableSead);
                    RegenerateLootMod.worldChestsLootTableIDs.add(lootTableID);
                    RegenerateLootMod.worldChestsPositions.add(position);
                    RegenerateLootMod.chestWorlds.add(world.getRegistryKey());

//                    for(int i = 0; i < RegenerateLootMod.worldChestsPositions.size(); i++) {
//                        RegenerateLootMod.LOGGER.info("Array entry: " + i);
//                        RegenerateLootMod.LOGGER.info(RegenerateLootMod.worldChestsPositions.get(i).toString());
//                        if(RegenerateLootMod.worldChestsLootTableIDs.get(i) != null) {
//                            RegenerateLootMod.LOGGER.info(RegenerateLootMod.worldChestsLootTableIDs.get(i).toString());
//                        } else {
//                            RegenerateLootMod.LOGGER.info("null");
//                        }
//                    }
                }

            }

            if(chest instanceof BarrelBlockEntity chestEntity) {
                Identifier lootTableID = (chestEntity.lootTableId);
                if((lootTableID) != null && !RegenerateLootMod.worldChestsPositions.contains(position)) {
                    String lootTableIDString = String.valueOf(lootTableID);
                    String lootTableSead = String.valueOf(chestEntity.lootTableSeed);
//                    RegenerateLootMod.LOGGER.info(lootTableIDString);
//                    RegenerateLootMod.LOGGER.info(lootTableSead);
                    RegenerateLootMod.worldChestsLootTableIDs.add(lootTableID);
                    RegenerateLootMod.worldChestsPositions.add(position);
                    RegenerateLootMod.chestWorlds.add(world.getRegistryKey());

//                    for(int i = 0; i <= RegenerateLootMod.worldChestsPositions.size(); i++) {
//                        RegenerateLootMod.LOGGER.info("Array entry: " + i);
//                        RegenerateLootMod.LOGGER.info(RegenerateLootMod.worldChestsPositions.get(i).toString());
//                        if(RegenerateLootMod.worldChestsLootTableIDs.get(i) != null) {
//                            RegenerateLootMod.LOGGER.info(RegenerateLootMod.worldChestsLootTableIDs.get(i).toString());
//                        } else {
//                            RegenerateLootMod.LOGGER.info("null");
//                        }
//                    }
                }

            }
        }


        return ActionResult.PASS;
    }
}
