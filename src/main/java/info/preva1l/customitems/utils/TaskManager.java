package info.preva1l.customitems.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Easy creation of Bukkit Tasks
 *
 * @author Preva1l
 */
@UtilityClass
public class TaskManager {
    /**
     * Synchronous Tasks
     */
    @UtilityClass
    public class Sync {
        /**
         * Run a synchronous task once. Helpful when needing to run some sync code in an async loop
         *
         * @param plugin   The current plugin typeof JavaPlugin. (Not Commons)
         * @param runnable The runnable, lambda supported yeh
         */
        public void run(JavaPlugin plugin, Runnable runnable) {
            plugin.getServer().getScheduler().runTask(plugin, runnable);
        }

        /**
         * Run a synchronous task forever with a delay between runs.
         *
         * @param plugin   The current plugin typeof JavaPlugin. (Not Commons)
         * @param runnable The runnable, lambda supported yeh
         * @param interval Time between each run
         */
        public void runTask(JavaPlugin plugin, Runnable runnable, long interval) {
            plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, 0L, interval);
        }

        /**
         * Run a synchronous task once with a delay. Helpful when needing to run some sync code in an async loop
         *
         * @param plugin   The current plugin typeof JavaPlugin. (Not Commons)
         * @param runnable The runnable, lambda supported yeh
         * @param delay    Time before running.
         */
        public void runLater(JavaPlugin plugin, Runnable runnable, long delay) {
            plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
        }
    }

    /**
     * Asynchronous tasks
     */
    @UtilityClass
    public class Async {
        /**
         * Run an asynchronous task once. Helpful when needing to run some sync code in an async loop
         *
         * @param plugin   The current plugin typeof JavaPlugin. (Not Commons)
         * @param runnable The runnable, lambda supported yeh
         */
        public void run(JavaPlugin plugin, Runnable runnable) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
        }

        /**
         * Run an asynchronous task forever with a delay between runs.
         *
         * @param plugin   The current plugin typeof JavaPlugin. (Not Commons)
         * @param runnable The runnable, lambda supported yeh
         * @param interval Time between each run
         */
        public void runTask(JavaPlugin plugin, Runnable runnable, long interval) {
            plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, 0L, interval);
        }

        /**
         * Run an asynchronous task once with a delay. Helpful when needing to run some sync code in an async loop
         *
         * @param plugin   The current plugin typeof JavaPlugin. (Not Commons)
         * @param runnable The runnable, lambda supported yeh
         * @param delay    Time before running.
         */
        public void runLater(JavaPlugin plugin, Runnable runnable, long delay) {
            plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
        }
    }
}