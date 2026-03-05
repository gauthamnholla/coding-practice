class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int hours = 0, currentEnergy = initialEnergy, currentExperience = initialExperience;

        for (int opponent = 0; opponent < energy.length; opponent++) {
            if (energy[opponent] >= currentEnergy) {
                int energyDifference = energy[opponent] - currentEnergy;
                hours += energyDifference + 1;
                currentEnergy += energyDifference + 1;
            }
            
            if (experience[opponent] >= currentExperience) {
                int experienceDifference = experience[opponent] - currentExperience;
                hours += experienceDifference + 1;
                currentExperience += experienceDifference + 1;
            }
            
            currentEnergy -= energy[opponent];
            currentExperience += experience[opponent];
        }

        return hours;
    }
}