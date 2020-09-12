package net.teamfruit.sneakcount;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public final class SneakCount extends JavaPlugin implements Listener {

    private Objective score;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Scoreboard sb = getServer().getScoreboardManager().getMainScoreboard();
        Objective sc = sb.getObjective("sneakcount");
        if (sc == null)
            sc = sb.registerNewObjective("sneakcount", "dummy", "スニーク回数");
        score = sc;
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        if (event.isSneaking()) {
            Score sc = score.getScore(event.getPlayer().getName());
            sc.setScore(sc.getScore() + 1);
        }
    }

}
