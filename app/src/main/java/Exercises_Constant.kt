import com.example.a7minuteworkout.R

class Exercises_Constant {
    companion object{
        fun getExerciseList() : ArrayList<Exercise_Model>{
            var exerciselist  = ArrayList<Exercise_Model> ()
            val abdominal_crunch : Exercise_Model = Exercise_Model(1,"abdominal_crunch", R.drawable.ic_abdominal_crunch,
                                                                                                                    false,false)
            exerciselist.add(abdominal_crunch)


            val high_knees = Exercise_Model(2,"High Knees",R.drawable.ic_high_knees_running_in_place,
                                                                                                                    false,false)
            exerciselist.add(high_knees)


            val jumping_jacks = Exercise_Model(3,"Jumping Jacks",R.drawable.ic_jumping_jacks,
                                                                                    false,false)
            exerciselist.add(jumping_jacks)

            val lunge = Exercise_Model(4, "Lunges",R.drawable.ic_lunge,
                                                                    false,false)
            exerciselist.add(lunge)

            val plank = Exercise_Model(5,"Plank",R.drawable.ic_plank,
                                                                    false,false)
            exerciselist.add(plank)

            val push_up = Exercise_Model(6, "Push Ups",R.drawable.ic_push_up,
                                                                        false,false)
            exerciselist.add(push_up)

            val push_up_rotation = Exercise_Model(7,"Push Ups And Rotations",R.drawable.ic_push_up_and_rotation,
                                                                                          false,false)
            exerciselist.add(push_up_rotation)

            val side_plank = Exercise_Model(8,":SIde Plank",R.drawable.ic_side_plank,
                                                                            false,false)
            exerciselist.add(side_plank)

            val squat = Exercise_Model(9,"Squats",R.drawable.ic_squat,
                                                                    false,false)
            exerciselist.add(squat)

            val step_up = Exercise_Model(10,"Step Up On Chair",R.drawable.ic_step_up_onto_chair,
                                                                       false,false)
            exerciselist.add(step_up)

            val triceps = Exercise_Model(11,"Tricep Dips",R.drawable.ic_triceps_dip_on_chair,
                                                                       false,false)
            exerciselist.add(triceps)

            val wall_sit = Exercise_Model(12,"Wall Sit",R.drawable.ic_wall_sit,
                                                                      false,false)
            exerciselist.add(wall_sit)


            return exerciselist
        }
    }
}