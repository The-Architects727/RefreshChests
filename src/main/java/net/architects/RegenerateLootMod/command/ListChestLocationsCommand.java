package net.architects.RegenerateLootMod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.architects.RegenerateLootMod.RegenerateLootMod;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class ListChestLocationsCommand {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("chests")
                .then(CommandManager.literal("list").then(CommandManager.literal("locations").executes(ListChestLocationsCommand::run)).requires((source) -> source.hasPermissionLevel(2))));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        for(int i = 0; i < RegenerateLootMod.worldChestsPositions.size() ; i++) {
            final int index = i;
            context.getSource().sendFeedback(() -> Text.literal((("Chest at index " + index + ": " + "X: " + RegenerateLootMod.worldChestsPositions.get(index).getX() + " Y: " + RegenerateLootMod.worldChestsPositions.get(index).getY() + " Z: " + RegenerateLootMod.worldChestsPositions.get(index).getZ()))), false);
        }

        return 1;
    }
}
