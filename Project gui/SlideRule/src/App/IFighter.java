package App;

public interface IFighter {
    /**
     * Performs the character's stun action.
    * @param stunDuration the duration of the stun.
     * Returns whether the character is stunned.
     * @return true if the character is stunned, false otherwise.
     */
    boolean Stun(int stunDuration);

    /**
     * Performs the character's combat action.
     */
    void Combat();
    /**
     * Returns whether the character is stunned.
     * @return true if the character is stunned, false otherwise.
     */
    int IsStunned();
}
