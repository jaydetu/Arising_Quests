package edu.ucdenver.questingcrew.arisingquests;

import androidx.room.*;
/*
 * SubstepDao is an interface that declares methods to insert, update, delete, and query (select/get) data from TaskDatabase.
 * The methods are then defined/generated automatically.
 * @author Jayde Tu
 * @version 11302023
 */
@Dao
public interface SubstepDao {
    @Query("SELECT * FROM Substep WHERE id = :id")
    public Substep getSubstep(long id);

    @Query("SELECT * FROM Substep WHERE taskId = :taskId ORDER BY id")
    public Substep[] getTaskSubsteps(long taskId);
    @Query("SELECT * FROM Substep")
    public Substep[] getSubstepsOfAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long addSubstep(Substep substep);

    // Update entire row of table
    @Update()
    void updateSubstep(Substep substep);

    @Delete()
    void deleteSubstep(Substep substep);
}
