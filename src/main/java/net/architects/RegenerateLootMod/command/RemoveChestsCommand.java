package net.architects.RegenerateLootMod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.architects.RegenerateLootMod.RegenerateLootMod;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.Random;
import java.util.function.Supplier;

public class RemoveChestsCommand {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("chests")
                .then(CommandManager.literal("remove").executes(RemoveChestsCommand::run)));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if(RegenerateLootMod.worldChestsPositions.size() > 0) {
            double x = context.getSource().getPosition().getX();
            int intX = (int) x - 1;
            double y = context.getSource().getPosition().getY();
            int intY = (int) y;
            double z = context.getSource().getPosition().getZ();
            int intZ = (int) z;
            BlockPos position = new BlockPos(intX, intY, intZ);
            int index = RegenerateLootMod.worldChestsPositions.indexOf(position);
            context.getSource().sendFeedback((Supplier<Text>) Text.of("Chest index " + index + " deleted"), false);
            RegenerateLootMod.worldChestsPositions.remove(index);
            RegenerateLootMod.worldChestsLootTableIDs.remove(index);
            RegenerateLootMod.chestWorlds.remove(index);
        }

        return 1;
    }

}
