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

public class ListChestCountCommand {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("chests")
                .then(CommandManager.literal("list").executes(ListChestCountCommand::run)).requires((source) -> source.hasPermissionLevel(2)));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {


        context.getSource().sendFeedback(() -> Text.literal(("Number of Looted Chests: " + RegenerateLootMod.worldChestsPositions.size())), false);

        return 1;
    }
}
