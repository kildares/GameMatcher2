package matcher.game.silveira.avila.com.gamematcher2.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import matcher.game.silveira.avila.com.gamematcher2.db.MatchDao
import matcher.game.silveira.avila.com.gamematcher2.db.MatchDatabase

@Module
class MatchModule{

    @Provides
    fun provideDatabase(app : Application) : MatchDatabase{

        return Room.databaseBuilder(app, MatchDatabase::class.java, "task.db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideTaskDao(db : MatchDatabase) : MatchDao{
        return db.matchDao()
    }

}