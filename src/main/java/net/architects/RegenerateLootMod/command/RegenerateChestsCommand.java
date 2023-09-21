package net.architects.RegenerateLootMod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.architects.RegenerateLootMod.RegenerateLootMod;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.server.command.BossBarCommand;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.Random;
import java.util.function.Supplier;

public class RegenerateChestsCommand {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("chests")
                .then(CommandManager.literal("regenerate").executes(RegenerateChestsCommand::run)).requires((source) -> source.hasPermissionLevel(2)));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
            for(int i = 0; i < RegenerateLootMod.worldChestsPositions.size() ; i++) {
                Random rand = new Random(context.getSource().getWorld().getSeed() + i);
                BlockPos position = (RegenerateLootMod.worldChestsPositions.get(i));
                if(context.getSource().getWorld().getBlockEntity(position) != null) {
                    BlockEntity chest = context.getSource().getWorld().getBlockEntity(position);
                    assert chest != null;
                    if(chest instanceof ChestBlockEntity chestEntity) {
//                    context.getSource().getWorld().setBlockState(position, context.getSource().getWorld().getBlockState(position));
                        chestEntity.setLootTable(RegenerateLootMod.worldChestsLootTableIDs.get(i), rand.nextLong(100000000) + rand.nextLong(100000000));
                    }
                    if(chest instanceof BarrelBlockEntity chestEntity) {
//                    context.getSource().getWorld().setBlockState(position, context.getSource().getWorld().getBlockState(position));
                        chestEntity.setLootTable(RegenerateLootMod.worldChestsLootTableIDs.get(i), rand.nextLong(100000000) + rand.nextLong(100000000));
                    }
                }

            }

            RegenerateLootMod.worldChestsPositions.clear();
            RegenerateLootMod.worldChestsLootTableIDs.clear();
            RegenerateLootMod.chestWorlds.remove(context.getSource().getWorld().getRegistryKey());


        return 1;
    }

}
