package matcher.game.silveira.avila.com.gamematcher2.di.module.dao

import android.app.Application
import dagger.Module
import dagger.Provides
import matcher.game.silveira.avila.com.gamematcher2.db.MatchDatabase
import matcher.game.silveira.avila.com.gamematcher2.db.dao.MatchDao
import matcher.game.silveira.avila.com.gamematcher2.di.module.viewmodel.MatchViewModelModule

@Module(includes = [MatchViewModelModule::class])
class MatchModule{

    @Provides
    fun provideDatabase(app : Application) : MatchDatabase{
        return MatchDatabase.getInstance(app)
//        return Room.databaseBuilder(app, MatchDatabase::class.java, "match.db")
//            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideTaskDao(db : MatchDatabase) : MatchDao {
        return db.matchDao()
    }

}