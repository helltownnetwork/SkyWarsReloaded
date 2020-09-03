package com.walrusone.skywarsreloaded.commands.maps;

import com.walrusone.skywarsreloaded.commands.BaseCmd;
import com.walrusone.skywarsreloaded.enums.GameType;
import com.walrusone.skywarsreloaded.game.GameMap;
import com.walrusone.skywarsreloaded.managers.MatchManager;
import com.walrusone.skywarsreloaded.utilities.Messaging;
import org.bukkit.entity.Player;

public class DebugCmd extends BaseCmd {
    public DebugCmd(String t) {
        type = t;
        forcePlayer = false;
        cmdName = "debug";
        argLength = 2;
    }


    public boolean run() {
        String worldName = args[1];
        GameMap gMap = GameMap.getMap(worldName);
        if (gMap == null) {
            sender.sendMessage(new Messaging.MessageFormatter().format("error.map-does-not-exist"));
            return true;
        }

        sender.sendMessage("-- Debug of arena: " + worldName);
        sender.sendMessage("Registered: " + gMap.isRegistered());
        sender.sendMessage("Status: " + gMap.getMatchState().name());
        sender.sendMessage("Players: " + gMap.getPlayerCount() + "/" + gMap.getMaxPlayers());
        sender.sendMessage("Playable arenas amount: " + GameMap.getPlayableArenas(GameType.ALL).size());
        sender.sendMessage(" ");
        if (sender instanceof Player) {
            sender.sendMessage("-- Debug of player: " + sender.getName());
            GameMap map = MatchManager.get().getPlayerMap(player);
            GameMap dead = MatchManager.get().getPlayerMap(player);
            GameMap spec = MatchManager.get().getPlayerMap(player);

            sender.sendMessage("Are u in-game as player? " + (map == null ? "no" : "yes") + " " + (map != null ? map.getName() : ""));
            sender.sendMessage("Are u in-game as dead? " + (dead == null ? "no" : "yes") + " " + (dead != null ? dead.getName() : ""));
            sender.sendMessage("Are u in-game as spec? " + (spec == null ? "no" : "yes") + " " + (spec != null ? spec.getName() : ""));
        }
        return true;
    }
}
