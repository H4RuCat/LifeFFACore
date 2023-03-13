//package lifeffa.org.example.lifeffacore.command;
//
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.command.TabCompleter;
//import org.bukkit.entity.Player;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.List;
//
//public class DailyMissionCommand implements CommandExecutor, TabCompleter {
//
//    @Override
//    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
//
//
//
//        return null;
//    }
//
//    @Override
//    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//
//        if ( args[0].equals("check") ) {
//            Player player = (Player) sender;
//
//            player.sendMessage("作成途中！ｗ");
//
//        }
//
//        return true;
//    }
//}
