class Exercise_Model(private var id : Int,private var  name: String,private var  image : Int,
                     private var isCompleted : Boolean,private var  isSelected : Boolean) {
    fun getid() : Int{
        return id
    }
    fun setid(id1 : Int){
        this.id = id1
    }
    fun getname () : String{
        return name
    }
    fun setname(name1 : String){
        this.name = name1
    }
    fun getimage () : Int{
        return image
    }
    fun setimage(image1 : Int){
        this.image  = image1
    }
    fun getisCompleted(): Boolean{
        return isCompleted
    }
    fun setisCompleted(isCompleted1 : Boolean){
        this.isCompleted = isCompleted1
    }
    fun getisSelected(): Boolean{
        return isSelected
    }
    fun setisSelected(isSelected1 : Boolean){
        this.isSelected = isSelected1
    }

}