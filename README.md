# Personal Note Application

With the help of personal notes app, user can easily save the notes in the phone local momory/DB and can later on modify and even can delete it.

 - **Application overview**
    Application consist of two screens
      • Home screen(Fragmant) to load the data from the local DataBase.
      • Notes Screen(Activity) to create, update and to delete an existing note in the DB.
   
 - **Functional Requirements**
    • Kotlin is preferred but not a must.
    • Users must be able to create notes with input fields such as title, description, image url (input can be optional) and
    store it locally on their phones.
    • Created note must contain a created date.
    • There must be a way to display all saved notes in the list. An item on the list must contain the created date
    (dd/mm/yyyy), the image if url is available, title and max. 2 lines of description.
    • There must be a way to edit/delete previously created notes. But edited notes must contain an (edited) tag
    somewhere while being displayed on the list.
    • All data should be persisted locally
    
 - **Language**
    • Used **Kotlin** to develop the application with the power of **kotlin coroutines** for the task managemant, for more please visit https://developer.android.com/courses/pathways/android-coroutines
    
 - **Architecture**
    • Used **MVVM + Repository pattern** for design the architectural pattern, for more about MVVM visit https://medium.com/hongbeomi-dev/create-android-app-with-mvvm-pattern-simply-using-android-architecture-component-529d983eaabe 
    
 - **Architecture Flow**
     • **Request/Update from/to view(Fragmnet/Activity) <--> ViewModel(Controller/request manipulation) <--> Repository(the end point before DB) <--> DBDao(Query managemnat) <--> Room DataBase()**
    
 - **Data Base**
    • Used Room database for the app, poweredby google foe more please visit https://developer.android.com/topic/libraries/architecture/room?gclid=CjwKCAiAnvj9BRA4EiwAuUMDfyR7KAssy8H6-aNvZ4KfkK87I7pVv09bZ2ZOGU7iRnNPzR4bi0ncrBoCqR8QAvD_BwE&gclsrc=aw.ds **Major components** includes **1-DaocClass, 2-ModelClass, 3-DatabaseCalss**
    
 - **DI Framework**
    • Used Koin for as an dependency injection frame work, for more https://insert-koin.io/

 
 **App Database**
 ```Kotlin
 @Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "message_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
 ```
 
**NotesDao**
```Kotlin
@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setNote(note: Notes) : Long

    @Query("SELECT * from note_table ORDER BY id ASC")
    fun getNotes(): LiveData<List<Notes>>

    @Query("DELETE FROM note_table")
    fun deleteAll()

    @Query("SELECT * FROM note_table WHERE id=:id ")
    suspend fun getSingleNote(id: Int): Notes?

    @Update
    fun updateNote(vararg note: Notes) : Int

    @Query("DELETE FROM note_table WHERE id = :id")
    fun deleteById(id: Int)
}
```

**Model**
````Kotlin
@Entity(tableName = "note_table")
data class Notes(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "date")
    var date: Long? = System.currentTimeMillis(),

    @ColumnInfo(name = "isUpdated")
    var isUpdated: Boolean = false
)
````

**Repository**

```kotlin
class NotesRepository private constructor() : BaseRepository(),  CoroutineScope {

 //injecting the DB object to the repository
    protected val appDatabase: AppDatabase by inject(AppDatabase::class.java)

//constructing the CoroutineContext
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var notesDao: NotesDao? = appDatabase.getNotesDao()

    fun getNotesList() = notesDao?.getNotes()

    suspend fun getNote(id: Int) = notesDao?.getSingleNote(id)

    fun saveNote(note: Notes) {
        launch { saveNoteInDB(note) }
    }

    private suspend fun saveNoteInDB(note: Notes) {
        withContext(Dispatchers.IO) {
            notesDao?.setNote(note)
        }
    }

    fun updateNote(note: Notes) {
        launch { updateNoteInDB(note) }
    }

    private suspend fun updateNoteInDB(note: Notes) {
        withContext(Dispatchers.IO) {
            notesDao?.updateNote(note)
        }
    }

    fun deleteById(id : Int){
        launch { deleteNote(id) }
    }

    private suspend fun deleteNote(id : Int) {
        withContext(Dispatchers.IO) {
            notesDao?.deleteById(id)
        }
    }
}
```

**Developed By**
------------
[Malik Dawar](https://github.com/malikdawar/) - malikdawar332@gmail.com
