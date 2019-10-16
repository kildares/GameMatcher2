package matcher.game.silveira.avila.com.gamematcher2.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import matcher.game.silveira.avila.com.gamematcher2.db.MatchDao
import matcher.game.silveira.avila.com.gamematcher2.db.MatchDatabase

@Module(includes = [MatchDetailViewModelModule::class])
class MatchModule{

    @Provides
    fun provideDatabase(app : Application) : MatchDatabase{

        return Room.databaseBuilder(app, MatchDatabase::class.java, "match.db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideTaskDao(db : MatchDatabase) : MatchDao{
        return db.matchDao()
    }

}