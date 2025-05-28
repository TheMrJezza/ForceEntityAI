package dev.themrjezza.force_entity_ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Predicate;

public final class ForcedAiGoalSelector extends GoalSelector {
    private final GoalSelector wrapped;

    public ForcedAiGoalSelector(GoalSelector wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void setControlFlag(Goal.@NotNull Flag flag, boolean enabled) {
        wrapped.setControlFlag(flag, true);
    }

    @Override
    public void addGoal(int priority, @NotNull Goal goal) {
        this.wrapped.addGoal(priority, goal);
    }

    @Override
    public void removeAllGoals(@NotNull Predicate<Goal> filter) {
        this.wrapped.removeAllGoals(filter);
    }

    @Override
    public boolean inactiveTick() {
        return this.wrapped.inactiveTick();
    }

    @Override
    public boolean hasTasks() {
        return this.wrapped.hasTasks();
    }

    @Override
    public void removeGoal(@NotNull Goal goal) {
        this.wrapped.removeGoal(goal);
    }

    @Override
    public void tick() {
        this.wrapped.tick();
    }

    @Override
    public void tickRunningGoals(boolean tickAllRunning) {
        this.wrapped.tickRunningGoals(tickAllRunning);
    }

    @Override
    public @NotNull Set<WrappedGoal> getAvailableGoals() {
        return this.wrapped.getAvailableGoals();
    }

    @Override
    public void disableControlFlag(Goal.@NotNull Flag flag) {
        this.wrapped.disableControlFlag(flag);
    }

    @Override
    public void enableControlFlag(Goal.@NotNull Flag flag) {
        this.wrapped.enableControlFlag(flag);
    }
}
