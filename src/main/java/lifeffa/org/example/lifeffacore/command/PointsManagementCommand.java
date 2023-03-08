package lifeffa.org.example.lifeffacore.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PointsManagementCommand implements CommandExecutor {

    @Override
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {

        if ( args[0].equals("set") ) {

            /*
            args[1]が数字:
             true - args[1]にpointsをsetする
             false - return true;
            */

        }
        if ( args[0].equals("add") ) {

            /*
            args[1]が数字:
             true - 現在のpoints + args[1]
             false- return true;
            */

        }
        if ( args[0].equals("remove") ) {

            /*
            args[1]が数字:
             true - 現在のpoints - args[1]
             false - return true;
            */

        }

        return true;
    }
}
