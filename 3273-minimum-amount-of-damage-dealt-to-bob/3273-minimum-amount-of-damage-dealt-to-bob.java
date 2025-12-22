class Solution {

    public long minDamage(int power, int[] damage, int[] health) {
        final int n = damage.length;
        
        // List to store enemies with their index, damage, and time to kill
        List<Enemy> enemies = new ArrayList<>();
        
        // Step 1: Calculate time to kill each enemy and add to list
        for (int i = 0; i < n; i++) {
            int time = (health[i] + power - 1) / power; // Equivalent to ceil(health[i] / power)
            enemies.add(new Enemy(damage[i], time));
        }

        // Step 2: Sort enemies based on damage-to-time ratio, and then by time
        enemies.sort((e1, e2) -> {
            double ratio1 = (double) e1.damage / e1.time;
            double ratio2 = (double) e2.damage / e2.time;
            if (Double.compare(ratio1, ratio2) != 0) {
                return Double.compare(ratio2, ratio1); // Sort by ratio in descending order
            } else {
                return Integer.compare(e1.time, e2.time); // Sort by time in ascending order
            }
        });

        // Step 3: Compute the minimum total damage
        long totalDamage = 0;
        long accumulatedTime = 0;
        
        for (Enemy enemy : enemies) {
            accumulatedTime += enemy.time; // Accumulate time to process the current enemy
            totalDamage += accumulatedTime * enemy.damage; // Add the damage for this period
        }

        return totalDamage;
    }

    // Helper class to represent an enemy
    private static class Enemy {
        int damage; // Damage per second
        int time;   // Time required to kill

        Enemy(int damage, int time) {
            this.damage = damage;
            this.time = time;
        }
    }
}